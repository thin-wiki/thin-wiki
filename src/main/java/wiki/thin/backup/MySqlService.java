package wiki.thin.backup;

import lombok.AllArgsConstructor;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author Beldon
 */
@Service
@AllArgsConstructor
public class MySqlService {

    private final DatabaseClient databaseClient;

    public Flux<String> getAllTables() {
        return currentDatabase()
                .flatMapMany(this::getAllTables);
    }

    public Mono<String> getCreateTableSql(String table) {
        return databaseClient.sql("SHOW CREATE TABLE " + "`" + table + "`;")
                .fetch()
                .first()
                .map(v -> "DROP TABLE IF EXISTS `" + table + "`;\n" + v.get("Table").toString())
                .defaultIfEmpty("");
    }

    public Mono<String> getDataInsertSql(String table) {
        AtomicBoolean hasData = new AtomicBoolean(false);
        StringBuilder insertSql = new StringBuilder();
        insertSql.append("INSERT INTO `").append(table).append("`(");

        List<ColumnMeta> columnMetas = new LinkedList<>();
        return currentDatabase()
                .flatMapMany(dbName -> getColumnMetaData(dbName, table))
                .doOnNext(columnMeta -> {
                    insertSql.append("`").append(columnMeta.name).append("`, ");
                    columnMetas.add(columnMeta);
                })
                .then(Mono.just(insertSql))
                .doOnNext(sql -> sql.deleteCharAt(sql.length() - 2).append(") VALUES "))
                .thenMany(queryData(table, columnMetas))
                .doOnNext(sql -> {
                    insertSql.append(sql).append(",");
                    hasData.set(true);
                })
                .then(Mono.just(insertSql))
                .doOnNext(sql -> {
                    sql.deleteCharAt(sql.length() - 1);
                    sql.append("\n");
                })
                .map(sql -> hasData.get() ? sql.toString() : "");

    }

    private Mono<String> currentDatabase() {
        return databaseClient.sql("select database() as db")
                .fetch()
                .one()
                .map(res -> res.get("db").toString());
    }


    private Flux<String> getAllTables(String database) {
        return databaseClient.sql("select TABLE_NAME from information_schema.`TABLES` a where a.TABLE_SCHEMA = '" + database + "';")
                .fetch()
                .all()
                .map(data -> data.get("TABLE_NAME").toString());
    }

    private Flux<ColumnMeta> getColumnMetaData(String database, String tableName) {
        String sql = "select COLUMN_NAME,DATA_TYPE from information_schema.`COLUMNS` a " +
                "where a.TABLE_SCHEMA = '" + database + "' and a.TABLE_NAME = '" + tableName + "';";
        return databaseClient.sql(sql)
                .fetch()
                .all()
                .map(data -> new ColumnMeta(data.get("COLUMN_NAME").toString(), data.get("DATA_TYPE").toString()));
    }

    private Flux<String> queryData(String table, List<ColumnMeta> columnMetas) {
        return databaseClient.sql("SELECT * FROM `" + table + "`;")
                .map(row -> {
                    StringBuilder sql = new StringBuilder("(");
                    for (ColumnMeta columnMeta : columnMetas) {
                        sql.append(columnMeta.convert(row.get(columnMeta.name))).append(",");
                    }
                    sql.deleteCharAt(sql.length() - 1).append(")");
                    return sql.toString();
                })
                .all();
    }


    @AllArgsConstructor
    static class ColumnMeta {
        private final String name;
        private final String type;

        public String convert(Object value) {
            if (Objects.isNull(value)) {
                return "null";
            }
            if (isNumber()) {
                return value.toString();
            }
            return "\"" + value + "\"";
        }

        public boolean isNumber() {
            return type.equalsIgnoreCase("bit")
                    || type.equalsIgnoreCase("int")
                    || type.equalsIgnoreCase("integer")
                    || type.equalsIgnoreCase("tinyint");
        }
    }
}
