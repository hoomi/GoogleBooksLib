package com.hoomi.books.lib.comms;

import com.hoomi.books.lib.listener.ResponseListener;
import com.hoomi.books.lib.listener.SearchListener;
import com.hoomi.books.lib.model.VolumeDetails;

/**
 * Created by hoomanostovari on 19/02/2016.
 */
public interface NetworkHelper {
    void searchInTitle(String wordInTitle, SearchListener searchListener);

    void volumeDetailsFromId(String volumeId, ResponseListener<VolumeDetails> responseListener);

    void searchInTitleFromIndex(String wordInTitle, int index, int maxResult, SearchListener searchListener);
}
