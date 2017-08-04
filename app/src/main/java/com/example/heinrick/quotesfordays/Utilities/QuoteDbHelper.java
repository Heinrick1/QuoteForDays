package com.example.heinrick.quotesfordays.Utilities;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.heinrick.quotesfordays.Utilities.QuoteDbContract.QuoteEntry;

/**
 * Created by Heinrick on 2017-08-01.
 */

public class QuoteDbHelper extends SQLiteOpenHelper {
        // If you change the database schema, you must increment the database version.
        public static final int DATABASE_VERSION = 1;
        public static final String DATABASE_NAME = "QuoteList.db";

        public QuoteDbHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }
        public void onCreate(SQLiteDatabase db) {
            final String SQL_CREATE_QUOTE_TABLE = "CREATE TABLE " +
                    QuoteEntry.TABLE_NAME + "(" +
                    QuoteEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    QuoteEntry.COLUMN_AUTHOR + " STRING NON NULL, " +
                    QuoteEntry.COLUMN_BODY + " STRING NON NULL" + ");";




            db.execSQL(SQL_CREATE_QUOTE_TABLE);
        }
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            // This database is only a cache for online data, so its upgrade policy is
            // to simply to discard the data and start over
            db.execSQL("DROP IF TABLE EXISTS" + QuoteEntry.TABLE_NAME);
            onCreate(db);
        }
        public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            onUpgrade(db, oldVersion, newVersion);
        }

}
