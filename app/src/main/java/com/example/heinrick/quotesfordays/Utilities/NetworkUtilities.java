package com.example.heinrick.quotesfordays.Utilities;

import com.squareup.okhttp.HttpUrl;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;

/**
 * Created by Heinrick on 2017-05-22.
 */

public class NetworkUtilities {


    // 1. Create client
    OkHttpClient client = new OkHttpClient();

    //2. Build URL
    HttpUrl.Builder urlBuilder = HttpUrl.parse("").newBuilder();
    urlBuilder.addQueryParameter("v", "1.0");
    urlBuilder.addQueryParameter("user", "vogella");
    String url = urlBuilder.build().toString();
    //3. Make request
    Request request = new Request.Builder()
            .url("https://andfun-weather.udacity.com/staticweather")
            .build();



}
