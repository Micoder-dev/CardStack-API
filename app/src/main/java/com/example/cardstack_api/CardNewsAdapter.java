package com.example.cardstack_api;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class CardNewsAdapter extends BaseAdapter {

    private ArrayList<CardNewsModel> newsData;
    private Context context;

    public CardNewsAdapter(ArrayList<CardNewsModel> newsData, Context context) {
        this.newsData = newsData;
        this.context = context;
    }

    @Override
    public int getCount() {
        return newsData.size();
    }

    @Override
    public Object getItem(int position) {
        return newsData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // in get view method we are inflating our layout on below line.
        View v = convertView;
        if (v == null) {
            // on below line we are inflating our layout.
            v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_news_items, parent, false);
        }
        // on below line we are initializing our variables and setting data to our variables.
        ((TextView) v.findViewById(R.id.text_view_title)).setText(newsData.get(position).getTitle());
        ((TextView) v.findViewById(R.id.text_view_desc)).setText(newsData.get(position).getDescription());
        ((TextView) v.findViewById(R.id.text_view_published_date)).setText("Published Date:  "+newsData.get(position).getTime());
        ImageView imageUrl;
        imageUrl = v.findViewById(R.id.news_image);
        Glide.with(context).load(newsData.get(position).getImageUrl()).placeholder(R.drawable.news_logo).into(imageUrl);
        Button readMoreBtn;
        readMoreBtn = v.findViewById(R.id.readMoreBtn);
        CardNewsModel currentItem = newsData.get(position);
        readMoreBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, WebViewActivity.class);
                intent.putExtra("url", currentItem.getUrl());
                context.startActivity(intent);
            }
        });

        return v;
    }
}
