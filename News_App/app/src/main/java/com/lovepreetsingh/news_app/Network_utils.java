package com.lovepreetsingh.news_app;
import android.net.Uri;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

/**
 * Created by static on 6/19/17.
 */

class NetworkUtils {

    final static String News_URL =
            "https://newsapi.org/v1/articles";

    final static String PARAM_SOURCE="source";
    final static String source="the-next-web";


    final static String PARAM_SORT="sortBy";
    final static String sortby="latest";


    final static String PARAM_API="apiKey";
    final static String apikey="eee9140830c740d58ee10ba36754162b";






    public static URL buildUrl() {




        Uri builtUri = Uri.parse(News_URL).buildUpon()
                .appendQueryParameter(PARAM_SOURCE,source)
                .appendQueryParameter(PARAM_SORT,sortby)
                .appendQueryParameter(PARAM_API,apikey)
                .build();


        URL url = null;
        try {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return url;
    }



    public static String getResponseFromHttpUrl(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            InputStream in = urlConnection.getInputStream();

            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                return scanner.next();
            } else {
                return null;
            }
        } finally {
            urlConnection.disconnect();
        }
    }
}