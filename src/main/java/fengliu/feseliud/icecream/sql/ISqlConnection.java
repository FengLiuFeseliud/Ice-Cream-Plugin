package fengliu.feseliud.icecream.sql;

import fengliu.feseliud.icecream.config.message.Message;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * sql 连接工具
 */
public interface ISqlConnection {
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
     * 获取数据库连接
     * @return 数据库连接
     */
    Connection getConnection() throws SQLException;

    /**
     * 以该配置执行 sql 语句
     *
     * @param sql sql
     * @return 结果
     */
    default <T> T execute(SqlFunction<T> sql) {
        try {
            Statement statement = this.getConnection().createStatement();
            T data = sql.execute(statement);
            statement.close();
            return data;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 创建默认表
     */
    default void createTable(Statement statement) throws SQLException{
        String tableName = statement.executeQuery(String.format("SELECT name FROM sqlite_master WHERE type='table' AND name='%s';", this.getTableName())).getString(1);
        if (tableName != null){
            return;
        }

        try {
            Message.CREATE_TABLE.info(this.getTableName());
            statement.executeUpdate(this.getCreateTableSql());
        } catch (Exception exception){
            Message.CREATE_ERROR_TABLE.warning(this.getTableName());
            exception.printStackTrace();
        }
    }
}

