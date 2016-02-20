package com.hoomi.google.books.ui.fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.hoomi.books.lib.model.VolumeDetails;
import com.hoomi.google.books.R;
import com.hoomi.google.books.mvp.presenters.BookDetailsPresenter;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static junit.framework.Assert.assertSame;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by hoomanostovari on 20/02/2016.
 */
public class BookDetailsFragmentTest {

    private static final String TEST_DESCRIPTION = "This is a test book description";
    private static final int TEST_PAGE = 403;
    private static final float TEST_RATING = 3.5f;
    @Mock
    private BookDetailsPresenter mockedBookDetailsPresenter;
    @Mock
    private ViewGroup mockedContainer;
    @Mock
    private LayoutInflater mockedLayoutInflater;
    @Mock
    private View mockedView;
    @Mock
    private ImageView mockedBackgroundImageView;
    @Mock
    private TextView mockedBookDesTextView;
    @Mock
    private TextView mockedBookPagesTextView;
    @Mock
    private RatingBar mockedBookRating;
    private BookDetailsFragment bookDetailsFragment;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        when(mockedLayoutInflater.inflate(R.layout.book_details, mockedContainer, false)).thenReturn(mockedView);
        when(mockedView.findViewById(R.id.bookCoverBackground)).thenReturn(mockedBackgroundImageView);
        when(mockedView.findViewById(R.id.bookDescription)).thenReturn(mockedBookDesTextView);
        when(mockedView.findViewById(R.id.bookPages)).thenReturn(mockedBookPagesTextView);
        when(mockedView.findViewById(R.id.bookRating)).thenReturn(mockedBookRating);

        bookDetailsFragment = new BookDetailsFragment(mockedBookDetailsPresenter);
    }

    @Test
    public void testOnCreateView() throws Exception {

        View view = bookDetailsFragment.onCreateView(mockedLayoutInflater, mockedContainer, null);

        assertSame(mockedView, view);
    }

    @Test
    public void testOnResume() throws Exception {
        bookDetailsFragment.onResume();

        verify(mockedBookDetailsPresenter).onResume();
    }

    @Test
    public void testOnPause() throws Exception {
        bookDetailsFragment.onPause();

        verify(mockedBookDetailsPresenter).onPause();
    }

    @Test
    public void testShow() throws Exception {
        VolumeDetails mockedVolumeDetails = mock(VolumeDetails.class);

        when(mockedVolumeDetails.getDescription()).thenReturn(TEST_DESCRIPTION);
        when(mockedVolumeDetails.getAverageRating()).thenReturn(TEST_RATING);
        when(mockedVolumeDetails.getPageCount()).thenReturn(TEST_PAGE);

        bookDetailsFragment.onCreateView(mockedLayoutInflater, mockedContainer, null);
        bookDetailsFragment.show(mockedVolumeDetails);

        verify(mockedBookDesTextView).setText(TEST_DESCRIPTION);
        verify(mockedBookPagesTextView).setText("Pages: " + TEST_PAGE + "");
        verify(mockedBookRating).setRating(TEST_RATING);
    }

    @Test
    public void testShowErrorView() throws Exception {

    }

    @Test
    public void testShowEmptyView() throws Exception {

    }

    @Test
    public void testShowProgress() throws Exception {

    }

    @Test
    public void testHideProgress() throws Exception {

    }
}