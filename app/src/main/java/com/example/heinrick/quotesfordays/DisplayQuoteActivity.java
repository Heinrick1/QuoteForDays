package com.example.heinrick.quotesfordays;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.heinrick.quotesfordays.Utilities.JsonUtility;
import com.example.heinrick.quotesfordays.models.Quote;

import java.util.ArrayList;

public class DisplayQuoteActivity extends AppCompatActivity {


    private ViewPager aPager;

    private QfdSwipeAdapter aAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_display_quote);

        String stringQuoteList = getIntent().getStringExtra((getString(R.string.QUOTE_LIST_EXTRA)));

        ArrayList<Quote> quoteList = JsonUtility.formatJsonInput(stringQuoteList);

        //loads recycler view layout

        aPager = (ViewPager) findViewById(R.id.viewpager);
        aAdapter = new QfdSwipeAdapter(this);



        aPager.setAdapter(aAdapter);
        aAdapter.setQuoteList(quoteList);



    }

    //TODO make a recycler view that shows the quotes nicely formated with the movie title and
    //the author at the bottom right

    //TODO


}
