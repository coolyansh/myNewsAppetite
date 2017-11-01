package com.example.rays.apinewsfeed;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by rays on 10/27/2017.
 */

public class NewsDbHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME="news.db";
    public static final int DATABASE_VERSION=1;

    public NewsDbHelper(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE="CREATE TABLE " + NewsContract.TABLE_NAME + " ("
                + NewsContract._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + NewsContract.COLUMN_TITLE + " TEXT NOT NULL, "
                + NewsContract.COLUMN_DESCRIPTION + " TEXT , "
                + NewsContract.COLUMN_URL + " TEXT NOT NULL, "
                + NewsContract.COLUMN_IMG_URL + " TEXT NOT NULL);";
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
