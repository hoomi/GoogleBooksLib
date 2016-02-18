package com.hoomi.google.books.lib.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by hoomanostovari on 17/02/2016.
 */
public class SearchResult {
    @SerializedName("items")
    private List<Volume> volumes;

    public List<Volume> getVolumes() {
        return volumes;
    }
}
