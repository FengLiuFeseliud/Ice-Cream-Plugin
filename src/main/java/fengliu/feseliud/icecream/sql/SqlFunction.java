package fengliu.feseliud.icecream.sql;

import java.sql.SQLException;
import java.sql.Statement;

public interface SqlFunction<R>{
    R execute(Statement statement) throws SQLException;
}
