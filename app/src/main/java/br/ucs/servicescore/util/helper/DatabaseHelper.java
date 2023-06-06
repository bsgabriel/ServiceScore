package br.ucs.servicescore.util.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import br.ucs.servicescore.entity.Place;

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
        createTable.append(COL_ID + " TEXT PRIMARY KEY, ");
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

    public void addData(Place place) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_NOME, place.getNome());
        contentValues.put(COL_RATING, place.getAvaliacao());
        contentValues.put(COL_CATEGORIA, place.getCategoria());
        contentValues.put(COL_NUM_REVIEWS, place.getNumAvaliacoes());
        contentValues.put(COL_ENDERECO, place.getEndereco());
        contentValues.put(COL_URL_IMG, place.getUrlImage());
        db.insert(TABLE_NAME, null, contentValues);
    }

    public void addData(List<Place> places) {
        for (Place place : places) {
            addData(place);
        }
    }

    private Place cursorToPlace(Cursor cursor) {
        Place place = new Place();
        place.setId(cursor.getString(0));
        place.setNome(cursor.getString(1));
        place.setAvaliacao(cursor.getFloat(2));
        place.setCategoria(cursor.getString(3));
        place.setNumAvaliacoes(cursor.getInt(4));
        place.setEndereco(cursor.getString(5));
        place.setUrlImage(cursor.getString(6));
        return place;
    }

    public ArrayList<Place> buscarTodos() {
        ArrayList<Place> places = new ArrayList<Place>();
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {
                places.add(cursorToPlace(cursor));
            } while (cursor.moveToNext());
        }
        return places;
    }

    public void deleteAll() {
        this.getWritableDatabase().execSQL("delete from "+ TABLE_NAME);
    }


}