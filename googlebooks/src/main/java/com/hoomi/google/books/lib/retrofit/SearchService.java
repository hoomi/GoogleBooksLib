package com.hoomi.google.books.lib.retrofit;

import com.hoomi.google.books.lib.model.SearchResult;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 * Created by hoomanostovari on 17/02/2016.
 */
public interface SearchService {

    @GET("/books/v1/volumes")
    Call<SearchResult> searchInTitle(@QueryMap Map<String, String> queryMaps);
}
