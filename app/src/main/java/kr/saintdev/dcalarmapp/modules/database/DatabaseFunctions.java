package kr.saintdev.dcalarmapp.modules.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import java.util.ArrayList;

import kr.saintdev.dcalarmapp.modules.utils.GalleryMeta;

import static kr.saintdev.dcalarmapp.modules.database.DatabaseConst.SELECT_DC_TARGETING_GALLERY_ALL;
import static kr.saintdev.dcalarmapp.modules.database.DatabaseConst.SELECT_DC_TARGETING_GALLERY_WHERE_ID;
import static kr.saintdev.dcalarmapp.modules.utils.DateUtils.getNowToString;

public class DatabaseFunctions {
    /**
     * @Date 10.02 2019
     * 새 관리할 갤러리를 DB 에 삽입한다.
     */
    public static void Insert(GalleryMeta meta, DatabaseManager dbm, Context context) {
        SQLiteStatement pst = dbm.makeInsertQuery(context, DatabaseConst.INSERT_DC_TARGETING_GALLERY);
        pst.bindString(1, meta.galleryName);
        pst.bindString(2, meta.galleryID);
        pst.bindString(3, getNowToString());
        pst.executeInsert();
    }

    public static void removeFromDB(GalleryMeta meta, DatabaseManager dbm, Context context) {
        SQLiteStatement pst = dbm.makeInsertQuery(context, DatabaseConst.DELETE_DC_TARGETING_GALLERY);
        pst.bindString(1, meta.galleryID);
        pst.execute();
    }

    /**
     * @Date 10.02 2019
     * 관리중인 갤러리의 데이터를 불러 온다.
     */
    public static ArrayList<GalleryMeta> readAll(Context context) {
        DatabaseManager dbm = DatabaseManager.getInstance();
        Cursor cursor = dbm.executeQuery(SELECT_DC_TARGETING_GALLERY_ALL, context,null);
        ArrayList<GalleryMeta> datas = new ArrayList<>();

        while(cursor.moveToNext()) {
            datas.add(
                    new GalleryMeta(cursor.getString(1), cursor.getString(2), cursor.getString(3)));
        }

        return datas;
    }

    /**
     * @Date 10.07 2019
     * 특정 갤러리의 데이터를 불러 온다.
     */
    public static ArrayList<GalleryMeta> read(Context context, GalleryMeta gallery) {
        DatabaseManager dbm = DatabaseManager.getInstance();
        Cursor cursor = dbm.executeQuery(SELECT_DC_TARGETING_GALLERY_WHERE_ID, context, new String [] { gallery.galleryID });
        ArrayList<GalleryMeta> datas = new ArrayList<>();

        while(cursor.moveToNext()) {
            datas.add(
                    new GalleryMeta(cursor.getString(1), cursor.getString(2), cursor.getString(3)));
        }

        return datas;
    }
}
