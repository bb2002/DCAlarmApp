package kr.saintdev.dcalarmapp.modules.notifimanager;

import android.content.Context;

import kr.saintdev.dcalarmapp.modules.database.DatabaseManager;

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
    fun getAllNotifiAlarms() : ArrayList<NotificationAlarmMeta> {
        val cursor = databaseManager.executeQuery(SQLQueries.SELECT_DC_NOTIFIED_ALARMS, this.context, null)
        val notifications = ArrayList<NotificationAlarmMeta>()

        while(cursor.moveToNext()) {
            val tmp = NotificationAlarmMeta(
                    PostMeta(
                            cursor.getString(1),            // UUID
                            cursor.getString(2),            // URL
                            cursor.getString(3),            // Title
                            cursor.getString(4),            // Writer
                            cursor.getString(5).toDate(),    // Date
                            -1), cursor.getString(6).toDate()
            )
            notifications.add(tmp)
        }

        return notifications
    }

    /**
     * @Date 10.17 2019
     * add new notification alarm
     */
    fun addNotificationAlarm(item: NotificationAlarmMeta) {
        val stmt = databaseManager.makeInsertQuery(SQLQueries.INSERT_DC_NOTIFIED_ALARM, this.context)
        stmt.bindString(1, item.post.uuid)
        stmt.bindString(2, item.post.url)
        stmt.bindString(3, item.post.title)
        stmt.bindString(4, item.post.writer)
        stmt.bindString(5, item.post.date?.toFormatString() ?: DEFAULT_DATE_STRING)
        stmt.bindString(6, item.ndate.toFormatString())

        stmt.execute()
    }

    /**
     * @Date 10.18 2019
     * Clear all notification alarms.
     */
    fun removeAllNotificationAlarm() {
        databaseManager.makeReadQuery(SQLQueries.DELETE_DC_NOTIFIED_ALARMS, this.context).execute()
    }
}
