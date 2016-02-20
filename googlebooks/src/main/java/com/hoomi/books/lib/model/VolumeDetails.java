package com.hoomi.books.lib.model;

import java.util.Map;

/**
 * Created by hoomanostovari on 20/02/2016.
 */
public interface VolumeDetails extends CommonModelInterface {
    float getAverageRating();

    int getPageCount();

    String getDescription();

    String getTitle();

    String[] getAuthors();

    String getInfoLink();

    Map<String, String> getImageLinks();
}
