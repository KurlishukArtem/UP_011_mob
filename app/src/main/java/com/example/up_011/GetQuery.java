package com.example.up_011;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.util.Properties;

public class GetQuery {
    public interface Inter {
        void returner(String str) throws JSONException;
    }



    public static class GetQueryJsoup extends AsyncTask<Inter, Void, Void> {

        String json = "";

        Inter inter;

        Properties properties = new Properties();

        public static String url;

        private final String APIKEY="eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6InRkbmZ5cnJwZ2lnbm9ncXpta2ZlIiwicm9sZSI6ImFub24iLCJpYXQiOjE3MzA0NjA1NzgsImV4cCI6MjA0NjAzNjU3OH0.ySo_EI3G4mgZsPlCQ1esuW1qme2SbgtzPuYJxh52IwA";

        public GetQueryJsoup(String url){
            GetQueryJsoup.url = url;
        }

        @Override
        protected Void doInBackground(Inter... inters) {
            Document doc = null;
            inter = inters[0];
            try{
                doc = Jsoup
                        .connect(url + APIKEY)
                        .ignoreContentType(true)
                        .header("accept", "*/*")
                        .get();
            }
            catch (Exception e){
                Log.i("ilya",e.getMessage());
            }
            json = doc.text();
            return null;
        }
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
