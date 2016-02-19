package com.hoomi.books.lib;

import com.hoomi.books.lib.listener.SearchListener;

/**
 * Created by hoomanostovari on 17/02/2016.
 */
public interface HoomiGoogleBooks {
    void searchInTitle(String phraseInTitle, SearchListener responseListener);
}
