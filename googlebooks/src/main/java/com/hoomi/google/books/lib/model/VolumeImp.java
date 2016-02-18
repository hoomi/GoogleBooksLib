package com.hoomi.google.books.lib.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by hoomanostovari on 17/02/2016.
 */
class VolumeImp implements Volume, CommonModelInterface {
    private String id;
    private String selfLink;
    private VolumeInfo volumeInfo;

    public String getId() {
        return id;
    }

    public String getSelfLink() {
        return selfLink;
    }

    @Override
    public String getTitle() {
        return volumeInfo == null ? "" : volumeInfo.title;
    }

    @Override
    public String getDescription() {
        return volumeInfo == null ? "" : volumeInfo.description;
    }

    @Override
    public List<String> getAuthors() {
        return volumeInfo == null ? new ArrayList<>() : volumeInfo.authors;
    }

    @Override
    public Map<String, String> getImageLinks() {
        return volumeInfo == null ? new HashMap<>() : volumeInfo.imageLinks;
    }

    private static class VolumeInfo {
        private String title;
        private String description;
        private List<String> authors;
        private Map<String, String> imageLinks;
    }
}
