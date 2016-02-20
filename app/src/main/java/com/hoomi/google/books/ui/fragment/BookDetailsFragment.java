package com.hoomi.google.books.ui.fragment;


import android.os.Bundle;
import android.support.annotation.VisibleForTesting;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.hoomi.books.lib.HoomiGoogleBooksLib;
import com.hoomi.books.lib.model.VolumeDetails;
import com.hoomi.google.books.BuildConfig;
import com.hoomi.google.books.R;
import com.hoomi.google.books.mvp.presenters.BookDetailsPresenter;
import com.hoomi.google.books.mvp.views.MVPView;
import com.hoomi.google.books.utils.StringUtils;
import com.squareup.picasso.Picasso;

import java.util.Map;

public class BookDetailsFragment extends Fragment implements MVPView<VolumeDetails> {

    public static final String VOLUME_ID = "VOLUME_ID";
    private final BookDetailsPresenter bookDetailsPresenter;

    private String volumeId;
    private RatingBar bookRatingBar;
    private TextView bookDescription;
    private TextView bookPages;
    private ImageView bookBackgroundImage;

    @VisibleForTesting
    BookDetailsFragment(BookDetailsPresenter bookDetailsPresenter) {
        this.bookDetailsPresenter = bookDetailsPresenter;
    }

    public BookDetailsFragment() {
        this.bookDetailsPresenter = new BookDetailsPresenter(HoomiGoogleBooksLib.getGoogleBooks(BuildConfig.API_KEY), this);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            volumeId = getArguments().getString(VOLUME_ID);
            bookDetailsPresenter.getVolumeDetails(volumeId);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.book_details, container, false);
        bookRatingBar = (RatingBar) view.findViewById(R.id.bookRating);
        bookDescription = (TextView) view.findViewById(R.id.bookDescription);
        bookPages = (TextView) view.findViewById(R.id.bookPages);
        bookBackgroundImage = (ImageView) view.findViewById(R.id.bookCoverBackground);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        bookDetailsPresenter.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        bookDetailsPresenter.onPause();
    }

    @Override
    public void show(VolumeDetails volumeDetails) {
        if (volumeDetails != null) {
            bookDescription.setText(volumeDetails.getDescription());
            //TODO user plurals and string formatting from Android
            bookPages.setText("Pages: " + volumeDetails.getPageCount());
            bookRatingBar.setRating(volumeDetails.getAverageRating());
            loadImage(volumeDetails);
        }
    }

    @Override
    public void showErrorView() {

    }

    @Override
    public void showEmptyView() {

    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    private void loadImage(VolumeDetails volumeDetails) {
        Map<String, String> images = volumeDetails.getImageLinks();
        String imageUrl = null;
        if (images != null && !images.isEmpty()) {
            imageUrl = images.get(VolumeDetails.EXTRA_LARGE);
            if (StringUtils.isEmpty(imageUrl)) {
                imageUrl = images.get(VolumeDetails.LARGE);
            }
        }

        if (!StringUtils.isEmpty(imageUrl)) {
            Picasso.with(getContext()).load(imageUrl).fit().centerCrop().into(bookBackgroundImage);
        }

    }
}
