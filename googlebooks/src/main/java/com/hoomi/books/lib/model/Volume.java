package com.hoomi.books.lib.model;

import java.util.List;
import java.util.Map;

/**
 * Created by hoomanostovari on 17/02/2016.
 */
public interface Volume extends CommonModelInterface {
    String KEY_SMALL_THUMBNAIL="smallThumbnail";
    String KEY_THUMBNAIL="thumbnail";
    String getId();
    String getSelfLink();
    String getTitle();
    String getDescription();
    List<String> getAuthors();
    Map<String, String> getImageLinks();


}
