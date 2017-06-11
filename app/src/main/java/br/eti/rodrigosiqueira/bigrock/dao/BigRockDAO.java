package br.eti.rodrigosiqueira.bigrock.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.util.ArrayList;
import java.util.List;

import br.eti.rodrigosiqueira.bigrock.model.BigRock;

/**
 * Created by luiz.massa on 15/05/17.
 */

public class BigRockDAO extends BaseDAO {

    private String tableName = "big_rock";

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createDataBase = "CREATE TABLE IF NOT EXISTS BIG_ROCK( ID_BIG_ROCK INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, NM_BIG_ROCK VARCHAR(100) NOT NULL, DS_BIG_ROCK VARCHAR(1000), TP_STATUS VARCHAR(20), DT_INSERT DATETIME, DT_END DATETIME, NR_LAT VARCHAR(100), NR_LNG VARCHAR(100))";
        db.execSQL(createDataBase);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String dropDataBase = "DROP TABLE IF EXISTS BIG_ROCK";
        db.execSQL(dropDataBase);
        onCreate(db);
    }

    public BigRockDAO(Context context) {
        super(context);
    }


    public BigRock getBigRock(Integer idBigRock) {
       return proccessRow((Cursor) getItemParam("SELECT * FROM big_rock WHERE id_big_rock = ?", new String[]{idBigRock.toString()}));
    }


    public List<BigRock> getBigRocks() {
        return  proccessList((Cursor) getList("SELECT * FROM big_rock"));
    }


    public BigRock insertBigRock(BigRock bigRock) {


        ContentValues contentValues = new ContentValues();
        contentValues.put("NM_BIG_ROCK", bigRock.getNmBigRock());
        contentValues.put("DS_BIG_ROCK", bigRock.getDsBigRock());
        contentValues.put("TP_STATUS", bigRock.getTpStatus());
        contentValues.put("NR_LAT", bigRock.getNrLat());
        contentValues.put("NR_LNG", bigRock.getNrLng());

        Integer idBigRock = insertItem(tableName, contentValues);

        bigRock = getBigRock(idBigRock);

        return bigRock;
    }

    public BigRock updateBigRock(BigRock bigRock) {

        ContentValues contentValues = new ContentValues();
        contentValues.put("NM_BIG_ROCK", bigRock.getNmBigRock());
        contentValues.put("DS_BIG_ROCK", bigRock.getDsBigRock());
        contentValues.put("TP_STATUS", bigRock.getTpStatus());
        contentValues.put("DT_INSERT", bigRock.getDtInsert().toString());
        contentValues.put("DT_END", bigRock.getDtEnd().toString());
        contentValues.put("NR_LAT", bigRock.getNrLat());
        contentValues.put("NR_LNG", bigRock.getNrLng());

        String[] params = {bigRock.getIdBigRock().toString()};
        String where = "id_big_rock = ?";
        int update = updateItem(tableName, contentValues, where, params);

        if(update > 0) {
            bigRock = getBigRock(bigRock.getIdBigRock());
        } else {
            bigRock = new BigRock();
        }

        return bigRock;
    }

    public Boolean deleteBigRock(BigRock bigRock) {

        String[] params = {bigRock.getIdBigRock().toString()};
        String where = "id_big_rock = ?";
        int delete = deleteItem(tableName, where, params);
        if(delete > 0) {
            return true;
        } else {
            return  false;
        }
    }


    public BigRock proccessRow(Cursor cursor) {
        BigRock bigRock = new BigRock();

        while (cursor.moveToNext()) {
            bigRock.setIdBigRock(cursor.getInt(cursor.getColumnIndex("ID_BIG_ROCK")));
            bigRock.setNmBigRock(cursor.getString(cursor.getColumnIndex("NM_BIG_ROCK")));
            bigRock.setDsBigRock(cursor.getString(cursor.getColumnIndex("DS_BIG_ROCK")));
            bigRock.setTpStatus(cursor.getString(cursor.getColumnIndex("TP_STATUS")));
            bigRock.setDtInsert(cursor.getLong(cursor.getColumnIndex("DT_INSERT")));
            bigRock.setDtEnd(cursor.getLong(cursor.getColumnIndex("DT_END")));
            bigRock.setNrLat(cursor.getString(cursor.getColumnIndex("NR_LAT")));
            bigRock.setNrLng(cursor.getString(cursor.getColumnIndex("NR_LNG")));

        }

        cursor.close();

        return bigRock;
    }

    public List<BigRock> proccessList(Cursor cursor) {
        List<BigRock> bigRocks = new ArrayList<>();

        while (cursor.moveToNext()) {
            BigRock bigRock = new BigRock();
            bigRock.setIdBigRock(cursor.getInt(cursor.getColumnIndex("ID_BIG_ROCK")));
            bigRock.setNmBigRock(cursor.getString(cursor.getColumnIndex("NM_BIG_ROCK")));
            bigRock.setDsBigRock(cursor.getString(cursor.getColumnIndex("DS_BIG_ROCK")));
            bigRock.setTpStatus(cursor.getString(cursor.getColumnIndex("TP_STATUS")));
            bigRock.setDtInsert(cursor.getLong(cursor.getColumnIndex("DT_INSERT")));
            bigRock.setDtEnd(cursor.getLong(cursor.getColumnIndex("DT_END")));
            bigRock.setNrLat(cursor.getString(cursor.getColumnIndex("NR_LAT")));
            bigRock.setNrLng(cursor.getString(cursor.getColumnIndex("NR_LNG")));
            bigRocks.add(bigRock);
        }

        cursor.close();

        return bigRocks;
    }



}
