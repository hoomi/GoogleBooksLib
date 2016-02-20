package com.hoomi.books.lib;

import com.hoomi.books.lib.comms.NetworkHelper;
import com.hoomi.books.lib.comms.NetworkHelperImp;
import com.hoomi.books.lib.listener.SearchListener;
import com.hoomi.books.lib.model.ErrorModel;
import com.hoomi.books.lib.model.GsonFactory;
import com.hoomi.books.lib.model.SearchResult;
import com.hoomi.books.lib.retrofit.SearchService;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by hoomanostovari on 17/02/2016.
 */
public class HoomiGoogleBooksImp implements HoomiGoogleBooks {

    private NetworkHelper networkHelper;

    HoomiGoogleBooksImp(NetworkHelper networkHelper) {
        this.networkHelper = networkHelper;
    }

    public HoomiGoogleBooksImp(String apiKey) {
        this(new NetworkHelperImp(apiKey, Constants.GOOGLE_BASE_URL));
    }

    public void searchInTitle(String phraseInTitle, final SearchListener responseListener) {
        networkHelper.searchInTitle(phraseInTitle, responseListener);
    }
}
