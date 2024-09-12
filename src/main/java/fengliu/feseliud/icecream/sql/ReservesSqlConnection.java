package fengliu.feseliud.icecream.sql;

import fengliu.feseliud.icecream.IceCreamPlugin;

public class ReservesSqlConnection implements ISqlConnection{
    @Override
    public String getDBUrl() {
        return "jdbc:sqlite:%s/%s"
                .formatted(
                        IceCreamPlugin.instance.getDataFolder().getAbsolutePath(),
                        "icecream.db"
                );
    }

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
                                      	"RESERVE_ITEM"	INTEGER NOT NULL,
                                      	"SERVER_MONEY"	INTEGER NOT NULL,
                                      	"TRADER_UUID"	TEXT NOT NULL,
                                      	PRIMARY KEY("ID" AUTOINCREMENT)
                                      );
                """, this.getTableName());
    }
}
