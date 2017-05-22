package br.eti.rodrigosiqueira.bigrock.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.util.ArrayList;
import java.util.List;

import br.eti.rodrigosiqueira.bigrock.model.BigRockEnd;

/**
 * Created by luiz.massa on 15/05/17.
 */

public class BigRockEndDAO extends BaseDAO {

    private String tableName = "big_rock_end";

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createDataBase = "CREATE TABLE IF NOT EXISTS BIG_ROCK_END( ID_BIG_ROCK_END INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, ID_BIG_ROCK INTEGER NOT NULL, DT_END DOUBLE, NR_LAT VARCHAR(100), NR_LNG VARCHAR(100))";
        db.execSQL(createDataBase);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String dropDataBase = "DROP TABLE IF EXISTS BIG_ROCK_END";
        db.execSQL(dropDataBase);
        onCreate(db);
    }

    public BigRockEndDAO(Context context) {
        super(context);
    }


    public BigRockEnd getBigRockEnd(Integer idBigRockEnd) {
       return proccessRow((Cursor) getItemParam("SELECT * FROM "+tableName+" WHERE id_big_rock_end = ?", new String[]{idBigRockEnd.toString()}));
    }


    public List<BigRockEnd> getBigRocksEnd() {
        return  proccessList((Cursor) getList("SELECT * FROM "+tableName));
    }


    public BigRockEnd insertBigRockEnd(BigRockEnd bigRockEnd) {


        ContentValues contentValues = new ContentValues();
        contentValues.put("ID_BIG_ROCK", bigRockEnd.getIdBigRock());
        contentValues.put("DT_END", bigRockEnd.getDtEnd().toString());
        contentValues.put("NR_LAT", bigRockEnd.getNrLat());
        contentValues.put("NR_LNG", bigRockEnd.getNrLng());

        Integer idBigRockEnd = insertItem(tableName, contentValues);

        bigRockEnd = getBigRockEnd(idBigRockEnd);

        return bigRockEnd;
    }

    public BigRockEnd updateBigRockEnd(BigRockEnd bigRockEnd) {

        ContentValues contentValues = new ContentValues();
        contentValues.put("ID_BIG_ROCK", bigRockEnd.getIdBigRock());
        contentValues.put("DT_END", bigRockEnd.getDtEnd().toString());
        contentValues.put("NR_LAT", bigRockEnd.getNrLat());
        contentValues.put("NR_LNG", bigRockEnd.getNrLng());

        String[] params = {bigRockEnd.getIdBigRock().toString()};
        String where = "id_big_rock = ?";
        int update = updateItem(tableName, contentValues, where, params);

        if(update > 0) {
            bigRockEnd = getBigRockEnd(bigRockEnd.getIdBigRockEnd());
        } else {
            bigRockEnd = new BigRockEnd();
        }

        return bigRockEnd;
    }

    public Boolean deleteBigRockEnd(BigRockEnd bigRockEnd) {

        String[] params = {bigRockEnd.getIdBigRock().toString()};
        String where = "id_big_rock_end = ?";
        int delete = deleteItem(tableName, where, params);
        if(delete > 0) {
            return true;
        } else {
            return  false;
        }
    }


    public BigRockEnd proccessRow(Cursor cursor) {
        BigRockEnd bigRockEnd = new BigRockEnd();

        while (cursor.moveToNext()) {

            bigRockEnd.setIdBigRockEnd(cursor.getInt(cursor.getColumnIndex("ID_BIG_ROCK_END")));
            bigRockEnd.setIdBigRock(cursor.getInt(cursor.getColumnIndex("ID_BIG_ROCK")));
            bigRockEnd.setDtEnd(cursor.getLong(cursor.getColumnIndex("DT_END")));
            bigRockEnd.setNrLng(cursor.getString(cursor.getColumnIndex("NR_LAT")));
            bigRockEnd.setNrLat(cursor.getString(cursor.getColumnIndex("NR_LNG")));

        }

        cursor.close();

        return bigRockEnd;
    }

    public List<BigRockEnd> proccessList(Cursor cursor) {
        List<BigRockEnd> bigRocksEnd = new ArrayList<>();

        while (cursor.moveToNext()) {
            BigRockEnd bigRockEnd = new BigRockEnd();
            bigRockEnd.setIdBigRockEnd(cursor.getInt(cursor.getColumnIndex("ID_BIG_ROCK_END")));
            bigRockEnd.setIdBigRock(cursor.getInt(cursor.getColumnIndex("ID_BIG_ROCK")));
            bigRockEnd.setDtEnd(cursor.getLong(cursor.getColumnIndex("DT_END")));
            bigRockEnd.setNrLng(cursor.getString(cursor.getColumnIndex("NR_LAT")));
            bigRockEnd.setNrLat(cursor.getString(cursor.getColumnIndex("NR_LNG")));
            bigRocksEnd.add(bigRockEnd);
        }

        cursor.close();

        return bigRocksEnd;
    }



}
