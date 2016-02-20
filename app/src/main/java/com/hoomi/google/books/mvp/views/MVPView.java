package com.hoomi.google.books.mvp.views;

/**
 * Created by hoomanostovari on 18/02/2016.
 */
public interface MVPView<T> {
    void show(T results);

    void showErrorView();

    void showEmptyView();

    void showProgress();

    void hideProgress();
}
