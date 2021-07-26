package wiki.thin.backup;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.r2dbc.core.FetchSpec;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.sql.ResultSetMetaData;
import java.sql.Types;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;

/**
 * @author Beldon
 */
@Service
@AllArgsConstructor
public class MySqlService {

    private final DatabaseClient databaseClient;

    public Flux<String> getAllTables(String database) {
        return databaseClient.sql("SHOW TABLE STATUS FROM `" + database + "`;")
                .fetch()
                .all()
                .map(stringObjectMap -> stringObjectMap.get("Auto_increment").toString());
    }

    public Mono<String> getQueryCreateTableSql(String table) {
        return databaseClient.sql("SHOW CREATE TABLE " + "`" + table + "`;")
                .fetch()
                .first()
                .map(v -> "DROP TABLE IF EXISTS `" + table + "`;\n" + v.get("Table").toString())
                .defaultIfEmpty("");
    }

    public Flux<Map<String, Object>> getDataInsertSql(String table) {
        StringBuilder insertSql = new StringBuilder();
        insertSql.append("INSERT INTO `").append(table).append("`(");
        final Flux<Map<String, Object>> all = databaseClient.sql("SELECT * FROM `" + table + "`;")
                .fetch()
                .all();

        return all;
    }

//    public String getDataInsertSql(String table) {
//        StringBuilder insertSql = new StringBuilder();
//        insertSql.append("INSERT INTO `").append(table).append("`(");
//        final List<String> dataSqls = jdbcTemplate.query("SELECT * FROM `" + table + "`;", (rs, index) -> {
//            ResultSetMetaData metaData = rs.getMetaData();
//            if (index == 0) {
//                for (int j = 0; j < metaData.getColumnCount(); j++) {
//                    insertSql.append("`")
//                            .append(metaData.getColumnName(j + 1))
//                            .append("`, ");
//                }
//                insertSql.deleteCharAt(insertSql.length() - 1).deleteCharAt(insertSql.length() - 1).append(") VALUES \n");
//            }
//            StringBuilder sql = new StringBuilder();
//            sql.append("(");
//            for (int j = 0; j < rs.getMetaData().getColumnCount(); j++) {
//
//                int columnType = metaData.getColumnType(j + 1);
//                int columnIndex = j + 1;
//
//                if (Objects.isNull(rs.getObject(columnIndex))) {
//                    sql.append("").append(rs.getObject(columnIndex)).append(", ");
//                } else if (columnType == Types.INTEGER || columnType == Types.TINYINT || columnType == Types.BIT) {
//                    sql.append(rs.getInt(columnIndex)).append(", ");
//                } else {
//
//                    String val = rs.getString(columnIndex);
//                    val = val.replace("'", "\\'");
//
//                    sql.append("'").append(val).append("', ");
//                }
//            }
//
//            sql.deleteCharAt(sql.length() - 1).deleteCharAt(sql.length() - 1);
//
//            if (rs.isLast()) {
//                sql.append(")");
//            } else {
//                sql.append("),\n");
//            }
//
//            return sql.toString();
//        });
//
//        if (dataSqls.isEmpty()) {
//            return "";
//        }
//
//        for (String dataSql : dataSqls) {
//            insertSql.append(dataSql);
//        }
//
//        insertSql.append(";");
//        return insertSql.toString();
//    }
}
