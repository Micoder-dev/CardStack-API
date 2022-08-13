package com.example.cardstack_api;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.daprlabs.cardstack.SwipeDeck;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private SwipeDeck cardStack;
    private ArrayList<CardNewsModel> newsModalArrayList;
    private RequestQueue mRequestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRequestQueue = Volley.newRequestQueue(this);

        // on below line we are initializing our array list and swipe deck.
        newsModalArrayList = new ArrayList<>();
        cardStack = (SwipeDeck) findViewById(R.id.swipe_deck);

        String url = "https://newsapi.in/newsapi/news.php?key=TknEPxyTgZmRu74bg4VyU00YHl4h29&category=tamil_state";

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            JSONArray jsonArray = response.getJSONArray("News");

                            for (int i = 0; i < jsonArray.length(); i++) {

                                JSONObject news = jsonArray.getJSONObject(i);

                                String title = news.getString("title");
                                String imageUrl = news.getString("image");
                                String description = news.getString("description");
                                String time = news.getString("published_date");
                                String url = news.getString("url");

                                // on below line we are adding data to our array list.
                                newsModalArrayList.add(new CardNewsModel(title, description, time, imageUrl, url));

                                // on below line we are creating a variable for our adapter class and passing array list to it.
                                final CardNewsAdapter adapter = new CardNewsAdapter(newsModalArrayList, MainActivity.this);

                                // on below line we are setting adapter to our card stack.
                                cardStack.setAdapter(adapter);

                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        mRequestQueue.add(request);


    }
}