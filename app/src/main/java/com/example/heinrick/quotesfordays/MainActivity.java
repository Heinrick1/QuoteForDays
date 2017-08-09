package com.example.heinrick.quotesfordays;

import android.content.Intent;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;

import com.example.heinrick.quotesfordays.Utilities.JsonUtility;
import com.example.heinrick.quotesfordays.Utilities.NetworkUtilities;
import com.example.heinrick.quotesfordays.models.Quote;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<String> {

    int mNbOfQuotesRequested;

    //int constant for loader ID
    private static final int QUOTE_FOR_DAYS_GEN_LOADER = 11;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // TODO implement an AsynctaskLoader for the network part
        // TODO implement a recyclerview to display result in the displayquote Activity
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mNbOfQuotesRequested = getResources().getInteger(R.integer.nb_quotes_requested);

        //these buttons will trigger the network request and pass it the appropriate URL

        final Button buttonMovieQuote = (Button) findViewById(R.id.buttonMovieQuote);
        final Button buttonFamousQuote =(Button) findViewById(R.id.buttonFamousQuote);
        final Button buttonQuoteList = (Button) findViewById(R.id.buttonList);

        buttonMovieQuote.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                getQuotesFromServer("movie", mNbOfQuotesRequested );
            }
        });

        buttonFamousQuote.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                getQuotesFromServer("famous", mNbOfQuotesRequested );
            }
        });

        buttonQuoteList.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, DisplayQuoteActivity.class);
                intent.putExtra("calling-activity", ActivityConstants.ACTIVITY_LIST);
                startActivity(intent);
            }
        });
        
        //setting up the number picker
//        final NumberPicker quoteNbSelector = (NumberPicker) findViewById(R.id.quoteNbSelector);
//
//        quoteNbSelector.setMaxValue(10);
//        quoteNbSelector.setMinValue(1);
//
//        quoteNbSelector.setWrapSelectorWheel(true);

        //number picker listener update the member variable for the number of quotes

//        in case picker is not touched



//        quoteNbSelector.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
//            @Override
//            public void onValueChange(NumberPicker picker, int oldVal, int newVal){
//                //Display the newly selected number from picker
//                mNbOfQuotesRequested = newVal;
//            }
//        });

    }

    //creates a new loader
    @Override
    public Loader<String> onCreateLoader(int id, final Bundle args) {

        return new AsyncTaskLoader<String>(this) {

            //field to cache my data
            String quoteList = null;


            @Override
            protected void onStartLoading() {
                super.onStartLoading();
                if (args == null){
                    return;
                }
                else{
                    forceLoad();
                }
            }


            /*returns the result of the load to the listener*/
            public void deliverResult(String list){
                quoteList = list;
                super.deliverResult(list);
            }
            //the loader makes the network call on it's thread
            @Override
            public String loadInBackground() {

                String stringUrl = args.getString("URL_EXTRA");

                if(stringUrl == null) return null;

                try {
                    //converts string url with get request to URL object
                    URL url = new URL(stringUrl);

                    //fetches response from server and returns the String
                    return NetworkUtilities.getResponse(url);




                }
                catch(IOException e){
                    e.printStackTrace();
                    return null;
                }

            }
        };
    }

    //What happends when the loader is done with doInBackground, the data has been fetched from the server
    @Override
    public void onLoadFinished(Loader<String> loader, String data) {
        ArrayList<Quote> quoteList = JsonUtility.formatJsonInput(data);

         Intent intent = new Intent(MainActivity.this, DisplayQuoteActivity.class);
                intent.putExtra(getString(R.string.QUOTE_LIST_EXTRA), quoteList);
                intent.putExtra("calling-activity", ActivityConstants.ACTIVITY_DISP);
                startActivity(intent);
    }

    @Override
    public void onLoaderReset(Loader<String> loader) {

    }

    /*initializes and calls the loader
    * */
    public void getQuotesFromServer(String category, int count){

        //building the URL
        URL url = NetworkUtilities.buildURL(category, Integer.toString(count));

        //Bundle for the loader, containing the already built url
        Bundle queryBundle  = new Bundle();
        queryBundle.putString("URL_EXTRA", url.toString());

        LoaderManager loaderManager = getSupportLoaderManager();
        Loader<String> quoteLoader = loaderManager.getLoader(QUOTE_FOR_DAYS_GEN_LOADER);

        loaderManager.restartLoader(QUOTE_FOR_DAYS_GEN_LOADER, queryBundle, this);

    }

    public interface ActivityConstants {
        public static final int ACTIVITY_DISP = 1001;
        public static final int ACTIVITY_LIST = 1002;
        public static final int ACTIVITY_3 = 1003;
    }
}
