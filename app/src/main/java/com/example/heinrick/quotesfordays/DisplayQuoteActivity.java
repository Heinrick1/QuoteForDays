package com.example.heinrick.quotesfordays;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.heinrick.quotesfordays.Utilities.JsonUtility;
import com.example.heinrick.quotesfordays.Utilities.QuoteDbContract;
import com.example.heinrick.quotesfordays.Utilities.QuoteDbHelper;
import com.example.heinrick.quotesfordays.models.Quote;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class DisplayQuoteActivity extends AppCompatActivity {

    //ArrayList of retrieved and serialized quotes
    ArrayList<Quote> aQuotes = new ArrayList<Quote>();

    private ViewPager mPager;

    //Pager adapter provides pages to viewpager
    private QfdSwipeAdapter mPagerAdapter;

    private SQLiteDatabase aDb;

    int callingActivity;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //each of the fragment will use this quote view
        setContentView(R.layout.activity_display_quote);

        // Instantiate a ViewPager and a PagerAdapter.
        mPager = (ViewPager) findViewById(R.id.viewpager);

        mPagerAdapter = new QfdSwipeAdapter(this);

       // ArrayList<Quote> quoteList = new ArrayList<Quote>();

        callingActivity = getIntent().getIntExtra("calling-activity", 0);


        //opens access to database
        QuoteDbHelper dbHelper = new QuoteDbHelper(this);
        aDb = dbHelper.getWritableDatabase();


        switch (callingActivity) {
            //ACTIVITY HERE IS USED TO DISPLAY NEW QUOTES
            case MainActivity.ActivityConstants.ACTIVITY_DISP:
                // The activity is then used to display new quotes
                //The intent carries the arrayList of quotes that will be displayed
                aQuotes = (ArrayList<Quote>) getIntent().getSerializableExtra(getString(R.string.QUOTE_LIST_EXTRA));

                //storing them in the viewpager's input
                break;

            //ACTIVITY HERE IS USED TO DISPLAY THE LIST OF QUOTES
            case MainActivity.ActivityConstants.ACTIVITY_LIST:
                // here the activity is loaded with results from the database
                Cursor cursor = getAllQuotes();

                int position = 0;

                while (cursor.moveToPosition(position)){
                    String author = cursor.getString(cursor.getColumnIndex(QuoteDbContract.QuoteEntry.COLUMN_AUTHOR));
                    String body = cursor.getString(cursor.getColumnIndex(QuoteDbContract.QuoteEntry.COLUMN_BODY));

                    aQuotes.add(new Quote(body,author));
                    position++;
                }


                break;
        }

        mPagerAdapter.setQuoteList(aQuotes);
        mPager.setAdapter(mPagerAdapter);

    }

    private Cursor getAllQuotes (){
        return aDb.query(
                QuoteDbContract.QuoteEntry.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                QuoteDbContract.QuoteEntry.COLUMN_AUTHOR
        );
    }


  //  public void setQuoteList (ArrayList<Quote> quotes){
   //     aQuotes = quotes;
    // }

    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);

        // the 2 versions of this activity have different option menu layouts
        switch (callingActivity){
            case MainActivity.ActivityConstants.ACTIVITY_DISP:
                getMenuInflater().inflate(R.menu.displayed_quote_menu, menu);
                return true;
            case MainActivity.ActivityConstants.ACTIVITY_LIST:
                getMenuInflater().inflate(R.menu.quote_list_menu, menu);
                return true;
        }

        return true;

    }

    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection

        switch (item.getItemId()) {
            case R.id.saveItem:

                Quote current = mPagerAdapter.getQuote(mPager.getCurrentItem());
                addNewQuote(current.getAuthor(), current.getBody());
                return true;
            case R.id.shareItem:
                //showHelp();
                return true;
            //eventually replace with a side bar menu
            case R.id.listItem:
               // Intent startIntent = getIntent();
                Intent startIntent = new Intent(DisplayQuoteActivity.this, DisplayQuoteActivity.class);
                //intent.putExtra(getString(R.string.QUOTE_LIST_EXTRA), aQuotes);
                startIntent.putExtra("calling-activity", MainActivity.ActivityConstants.ACTIVITY_LIST);
                startActivity(startIntent);
                return true;

            case R.id.deleteItem:

                Quote currentDel = mPagerAdapter.getQuote(mPager.getCurrentItem());
                removeQuote2(currentDel.getBody());

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public long addNewQuote (String author, String body){

        ContentValues cv = new ContentValues();
        cv.put(QuoteDbContract.QuoteEntry.COLUMN_AUTHOR, author);
        cv.put(QuoteDbContract.QuoteEntry.COLUMN_BODY, body);
        return aDb.insert(QuoteDbContract.QuoteEntry.TABLE_NAME, null, cv);
    }

    private void removeQuote (String body) {
        aDb.execSQL("DELETE FROM " + QuoteDbContract.QuoteEntry.TABLE_NAME
                + " WHERE " + QuoteDbContract.QuoteEntry.COLUMN_BODY + " = ' " + body + " ' " );
    }

    private void removeQuote2 (String body) {
        aDb.delete(QuoteDbContract.QuoteEntry.TABLE_NAME, "quoteBody = ?", new String[] { body });
    }

}


