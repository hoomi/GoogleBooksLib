package com.hoomi.google.books.lib.model;

import java.util.List;
import java.util.Map;

/**
 * Created by hoomanostovari on 17/02/2016.
 */
public interface Volume extends CommonModelInterface {
    String getId();
    String getSelfLink();
    String getTitle();
    String getDescription();
    List<String> getAuthors();
    Map<String, String> getImageLinks();


}
