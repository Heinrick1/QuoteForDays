package com.example.heinrick.quotesfordays.Utilities;

/**
 * Created by Heinrick on 2017-06-02.
 */

import android.util.Log;

import org.junit.Test;

import java.io.IOException;
import java.io.SequenceInputStream;

import static org.junit.Assert.*;


public class NetworkUtilitiesTest {

    @Test
    public void buildURLTest () {

        String testURL =  NetworkUtilities.buildURL("movies", "10").toString();
        assertEquals(testURL,"https://andruxnet-random-famous-quotes.p.mashape.com/?cat=movies&count=10" );

    }

    @Test
    public void testRequest (){
        String testResponse = null;
        try {
            testResponse = NetworkUtilities.getResponse(NetworkUtilities.buildURL("movies", "10"));
        }
        catch(IOException e){
            e.printStackTrace();
        }

        System.out.println(testResponse);
        assertNotNull(testResponse);

    }

}
