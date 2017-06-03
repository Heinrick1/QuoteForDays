package com.example.heinrick.quotesfordays.Utilities;

import com.example.heinrick.quotesfordays.models.Quote;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;

import static com.example.heinrick.quotesfordays.Utilities.JsonUtility.formatJsonInput;
import static junit.framework.Assert.assertNotNull;

/**
 * Created by Heinrick on 2017-06-02.
 */

public class JsonUtilityTest {

    private static String testQuoteList = null;
    private static ArrayList<Quote> quotes = null;


    @Before
    public void fixture(){

        try {
            testQuoteList = NetworkUtilities.getResponse(NetworkUtilities.buildURL("movies", "10"));
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    @Test
    public void formatJsonInputTest (){

        quotes = formatJsonInput(testQuoteList);

        assertNotNull(quotes);

        System.out.println(quotes.toString());
    }

}
