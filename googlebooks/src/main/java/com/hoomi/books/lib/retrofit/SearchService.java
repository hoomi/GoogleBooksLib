package com.hoomi.books.lib.retrofit;

import com.hoomi.books.lib.model.SearchResult;
import com.hoomi.books.lib.model.VolumeDetails;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.HEAD;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;

/**
 * Created by hoomanostovari on 17/02/2016.
 */
public interface SearchService {

    @GET("/books/v1/volumes")
    Call<SearchResult> searchInTitle(@QueryMap Map<String, String> queryMaps);

    @GET("/books/v1/volumes/{volumeId}")
    Call<VolumeDetails> getVolumeDetails(@Path("volumeId") String volumeId);
}
