package com.hoomi.google.books.mvp.presenters;

import com.hoomi.books.lib.HoomiGoogleBooks;
import com.hoomi.books.lib.listener.ResponseListener;
import com.hoomi.books.lib.model.ErrorModel;
import com.hoomi.books.lib.model.VolumeDetails;
import com.hoomi.google.books.mvp.views.MVPView;

/**
 * Created by hoomanostovari on 20/02/2016.
 */
public class BookDetailsPresenter implements FragmentPresenter {

    private final HoomiGoogleBooks hoomiGoogleBooks;
    private final MVPView<VolumeDetails> view;
    private ResponseListener<VolumeDetails> responseListener = new ResponseListener<VolumeDetails>() {
        @Override
        public void onSuccess(VolumeDetails volumeDetails) {
            if (volumeDetails != null) {
                view.show(volumeDetails);
            } else {
                view.showEmptyView();
            }
            view.hideProgress();
        }

        @Override
        public void onError(ErrorModel errorModel) {
            view.showErrorView();
            view.hideProgress();
        }
    };

    public BookDetailsPresenter(HoomiGoogleBooks hoomiGoogleBooks, MVPView<VolumeDetails> view) {
        this.hoomiGoogleBooks = hoomiGoogleBooks;
        this.view = view;
    }

    @Override
    public void onPause() {

    }

    @Override
    public void onResume() {

    }

    @Override
    public void onDestroy() {

    }

    public void getVolumeDetails(String volumeId) {
        this.view.showProgress();
        this.hoomiGoogleBooks.volumeDetailsFromId(volumeId, responseListener);
    }
}
