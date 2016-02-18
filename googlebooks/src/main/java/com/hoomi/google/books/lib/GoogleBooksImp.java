package com.hoomi.google.books.lib;

import com.hoomi.google.books.lib.listener.SearchListener;
import com.hoomi.google.books.lib.model.ErrorModel;
import com.hoomi.google.books.lib.model.GsonFactory;
import com.hoomi.google.books.lib.model.SearchResult;
import com.hoomi.google.books.lib.retrofit.SearchService;

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
class GoogleBooksImp implements GoogleBooks {

    private static final String GOOGLE_BOOKS_BASE_URL = "https://www.googleapis.com";

    private final String apiKey;
    private Retrofit retrofit;
    private SearchService searchService;

    GoogleBooksImp(String apiKey, String baseUrl) {
        this.apiKey = apiKey;
        retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create(GsonFactory.getGson()))
                .build();

        searchService = retrofit.create(SearchService.class);
    }

    public GoogleBooksImp(String apiKey) {
        this(apiKey, GOOGLE_BOOKS_BASE_URL);
    }

    public void searchInTitle(String phraseInTitle, SearchListener responseListener) {
        Map<String, String> queryMap = new HashMap<>();
        queryMap.put("key", apiKey);
        queryMap.put("q", "intitle:" + phraseInTitle);
        Call<SearchResult> call = searchService.searchInTitle(queryMap);
        call.enqueue(new Callback<SearchResult>() {
            @Override
            public void onResponse(Call<SearchResult> call, Response<SearchResult> response) {
                if (responseListener != null) {
                    if (response.isSuccess()) {
                        responseListener.onSuccess(response.body().getVolumes());
                    } else {
                        try {
                            responseListener.onError(new ErrorModel(response.errorBody().string()));
                        } catch (IOException e) {
                            responseListener.onError(new ErrorModel("Something went wrong in the request"));
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<SearchResult> call, Throwable t) {
                if (responseListener != null) {
                    responseListener.onError(new ErrorModel(t.getMessage()));
                }
            }
        });
    }
}
