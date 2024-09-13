package fengliu.feseliud.icecream.sql;

import fengliu.feseliud.icecream.config.ReservesConfig;
import org.bukkit.entity.Player;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;

public class ReservesSqlConnection extends BaseSqlConnection{
    public static final ReservesSqlConnection instance = new ReservesSqlConnection();

    @Override
    public String getTableName() {
        return "RESERVES_TABLE";
    }

    @Override
    public String getCreateTableSql() {
        return String.format("""
                            CREATE TABLE "%s" (
                                      	"ID"	INTEGER NOT NULL,
                                      	"TRADE_TIME"	INTEGER NOT NULL,
                                      	"RESERVE_ITEM_COUNT"	INTEGER NOT NULL,
                                      	"SERVER_MONEY"	INTEGER NOT NULL,
                                      	"TRADER_UUID"	TEXT NOT NULL,
                                      	PRIMARY KEY("ID" AUTOINCREMENT)
                                      );
                """, this.getTableName());
    }

    private ResultSet getReserves(Statement statement) throws SQLException {
        return statement.executeQuery("SELECT max(ID), * FROM '%s';".formatted(this.getTableName()));
    }

    private int insertReserves(Statement statement, long reservesItemCount, double serverMoney, Player player) throws SQLException {
        return statement.executeUpdate("INSERT INTO '%s' (TRADE_TIME,RESERVE_ITEM_COUNT,SERVER_MONEY,TRADER_UUID) VALUES ('%s', %s, %s, '%s');"
                        .formatted(this.getTableName(), new Timestamp(System.currentTimeMillis()), reservesItemCount, serverMoney, player.getUniqueId()));
    }

    private double getServerMoney(Statement statement) throws SQLException {
        return statement.executeQuery("SELECT max(ID), SERVER_MONEY FROM '%s'".formatted(this.getTableName())).getLong(1);
    }

    public double getServerMoney(){
        return this.execute(this::getServerMoney);
    }

    private long getReservesItemCount(Statement statement) throws SQLException {
        return statement.executeQuery("SELECT max(ID), RESERVE_ITEM_COUNT FROM '%s'".formatted(this.getTableName())).getLong(1);
    }

    public long getReservesItemCount(){
        return this.execute(this::getReservesItemCount);
    }

    public void subReservesItem(int itemCount, Player player){
        this.execute(statement -> {
            int reservesItemCount;
            double serverMoney;

            ResultSet result = this.getReserves(statement);
            if (result.getInt(1) == 0) {
                reservesItemCount = ReservesConfig.INITIAL_ITEM_COUNT.get();
                serverMoney = ReservesConfig.INITIAL_SERVER_MONEY.get();
            } else {
                reservesItemCount = result.getInt(4);
                serverMoney = result.getDouble(5);
            }

            double addMoney = itemCount / ReservesConfig.RATE.get();
            reservesItemCount += itemCount;
            serverMoney += addMoney - itemCount / ReservesConfig.ITEM_RATE.get();
            return this.insertReserves(statement, reservesItemCount, serverMoney, player);
        });
    }

    public void takeReservesItem(int itemCount, double money, Player player){
        this.execute(statement -> {
            long reservesItemCount;
            double serverMoney;

            ResultSet result = this.getReserves(statement);
            if (result.getInt(1) == 0) {
                reservesItemCount = ReservesConfig.INITIAL_ITEM_COUNT.get();
                serverMoney = ReservesConfig.INITIAL_SERVER_MONEY.get();
            } else {
                reservesItemCount = result.getInt(4);
                serverMoney = result.getDouble(5);
            }

            reservesItemCount -= itemCount;
            serverMoney += money;
            return this.insertReserves(statement, reservesItemCount, serverMoney, player);
        });
    }

    public boolean payServerMoney(double money, Player player){
        return this.execute(statement -> {
            ResultSet result = this.getReserves(statement);
            if (result.getInt(1) == 0){
                return false;
            }

            double serverMoney = result.getDouble(5);
            if (money > serverMoney){
                return false;
            }

            this.insertReserves(statement, result.getInt(4), serverMoney - money, player);
            return true;
        });
    }
}
