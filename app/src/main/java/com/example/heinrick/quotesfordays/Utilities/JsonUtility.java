package com.example.heinrick.quotesfordays.Utilities;

import com.example.heinrick.quotesfordays.models.Quote;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;

/**
 * Created by Heinrick on 2017-06-02.
 */


public class JsonUtility {

    //parses the Json array of quotes and returns a quote arrayList

    /*Takes a Json input String and formats it into an ArrayList of Quote objects
    * @param    - String JsonInput: Json String
    * @return   - ArrayList<Quotes>*/

    public static ArrayList<Quote> formatJsonInput (String JsonInput){

        ArrayList<Quote> quotes = new ArrayList<Quote>();

        try {

            JsonReader reader = new JsonReader(new StringReader(JsonInput));
            Gson gson = new GsonBuilder().create();
            reader.beginArray();

            while (reader.hasNext()) {

                Quote quote = gson.fromJson(reader, Quote.class);
                quotes.add(quote);
            }
            reader.close();
        }

        catch(IOException e){
            e.printStackTrace();
        }
        return quotes;

    }

}
