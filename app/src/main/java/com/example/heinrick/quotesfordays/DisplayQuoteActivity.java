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

        String result = "nothing happened";

        URL url = NetworkUtilities.buildURL("movies","10");
        try {
            result = NetworkUtilities.getResponse(url);
        }
        catch(IOException e){
            e.printStackTrace();
        }

        int testNum = this.getIntent().getIntExtra((getString(R.string.NBQUOTES)).toString(), 1);
        mtv.setText(result);


    }


}
