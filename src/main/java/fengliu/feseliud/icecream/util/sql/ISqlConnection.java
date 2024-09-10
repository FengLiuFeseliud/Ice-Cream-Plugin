package fengliu.feseliud.icecream.util.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.function.Function;

/**
 * sql 连接
 */
public interface ISqlConnection {
    /**
     * 获取数据库地址
     *
     * @return 数据库地址
     */
    String getDBUrl();

    /**
     * 获取使用表名
     *
     * @return 表名
     */
    String getTableName();

    /**
     * 获取创建使用表 sql
     *
     * @return sql
     */
    String getCreateTableSql();

    /**
     * 以该配置执行 sql 语句
     *
     * @param sql sql
     * @return 结果
     */
    default <T> T executeSpl(Function<Statement, T> sql) {
        try {
            Class.forName("org.sqlite.JDBC");
            Connection connection = DriverManager.getConnection(this.getDBUrl());
            Statement statement = connection.createStatement();
            this.createTable(statement);
            T data = sql.apply(statement);
            statement.close();
            connection.close();
            return data;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 创建表
     */
    default void createTable(Statement statement) throws SQLException {
        statement.executeQuery(String.format("SELECT name FROM sqlite_master WHERE type='table' AND name='%s';", this.getTableName())).getString(1);
        statement.execute(this.getCreateTableSql());
    }
}

