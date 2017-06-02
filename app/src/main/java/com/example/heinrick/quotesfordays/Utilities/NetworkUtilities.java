package com.example.heinrick.quotesfordays.Utilities;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


/**
 * Created by Heinrick on 2017-05-22.
 */

public class NetworkUtilities {




    //1. Method that builds the url

    public static URL buildURL (String cat, String count) {

        URL url = null;
        //Seems that query parameters must be added to the urlBuilder and not later on !!!!!

        HttpUrl.Builder urlBuilder = HttpUrl.parse("https://andruxnet-random-famous-quotes.p.mashape.com")
                .newBuilder()
                .addQueryParameter("cat", cat)
                .addQueryParameter("count", count);


        try {
            url = new URL(urlBuilder.build().toString());
        }
        catch(MalformedURLException e){
            e.printStackTrace();
        }

        return url;
    }

    //2. Make request
    public static String getResponse (URL url) throws IOException{

        // 1. Create client
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .header("X-Mashape-Key", "19V4ilPyFrmshPb24hQuAM6CK90Up1msT01jsnXBBk7CMaPLpU")
                .header("Content-Type", "application/x-www-form-urlencoded")
                .header("Accept", "application/json")
                .url(url)
                .build();

            Response response = client.newCall(request).execute();

        return response.body().string();
    }






}
