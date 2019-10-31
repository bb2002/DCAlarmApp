package kr.saintdev.dcalarmapp.modules.keywordmanager;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import java.util.ArrayList;

import kr.saintdev.dcalarmapp.modules.database.DatabaseConst;
import kr.saintdev.dcalarmapp.modules.database.DatabaseManager;

public class KeywordManager {
    private static KeywordManager ins = null;

    public KeywordManager getInstance(Context context) {
        if(ins == null) {
            ins = new KeywordManager(context);
        }

        return ins;
    }

    private Context context = null;
    private DatabaseManager databaseManager = null;

    private KeywordManager(Context context) {
        this.context = context;
        this.databaseManager = DatabaseManager.getInstance();
    }

    /**
     * @Date 10.10 2019
     * return All Keywords.
     * @return ArrayList<Keyword>       Keyword Array.
     */
    public ArrayList<Keyword> readAllKeywords() {
        Cursor cursor = this.databaseManager.executeQuery(DatabaseConst.SELECT_KEYWORD_ALL, this.context, null);
        ArrayList<Keyword> keywords = new ArrayList<>();

        while(cursor.moveToNext()) {
            Keyword tmp = new Keyword(cursor.getInt(0), cursor.getString(1));
            keywords.add(tmp);
        }

        cursor.close();
        return keywords;
    }

    public Keyword readKeyword(int id) {
        Cursor cursor = this.databaseManager.executeQuery(DatabaseConst.SELECT_KEYWORD_WHERE_ID, this.context, new String[] { id + "" });

        if(cursor.moveToNext()) {
            Keyword tmp =  new Keyword(cursor.getInt(1), cursor.getString(2));
            cursor.close();
            return tmp;
        }

        return null;
    }

    public void removeKeyword(int id) {
        SQLiteStatement stmt = this.databaseManager.makeReadQuery(DatabaseConst.DELETE_KEYWORD_WHERE_ID, this.context);
        stmt.bindLong(1, (long) id);
        stmt.execute();
    }

    public void insertKeyword(Keyword keyword) {
        SQLiteStatement stmt = this.databaseManager.makeInsertQuery(this.context, DatabaseConst.INSERT_KEYWORD);
        stmt.bindString(1, keyword.keyword);
        stmt.execute();
    }

    public boolean isExsit(Keyword k) {
        ArrayList<Keyword> keywords = readAllKeywords();

        for(Keyword kd : keywords) {
            if(kd.keyword.equals(k.keyword)) return true;
        }

        return false;
    }
}
