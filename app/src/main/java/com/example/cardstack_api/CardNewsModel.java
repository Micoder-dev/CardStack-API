package com.example.cardstack_api;

public class CardNewsModel {

    private String mTitle, mDescription, mTime, mImageUrl, mUrl;

    public CardNewsModel(String title, String description, String time, String imageUrl, String url) {
        mTitle = title;
        mDescription = description;
        mTime = time;
        mImageUrl = imageUrl;
        mUrl = url;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getDescription() {
        return mDescription;
    }

    public String getTime() {
        return mTime;
    }

    public String getImageUrl() {
        return mImageUrl;
    }

    public String getUrl() {
        return mUrl;
    }
}
