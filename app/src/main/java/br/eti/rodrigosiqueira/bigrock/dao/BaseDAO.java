package br.eti.rodrigosiqueira.bigrock.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.List;

/**
 * Created by luiz.massa on 18/05/17.
 */

public class BaseDAO extends SQLiteOpenHelper {

    protected String tablename;
    private String createDatabase = "CREATE TABLE IF NOT EXISTS BIG_ROCK( ID_BIG_ROCK INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, NM_BIG_ROCK VARCHAR(100) NOT NULL, DS_BIG_ROCK VARCHAR(500), VL_BIG_ROCK INTEGER)";
    private String dropDatabase = "DROP TABLE IF EXISTS BIG_ROCK";

    public BaseDAO(Context context) {
        super(context, "application_db", null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(createDatabase);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(dropDatabase);
        onCreate(db);
    }

    protected Object getItem(String sql) {
        SQLiteDatabase db = getReadableDatabase();
        return db.rawQuery(sql, null);
    }

    protected Object getItemParam(String sql, String[] params) {

        SQLiteDatabase db = getReadableDatabase();
        return db.rawQuery(sql, params);

    }

    protected Object getList(String sql) {

        SQLiteDatabase db = getReadableDatabase();
        return db.rawQuery(sql, null);

    }

    protected Object getListParam(String sql, String[] params) {
        SQLiteDatabase db = getReadableDatabase();
        return db.rawQuery(sql, params);
    }

    protected int insertItem(String tablename, ContentValues contentValues) {

        SQLiteDatabase db = getWritableDatabase();
        return (int) db.insert(tablename, null, contentValues);


    }

    protected int updateItem(String tablename, ContentValues contentValues, String where, String[] params) {

        SQLiteDatabase db = getWritableDatabase();
        return db.update(tablename, contentValues, where, params);

    }

    protected int deleteItem(String tablename, String where, String[] params) {

        SQLiteDatabase db = getWritableDatabase();
        return db.delete(tablename, where, params);

    }

}
