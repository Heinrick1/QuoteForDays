package com.example.heinrick.quotesfordays;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class DisplayQuoteActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_quote);

        TextView mtv = (TextView) this.findViewById(R.id.tv_quote);

        int testNum = this.getIntent().getIntExtra((getString(R.string.NBQUOTES)).toString(), 1);
        mtv.setText(Integer.toString(testNum));


    }


}
