package com.example.heinrick.quotesfordays;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.heinrick.quotesfordays.Utilities.NetworkUtilities;

import java.io.IOException;
import java.net.URL;

public class DisplayQuoteActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_quote);

        TextView mtv = (TextView) this.findViewById(R.id.tv_quote);

        String test = getIntent().getStringExtra((getString(R.string.QUOTE_LIST_EXTRA)));
        mtv.setText(test);


    }


}
