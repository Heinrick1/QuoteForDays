package com.example.heinrick.quotesfordays;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.heinrick.quotesfordays.Utilities.JsonUtility;
import com.example.heinrick.quotesfordays.Utilities.NetworkUtilities;
import com.example.heinrick.quotesfordays.models.Quote;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class DisplayQuoteActivity extends AppCompatActivity {


    private RecyclerView aRecyclerV;
    // COMPLETED (35) Add a private ForecastAdapter variable called mForecastAdapter
    private QfdAdapter aAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_quote);

        String stringQuoteList = getIntent().getStringExtra((getString(R.string.QUOTE_LIST_EXTRA)));

        ArrayList<Quote> quoteList = JsonUtility.formatJsonInput(stringQuoteList);

        //loads recycler view layout

        aAdapter = new QfdAdapter();

        aAdapter.setQuoteList(quoteList);


        aRecyclerV = (RecyclerView) findViewById(R.id.rcv_displayQuote);

        LinearLayoutManager layoutManager
                = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);

        aRecyclerV.setLayoutManager(layoutManager);

        aRecyclerV.setHasFixedSize(true);



        aRecyclerV.setAdapter(aAdapter);


    }

    //TODO make a recycler view that shows the quotes nicely formated with the movie title and
    //the author at the bottom right

    //TODO


}
