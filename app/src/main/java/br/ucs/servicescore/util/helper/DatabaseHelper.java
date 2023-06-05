package br.ucs.servicescore.util.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "yelp_ucs";
    public static final String TABLE_NAME = "yelp_search";
    public static final String COL_ID = "id";
    public static final String COL_NOME = "nome";
    public static final String COL_RATING = "rating";
    public static final String COL_CATEGORIA = "categoria";
    public static final String COL_NUM_REVIEWS = "num_reviews";
    public static final String COL_ENDERECO = "endereco";
    public static final String COL_URL_IMG = "url_img";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        StringBuilder createTable = new StringBuilder();
        createTable.append("CREATE TABLE " + TABLE_NAME + "(");
        createTable.append(COL_ID + " ID INTEGER PRIMARY KEY AUTOINCREMENT, ");
        createTable.append(COL_NOME + " TEXT, ");
        createTable.append(COL_RATING + " INTEGER, ");
        createTable.append(COL_CATEGORIA + " TEXT, ");
        createTable.append(COL_NUM_REVIEWS + " INTEGER, ");
        createTable.append(COL_ENDERECO + " TEXT, ");
        createTable.append(COL_URL_IMG + " TEXT)");
        db.execSQL(createTable.toString());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
    }

    public boolean addData(String nome, Long rating, String categoria, Integer numReviews, String endereco, String urlImage) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_NOME, nome);
        contentValues.put(COL_RATING, rating);
        contentValues.put(COL_CATEGORIA, categoria);
        contentValues.put(COL_NUM_REVIEWS, numReviews);
        contentValues.put(COL_ENDERECO, endereco);
        contentValues.put(COL_URL_IMG, urlImage);

        long result = db.insert(TABLE_NAME, null, contentValues);

        return result != -1;
    }

    public Cursor getListContects() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor data = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        return data;
    }

    public void onDelete(SQLiteDatabase db) {
        db.execSQL("DELETE FROM " + TABLE_NAME);
    }


}