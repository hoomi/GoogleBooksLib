package com.hoomi.books.lib.comms;

import com.hoomi.books.lib.listener.ResponseListener;
import com.hoomi.books.lib.listener.SearchListener;
import com.hoomi.books.lib.model.ErrorModel;
import com.hoomi.books.lib.model.GsonFactory;
import com.hoomi.books.lib.model.SearchResult;
import com.hoomi.books.lib.model.VolumeDetails;
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
 * Created by hoomanostovari on 19/02/2016.
 */
public class NetworkHelperImp implements NetworkHelper {

    private final String apiKey;
    private Retrofit retrofit;
    private SearchService searchService;

    public NetworkHelperImp(String apiKey, String baseUrl) {
        this.apiKey = apiKey;
        retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create(GsonFactory.getGson()))
                .build();

        searchService = retrofit.create(SearchService.class);
    }

    @Override
    public void searchInTitle(String wordInTitle, final SearchListener searchListener) {
        Map<String, String> queryMap = new HashMap<String, String>();
        queryMap.put("key", apiKey);
        queryMap.put("q", "intitle:" + wordInTitle);
        Call<SearchResult> call = searchService.searchInTitle(queryMap);
        call.enqueue(new Callback<SearchResult>() {
            @Override
            public void onResponse(Call<SearchResult> call, Response<SearchResult> response) {
                if (searchListener != null) {
                    if (response.isSuccess()) {
                        searchListener.onSuccess(response.body().getVolumes());
                    } else {
                        try {
                            searchListener.onError(new ErrorModel(response.errorBody().string()));
                        } catch (IOException e) {
                            searchListener.onError(new ErrorModel("Something went wrong in the request"));
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<SearchResult> call, Throwable t) {
                if (searchListener != null) {
                    searchListener.onError(new ErrorModel(t.getMessage()));
                }
            }
        });
    }

    @Override
    public void programDetailsFromId(String volumeId, final ResponseListener<VolumeDetails> responseListener) {
        Call<VolumeDetails> volumeDetailsCall = searchService.getVolumeDetails(volumeId);
        volumeDetailsCall.enqueue(new Callback<VolumeDetails>() {
            @Override
            public void onResponse(Call<VolumeDetails> call, Response<VolumeDetails> response) {
                if (responseListener != null) {
                    if (response.isSuccess()) {
                        responseListener.onSuccess(response.body());
                    } else {
                        try {
                            responseListener.onError(new ErrorModel(response.errorBody().string()));
                        } catch (IOException e) {
                            e.printStackTrace();
                            responseListener.onError(new ErrorModel("Something went wrong"));
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<VolumeDetails> call, Throwable t) {
                if (responseListener != null) {
                    responseListener.onError(new ErrorModel(t.getMessage()));
                }
            }
        });
    }
}
