package com.hoomi.books.lib.model;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by hoomanostovari on 20/02/2016.
 */
public class VolumeDetailsImp implements VolumeDetails {

    private VolumeInfo volumeInfo;

    @Override
    public float getAverageRating() {
        return volumeInfo != null ? volumeInfo.averageRating : -1;
    }

    @Override
    public int getPageCount() {
        return volumeInfo != null ? volumeInfo.pageCount : -1;
    }

    @Override
    public String getDescription() {
        return volumeInfo != null ? volumeInfo.description : "";
    }

    @Override
    public String getTitle() {
        return volumeInfo != null ? volumeInfo.title : "";
    }

    @Override
    public String[] getAuthors() {
        return volumeInfo != null ? volumeInfo.authors : new String[1];
    }

    @Override
    public String getInfoLink() {
        return volumeInfo != null ? volumeInfo.infoLink : "";
    }

    @Override
    public Map<String, String> getImageLinks() {
        return volumeInfo != null ? volumeInfo.imageLinks : new HashMap<String, String>();
    }


    static class VolumeInfo {
        private float averageRating;
        private int pageCount;
        private String description;
        private String title;
        private String[] authors;
        private String infoLink;
        private Map<String, String> imageLinks;
    }
}
