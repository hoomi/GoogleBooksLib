package com.hoomi.google.books.mvp.views;

/**
 * Created by hoomanostovari on 18/02/2016.
 */
public interface MVPView<T> {
    void show(T mockedVolumes);

    void showErrorView();

    void showEmptyView();

    void showProgress();

    void hideProgress();
}
