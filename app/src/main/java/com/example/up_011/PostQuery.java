package com.example.up_011;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class PostQuery {
    public interface Inter {
        void returner(String str) throws JSONException;
    }



    public static class PostQueryJsoup extends AsyncTask<Inter, Void, Void> {

        String json = "";

        Inter inter;

        public static String  postData;

        public static String url;

        private final String APIKEY="eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6InRkbmZ5cnJwZ2lnbm9ncXpta2ZlIiwicm9sZSI6ImFub24iLCJpYXQiOjE3MzA0NjA1NzgsImV4cCI6MjA0NjAzNjU3OH0.ySo_EI3G4mgZsPlCQ1esuW1qme2SbgtzPuYJxh52IwA";

        public PostQueryJsoup(String url, String postData){
            PostQueryJsoup.url = url;
            PostQueryJsoup.postData = postData;
        }

        @Override
        protected Void doInBackground(Inter... inters) {
            inter = inters[0];
            try {

                Document doc = Jsoup.connect(url + APIKEY)
                        .ignoreContentType(true)
                        .header("accept", "*/*")
                        .header("Content-Type", "application/x-www-form-urlencoded")
                        .requestBody(postData)
                        .post();

                String json = doc.text();
            } catch (Exception e) {
                Log.i("ilya", e.getMessage());
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void unused) {
            super.onPostExecute(unused);

            try {
                inter.returner(json);
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
