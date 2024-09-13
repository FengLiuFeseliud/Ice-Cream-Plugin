package fengliu.feseliud.icecream.sql;

import java.sql.Connection;
import java.sql.SQLException;

public abstract class BaseSqlConnection implements ISqlConnection{
    public BaseSqlConnection(){
        this.execute(statement -> {
            this.createTable(statement);
            return null;
        });
    }

    @Override
    public Connection getConnection() throws SQLException {
        return DataSource.getDataSource().getConnection();
    }
}
