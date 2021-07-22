//package wiki.thin.backup;
//
//import org.springframework.stereotype.Service;
//import org.springframework.util.StringUtils;
//import org.zeroturnaround.zip.ZipUtil;
//import wiki.thin.exception.UnexpectedException;
//
//import java.io.File;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.OutputStream;
//import java.nio.charset.StandardCharsets;
//import java.nio.file.Files;
//import java.nio.file.Path;
//import java.nio.file.Paths;
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Comparator;
//import java.util.Date;
//import java.util.List;
//
///**
// * @author Beldon
// */
//@Service
//public class BackupServiceImpl implements BackupService {
//
//    private static final String BACKUP_PATH = "./sql";
//
//    private final MySqlService mySqlService;
//
//    public BackupServiceImpl(MySqlService mySqlService) {
//        this.mySqlService = mySqlService;
//    }
//
//    @Override
//    public void backup() throws IOException {
//        final String fileName = generateFileName();
//        final Path filePath = Path.of(BACKUP_PATH, fileName);
//        final File parentFile = filePath.toFile().getParentFile();
//        if (!parentFile.exists()) {
//            final boolean mkdirSuccess = parentFile.mkdirs();
//            if (!mkdirSuccess) {
//                throw new UnexpectedException("文件夹创建失败");
//            }
//        }
//        final String sql = exportToSql("thin-wiki");
//        OutputStream outputStream = Files.newOutputStream(filePath);
//
//        try (outputStream) {
//            outputStream.write(sql.getBytes(StandardCharsets.UTF_8));
//        }
//        ZipUtil.packEntry(new File(BACKUP_PATH, fileName), new File(BACKUP_PATH, fileName + ".zip"));
//        Files.deleteIfExists(filePath);
//    }
//
//    @Override
//    public void backup(int retainFiles) throws IOException {
//        backup();
//
//        final List<BackupFile> files = list();
//
//        if (files.size() > retainFiles) {
//            files.sort(Comparator.comparing(BackupFile::getLastModified).reversed());
//            for (int i = retainFiles; i < files.size(); i++) {
//                Files.deleteIfExists(Paths.get(BACKUP_PATH, files.get(i).getFileName()));
//            }
//        }
//    }
//
//    @Override
//    public List<BackupFile> list() {
//        final File filePath = new File(BACKUP_PATH);
//        if (!filePath.exists()) {
//            return List.of();
//        }
//
//        List<BackupFile> files = new ArrayList<>();
//        final File[] backupFiles = filePath.listFiles(f -> f.getName().endsWith(".zip"));
//        if (backupFiles != null) {
//            for (File f : backupFiles) {
//                BackupFile backupFile = new BackupFile();
//                backupFile.setFileName(f.getName());
//                backupFile.setLength(f.length());
//                backupFile.setLastModified(new Date(f.lastModified()));
//
//                files.add(backupFile);
//
//            }
//        }
//
//        files.sort(Comparator.comparing(BackupFile::getLastModified).reversed());
//
//        return files;
//    }
//
//    @Override
//    public void restore(String fileName) {
//
//    }
//
//    @Override
//    public void delete(String fileName) throws IOException {
//        final File filePath = new File(BACKUP_PATH);
//        if (!filePath.exists()) {
//            return;
//        }
//
//        final File[] backupFiles = filePath.listFiles(f -> f.getName().equals(fileName));
//        if (backupFiles != null) {
//            for (File backupFile : backupFiles) {
//                Files.delete(backupFile.toPath());
//            }
//        }
//    }
//
//    @Override
//    public void download(String fileName, OutputStream outputStream) throws IOException {
//        final File filePath = new File(BACKUP_PATH);
//        if (!filePath.exists()) {
//            return;
//        }
//        final File[] backupFiles = filePath.listFiles((dir, name) -> name.equals(fileName));
//        if (backupFiles == null || backupFiles.length == 0) {
//            return;
//        }
//        InputStream is = Files.newInputStream(backupFiles[0].toPath());
//        try (is) {
//            is.transferTo(outputStream);
//        }
//    }
//
//    private String exportToSql(String database) {
//        StringBuilder sql = new StringBuilder();
//        sql.append("--");
//        sql.append("\n-- Generated by thin-wiki");
//        sql.append("\n-- https://github.com/beldon");
//        sql.append("\n-- Date: ").append(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
//        sql.append("\n--");
//
//        //these declarations are extracted from HeidiSQL
//        sql.append("\n\n/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;")
//                .append("\n/*!40101 SET NAMES utf8 */;")
//                .append("\n/*!50503 SET NAMES utf8mb4 */;")
//                .append("\n/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;")
//                .append("\n/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;");
//
//        final List<String> allTables = mySqlService.getAllTables(database);
//        for (String table : allTables) {
//            sql.append(getTableInsertStatement(table.trim()));
//            sql.append(getDataInsertStatement(table.trim()));
//        }
//
//        sql.append("\n/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;")
//                .append("\n/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;")
//                .append("\n/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;");
//
//        return sql.toString();
//    }
//
//    private String getTableInsertStatement(String table) {
//        StringBuilder sql = new StringBuilder();
//        if (table != null && !table.isEmpty()) {
//            final String createTableSql = mySqlService.getQueryCreateTableSql(table);
//            if (StringUtils.hasText(createTableSql)) {
//                sql.append("\n\n--");
//                sql.append("\n").append("-- start").append("  table dump : ").append(table);
//                sql.append("\n--\n");
//
//                sql.append(createTableSql).append(";\n");
//
//                sql.append("\n--");
//                sql.append("\n").append("-- end").append("  table dump : ").append(table);
//                sql.append("\n--\n");
//            }
//        }
//        return sql.toString();
//    }
//
//    private String getDataInsertStatement(String table) {
//
//        final String dataInsertSql = mySqlService.getDataInsertSql(table);
//        if (!StringUtils.hasText(dataInsertSql)) {
//            return "";
//        }
//
//        StringBuilder sql = new StringBuilder();
//        sql.append("--").append("\n-- Inserts of ").append(table).append("\n--\n");
//
//        sql.append("\n/*!40000 ALTER TABLE `").append(table).append("` DISABLE KEYS */;\n");
//
//        sql.append("\n--\n")
//                .append("-- start").append(" table insert : ").append(table)
//                .append("\n--\n");
//        sql.append(dataInsertSql);
//        sql.append(";");
//
//        sql.append("\n--\n")
//                .append("-- end").append(" table insert : ").append(table)
//                .append("\n--\n");
//        sql.append("\n/*!40000 ALTER TABLE `").append(table).append("` ENABLE KEYS */;\n");
//        return sql.toString();
//    }
//
//    private String generateFileName() {
//        return new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss").format(new Date()) + ".sql";
//    }
//}
