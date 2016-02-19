package com.hoomi.google.books.mvp.presenters;

import com.hoomi.books.lib.HoomiGoogleBooks;
import com.hoomi.books.lib.listener.SearchListener;
import com.hoomi.books.lib.model.ErrorModel;
import com.hoomi.books.lib.model.Volume;
import com.hoomi.google.books.mvp.views.MVPView;

import java.util.List;

/**
 * Created by hoomanostovari on 18/02/2016.
 */
public class SearchPresenterImp implements SearchPresenter {

    private final HoomiGoogleBooks googleBooks;
    private final MVPView<List<Volume>> mVPView;
    private SearchListener searchListener = new SearchListener() {
        @Override
        public void onSuccess(List<Volume> volumes) {
            if (volumes != null && !volumes.isEmpty()) {
                mVPView.show(volumes);
            } else {
                mVPView.showEmptyView();
            }
            mVPView.hideProgress();
        }

        @Override
        public void onError(ErrorModel errorModel) {
            mVPView.showErrorView();
            mVPView.hideProgress();
        }
    };

    public SearchPresenterImp(HoomiGoogleBooks googleBooks, MVPView<List<Volume>> mVPView) {
        this.googleBooks = googleBooks;
        this.mVPView = mVPView;
    }

    @Override
    public void search(String testTitle) {
        this.mVPView.showProgress();
        this.googleBooks.searchInTitle(testTitle, searchListener);
    }
}
