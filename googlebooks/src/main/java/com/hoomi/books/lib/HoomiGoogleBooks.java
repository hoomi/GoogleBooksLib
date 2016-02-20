package com.hoomi.books.lib;

import com.hoomi.books.lib.listener.ResponseListener;
import com.hoomi.books.lib.listener.SearchListener;
import com.hoomi.books.lib.model.VolumeDetails;

/**
 * Created by hoomanostovari on 17/02/2016.
 */
public interface HoomiGoogleBooks {
    void searchInTitle(String phraseInTitle, SearchListener responseListener);

    void volumeDetailsFromId(String volumeId, ResponseListener<VolumeDetails> responseListener);

    void searchInTitleFromIndex(String phraseInTitle, int index, int max, SearchListener searchListener);
}
