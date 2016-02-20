package com.hoomi.books.lib;

import com.hoomi.books.lib.comms.NetworkHelper;
import com.hoomi.books.lib.comms.NetworkHelperImp;
import com.hoomi.books.lib.listener.ResponseListener;
import com.hoomi.books.lib.listener.SearchListener;
import com.hoomi.books.lib.model.VolumeDetails;

/**
 * Created by hoomanostovari on 17/02/2016.
 */
public class HoomiGoogleBooksImp implements HoomiGoogleBooks {

    private NetworkHelper networkHelper;

    HoomiGoogleBooksImp(NetworkHelper networkHelper) {
        this.networkHelper = networkHelper;
    }

    public HoomiGoogleBooksImp(String apiKey) {
        this(new NetworkHelperImp(apiKey, Constants.GOOGLE_BASE_URL));
    }

    public void searchInTitle(String phraseInTitle, final SearchListener responseListener) {
        networkHelper.searchInTitle(phraseInTitle, responseListener);
    }

    @Override
    public void volumeDetailsFromId(String volumeId, ResponseListener<VolumeDetails> responseListener) {
        networkHelper.volumeDetailsFromId(volumeId, responseListener);
    }

    @Override
    public void searchInTitleFromIndex(String phraseInTitle, int index, int max, SearchListener searchListener) {
        networkHelper.searchInTitleFromIndex(phraseInTitle, index, max,searchListener);
    }
}
