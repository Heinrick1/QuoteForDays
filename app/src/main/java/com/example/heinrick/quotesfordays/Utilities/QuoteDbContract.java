package com.example.heinrick.quotesfordays.Utilities;

import android.provider.BaseColumns;

/**
 * Created by Heinrick on 2017-08-01.
 */

public class QuoteDbContract {

    // To prevent someone from accidentally instantiating the contract class,
    // make the constructor private.
    private QuoteDbContract() {}

    /* Inner class that defines the table contents */
    public static class QuoteEntry implements BaseColumns {
        public static final String TABLE_NAME = "quoteList";
        public static final String COLUMN_AUTHOR = "quoteAuthor";
        public static final String COLUMN_BODY = "quoteBody";

    }
}

