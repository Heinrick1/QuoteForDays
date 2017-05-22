package com.example.heinrick.quotesfordays;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //these buttons will trigger the network request and pass it the appropriate URL

        final Button buttonMovieQUote = (Button)findViewById(R.id.buttonMovieQuote);
        final Button buttonFamousQuote = (Button)findViewById(R.id.buttonFamousQuote);

        buttonMovieQUote.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // TODO replace stub intent with a call to the actual network utility in both buttons listeners
                Intent intent = new Intent(MainActivity.this, DisplayQuoteActivity.class);
                startActivity(intent);

            }
        });

        buttonFamousQuote.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, DisplayQuoteActivity.class);
                startActivity(intent);
            }
        });
        
        //setting up the number picker
        final NumberPicker quoteNbSelector = (NumberPicker) findViewById(R.id.quoteNbSelector);

        quoteNbSelector.setMaxValue(10);
        quoteNbSelector.setMinValue(1);


        
        

    }
}
