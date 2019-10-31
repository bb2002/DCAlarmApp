package kr.saintdev.dcalarmapp.modules.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

public class DatabaseManager {
    private static DatabaseManager instance = null;

    public static DatabaseManager getInstance() {
        if(instance == null) {
            instance = new DatabaseManager();
        }

        return instance;
    }

    private DatabaseHelper helper = null;

    private void initHelper(Context context) {
        if(this.helper == null) {
            this.helper = new DatabaseHelper(context, "dcalarm.db", null, 1);
        }
    }

    public SQLiteStatement makeInsertQuery(Context context, String query) {
        initHelper(context);
        SQLiteDatabase writable = this.helper.getWritableDatabase();
        return writable.compileStatement(query);
    }

    public SQLiteStatement makeReadQuery(String query, Context context) {
        initHelper(context);
        SQLiteDatabase readable = this.helper.getReadableDatabase();
        return readable.compileStatement(query);
    }

    public Cursor executeQuery(String sql, Context context, String[] args) {
        initHelper(context);
        SQLiteDatabase readable = this.helper.getReadableDatabase();
        return readable.rawQuery(sql, args);
    }


    private class DatabaseHelper extends SQLiteOpenHelper {
        DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        @Override
        public void onCreate(SQLiteDatabase p0) {
            p0.execSQL(DatabaseConst.CREATE_TABLE_METASET);
            p0.execSQL(DatabaseConst.CREATE_TABLE_TARGETING_GALL);
            p0.execSQL(DatabaseConst.CREATE_TABLE_KEYWORD);
        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        }
    }
}
