package kr.saintdev.dcalarmapp.modules.notifimanager;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import java.sql.PreparedStatement;
import java.util.ArrayList;

import kr.saintdev.dcalarmapp.modules.database.DatabaseConst;
import kr.saintdev.dcalarmapp.modules.database.DatabaseManager;
import kr.saintdev.dcalarmapp.modules.utils.DateUtils;
import kr.saintdev.dcalarmapp.modules.utils.PostMeta;

public class NotificationAlarmManager {
    private static NotificationAlarmManager ins = null;

    public static NotificationAlarmManager getInstance(Context context) {
        if(ins == null) {
            ins = new NotificationAlarmManager(context);
        }

        return ins;
    }

    private DatabaseManager databaseManager = null;
    private Context context = null;

    public NotificationAlarmManager(Context context) {
        this.databaseManager = DatabaseManager.getInstance();
        this.context = context;
    }

    /**
     * @Date 10.17 2019
     * get all notification alarms
     */
    public ArrayList<NotificationAlarmMeta> getAllNotifiAlarms() {
        Cursor cursor = databaseManager.executeQuery(DatabaseConst.SELECT_DC_NOTIFIED_ALARMS, this.context, null);
        ArrayList<NotificationAlarmMeta> notifications = new ArrayList<>();

        while(cursor.moveToNext()) {
            NotificationAlarmMeta tmp = new NotificationAlarmMeta(
                    new PostMeta(
                            cursor.getString(1),            // UUID
                            cursor.getString(2),            // URL
                            cursor.getString(3),            // Title
                            cursor.getString(4),            // Writer
                            DateUtils.stringToDate(cursor.getString(5)),    // Date
                            -1), DateUtils.stringToDate(cursor.getString(6))
            );
            notifications.add(tmp);
        }

        return notifications;
    }

    /**
     * @Date 10.17 2019
     * add new notification alarm
     */
    public void addNotificationAlarm(NotificationAlarmMeta item) {
        SQLiteStatement stmt = databaseManager.makeInsertQuery(this.context, DatabaseConst.INSERT_DC_NOTIFIED_ALARM);
        stmt.bindString(1, item.getPost().uuid);
        stmt.bindString(2, item.getPost().url);
        stmt.bindString(3, item.getPost().title);
        stmt.bindString(4, item.getPost().writer);
        stmt.bindString(5, DateUtils.dateToString(item.getPost().date));
        stmt.bindString(6, DateUtils.dateToString(item.getNdate()));

        stmt.execute();
    }

    /**
     * @Date 10.18 2019
     * Clear all notification alarms.
     */
    void removeAllNotificationAlarm() {
        databaseManager.makeReadQuery(DatabaseConst.DELETE_DC_NOTIFIED_ALARMS, this.context).execute();
    }
}
