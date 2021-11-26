package wiki.thin.module.backup;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.ResultSetMetaData;
import java.sql.Types;
import java.util.List;
import java.util.Objects;

/**
 * @author Beldon
 */
@Service
class MySqlService {

    private final JdbcTemplate jdbcTemplate;

    public MySqlService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<String> getAllTables(String database) {
        return jdbcTemplate.query("SHOW TABLE STATUS FROM `" + database + "`;",
                (resultSet, i) -> resultSet.getString("Name"));
    }

    public String getQueryCreateTableSql(String table) {
        final List<String> data = jdbcTemplate.query("SHOW CREATE TABLE " + "`" + table + "`;",
                (resultSet, i) -> resultSet.getString(2));
        if (data.size() == 1) {
            return "DROP TABLE IF EXISTS `" + table + "`;\n" + data.get(0);
        }
        return null;
    }

    public String getDataInsertSql(String table) {
        StringBuilder insertSql = new StringBuilder();
        insertSql.append("INSERT INTO `").append(table).append("`(");
        final List<String> dataSqls = jdbcTemplate.query("SELECT * FROM `" + table + "`;", (rs, index) -> {
            ResultSetMetaData metaData = rs.getMetaData();
            if (index == 0) {
                for (int j = 0; j < metaData.getColumnCount(); j++) {
                    insertSql.append("`")
                            .append(metaData.getColumnName(j + 1))
                            .append("`, ");
                }
                insertSql.deleteCharAt(insertSql.length() - 1).deleteCharAt(insertSql.length() - 1).append(") VALUES \n");
            }
            StringBuilder sql = new StringBuilder();
            sql.append("(");
            for (int j = 0; j < rs.getMetaData().getColumnCount(); j++) {

                int columnType = metaData.getColumnType(j + 1);
                int columnIndex = j + 1;

                if (Objects.isNull(rs.getObject(columnIndex))) {
                    sql.append("").append(rs.getObject(columnIndex)).append(", ");
                } else if (columnType == Types.INTEGER || columnType == Types.TINYINT || columnType == Types.BIT) {
                    sql.append(rs.getInt(columnIndex)).append(", ");
                } else {

                    String val = rs.getString(columnIndex);
                    val = val.replace("'", "\\'");

                    sql.append("'").append(val).append("', ");
                }
            }

            sql.deleteCharAt(sql.length() - 1).deleteCharAt(sql.length() - 1);

            if (rs.isLast()) {
                sql.append(")");
            } else {
                sql.append("),\n");
            }

            return sql.toString();
        });

        if (dataSqls.isEmpty()) {
            return "";
        }

        for (String dataSql : dataSqls) {
            insertSql.append(dataSql);
        }

        insertSql.append(";");
        return insertSql.toString();
    }
}
