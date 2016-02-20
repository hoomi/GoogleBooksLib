package com.hoomi.google.books.mvp.presenters;

import com.hoomi.books.lib.HoomiGoogleBooks;
import com.hoomi.books.lib.listener.SearchListener;
import com.hoomi.books.lib.model.ErrorModel;
import com.hoomi.books.lib.model.Volume;
import com.hoomi.google.books.mvp.views.VolumeView;

import java.util.List;

/**
 * Created by hoomanostovari on 18/02/2016.
 */
public class SearchPresenter implements FragmentPresenter {

    private final HoomiGoogleBooks googleBooks;
    private final VolumeView volumeView;
    private boolean paused;
    private boolean moreToLoad;
    private String title;
    private List<Volume> cachedVolumes;
    private SearchListener searchListener = new SearchListener() {

        @Override
        public void onSuccess(List<Volume> volumes) {
            moreToLoad = !(volumes == null || volumes.isEmpty());
            if (!paused) {
                if (volumes != null && !volumes.isEmpty()) {
                    volumeView.show(volumes);
                } else {
                    volumeView.showEmptyView();
                }
                volumeView.hideProgress();
            } else {
                cachedVolumes = volumes;
            }
        }

        @Override
        public void onError(ErrorModel errorModel) {
            if (!paused) {
                volumeView.showErrorView();
                volumeView.hideProgress();
            }
            moreToLoad = false;
        }
    };

    private SearchListener loadListener = new SearchListener() {

        @Override
        public void onSuccess(List<Volume> volumes) {
            if (!paused) {
                if (volumes != null && !volumes.isEmpty()) {
                    volumeView.addVolumes(volumes);
                } else {
                    moreToLoad = false;
                }
            } else {
                if (cachedVolumes == null) {
                    cachedVolumes = volumes;
                } else {
                    cachedVolumes.addAll(volumes);
                }
            }
        }

        @Override
        public void onError(ErrorModel errorModel) {
            moreToLoad = false;
        }
    };

    public SearchPresenter(HoomiGoogleBooks googleBooks, VolumeView volumeView) {
        this.googleBooks = googleBooks;
        this.volumeView = volumeView;
        this.paused = false;
    }

    public void search(String title) {
        this.title = title;
        this.moreToLoad = true;
        this.volumeView.showProgress();
        this.googleBooks.searchInTitle(title, searchListener);
    }

    @Override
    public void onPause() {
        this.paused = true;
    }

    @Override
    public void onResume() {
        this.paused = false;
        if (cachedVolumes != null) {
            volumeView.show(cachedVolumes);
            cachedVolumes = null;
        }
        volumeView.hideProgress();
    }

    @Override
    public void onDestroy() {
        this.paused = true;
        if (this.cachedVolumes != null) {
            this.cachedVolumes.clear();
            this.cachedVolumes = null;
        }
    }

    public void loadMoreVolume(int index) {
        if (moreToLoad) {
            this.googleBooks.searchInTitleFromIndex(title, index, 40, loadListener);
        }
    }
}
