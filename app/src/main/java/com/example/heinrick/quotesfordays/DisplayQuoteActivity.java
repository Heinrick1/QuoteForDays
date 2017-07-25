package com.example.heinrick.quotesfordays;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.heinrick.quotesfordays.Utilities.JsonUtility;
import com.example.heinrick.quotesfordays.models.Quote;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class DisplayQuoteActivity extends FragmentActivity {

    //ArrayList of retrieved and serialized quotes
    ArrayList<Quote> aQuotes = new ArrayList<Quote>();

    private ViewPager mPager;

    //Pager adapter provides pages to viewpager
    private QfdSwipeAdapter mPagerAdapter;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //each of the fragment will use this quote view
        setContentView(R.layout.activity_display_quote);

        // Instantiate a ViewPager and a PagerAdapter.
        mPager = (ViewPager) findViewById(R.id.viewpager);

        mPagerAdapter = new QfdSwipeAdapter(this);

        //retrieve quotes from start intent
        String stringQuoteList = getIntent().getStringExtra((getString(R.string.QUOTE_LIST_EXTRA)));
        //deserializing the quotes using the utility
        ArrayList<Quote> quoteList = JsonUtility.formatJsonInput(stringQuoteList);
        //storing them in the viewpager's input
        mPagerAdapter.setQuoteList(quoteList);
        mPager.setAdapter(mPagerAdapter);


    }


    public void setQuoteList (ArrayList<Quote> quotes){
        aQuotes = quotes;
    }

}


