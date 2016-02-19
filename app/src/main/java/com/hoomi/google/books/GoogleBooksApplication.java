package com.hoomi.google.books;

import android.app.Application;

import com.squareup.picasso.OkHttpDownloader;
import com.squareup.picasso.Picasso;

/**
 * Created by hoomanostovari on 18/02/2016.
 */
public class GoogleBooksApplication extends Application {

    private static final int CACHE_SIZE = 20 * 1024 * 1024;

    @Override
    public void onCreate() {
        super.onCreate();
        init();
    }

    private void init() {
        Picasso.Builder builder = new Picasso.Builder(this);
        Picasso built = builder.build();
        built.setIndicatorsEnabled(true);
        built.setLoggingEnabled(BuildConfig.DEBUG);
        Picasso.setSingletonInstance(built);
    }
}
