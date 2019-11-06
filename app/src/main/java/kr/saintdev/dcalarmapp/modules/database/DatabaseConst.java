package kr.saintdev.dcalarmapp.modules.database;

public interface DatabaseConst {
    String CREATE_TABLE_NOTIFIALARM = "CREATE TABLE dc_notified_alarms (" +
            "  _id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
            "  uuid TEXT NOT NULL," +
            "  url TEXT NOT NULL," +
            "  title TEXT NOT NULL DEFAULT ''," +
            "  writer TEXT NOT NULL DEFAULT ''," +
            "  post_date TEXT NOT NULL DEFAULT '1970-01-01 00:00:00'," +
            "  notified_date TEXT NOT NULL DEFAULT '1970-01-01 00:00:00');";

    String CREATE_TABLE_TARGETING_GALL = "CREATE TABLE dc_tracking_gallery (" +
            "  _id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
            "  gall_name TEXT NOT NULL," +
            "  gall_id TEXT NOT NULL," +
            "  wdate TEXT NOT NULL);";

    String CREATE_TABLE_KEYWORD = "CREATE TABLE app_keywords (" +
            "_id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
            "keyword TEXT NOT NULL DEFAULT '');";

    String INSERT_DC_TARGETING_GALLERY = "INSERT INTO dc_tracking_gallery (gall_name, gall_id, wdate) VALUES(?,?,?);";

    String INSERT_DC_NOTIFIED_ALARM = "INSERT INTO dc_notified_alarms (uuid, url, title, writer, post_date, notified_date) VALUES(?,?,?,?,?,?)";

    String SELECT_DC_NOTIFIED_ALARMS = "SELECT * FROM dc_notified_alarms ORDER BY _id DESC";

    String DELETE_DC_NOTIFIED_ALARMS = "DELETE FROM dc_notified_alarms";

    String DELETE_DC_TARGETING_GALLERY = "DELETE FROM dc_tracking_gallery WHERE gall_id = ?";

    String SELECT_DC_TARGETING_GALLERY_ALL = "SELECT * FROM dc_tracking_gallery ORDER BY _id DESC;";

    String SELECT_DC_TARGETING_GALLERY_WHERE_ID = "SELECT * FROM dc_tracking_gallery WHERE gall_id = ? ORDER BY _id DESC;";

    String SELECT_KEYWORD_ALL = "SELECT * FROM app_keywords ORDER BY _id DESC;";

    String SELECT_KEYWORD_WHERE_ID = "SELECT * FROM app_keywords WHERE _id = ?;";

    String DELETE_KEYWORD_WHERE_ID = "DELETE FROM app_keywords WHERE _id = ?;";

    String INSERT_KEYWORD = "INSERT INTO app_keywords (keyword) VALUES(?)";

}
