package com.example.heinrick.quotesfordays;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;

import static java.security.AccessController.getContext;

public class MainActivity extends AppCompatActivity {

    int mNbOfQuotesRequested;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mNbOfQuotesRequested = getResources().getInteger(R.integer.picker_min);

        //these buttons will trigger the network request and pass it the appropriate URL

        final Button buttonMovieQuote = (Button) findViewById(R.id.buttonMovieQuote);
        final Button buttonFamousQuote =(Button) findViewById(R.id.buttonFamousQuote);

        buttonMovieQuote.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // TODO replace stub intent with a call to the actual network utility in both buttons listeners
                Intent intent = new Intent(MainActivity.this, DisplayQuoteActivity.class);
                intent.putExtra(getString(R.string.NBQUOTES), mNbOfQuotesRequested);
                startActivity(intent);

            }
        });

        buttonFamousQuote.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, DisplayQuoteActivity.class);
                intent.putExtra(getString(R.string.NBQUOTES), mNbOfQuotesRequested);
                startActivity(intent);
            }
        });
        
        //setting up the number picker
        final NumberPicker quoteNbSelector = (NumberPicker) findViewById(R.id.quoteNbSelector);

        quoteNbSelector.setMaxValue(10);
        quoteNbSelector.setMinValue(1);

        quoteNbSelector.setWrapSelectorWheel(true);

        //number picker listener update the member variable for the number of quotes

        //in case picker is not touched



        quoteNbSelector.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal){
                //Display the newly selected number from picker
                mNbOfQuotesRequested = newVal;
            }
        });

    }
}
