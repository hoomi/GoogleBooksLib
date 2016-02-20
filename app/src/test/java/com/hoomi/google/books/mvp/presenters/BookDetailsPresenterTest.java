package com.hoomi.google.books.mvp.presenters;

import com.hoomi.books.lib.HoomiGoogleBooks;
import com.hoomi.books.lib.listener.ResponseListener;
import com.hoomi.books.lib.model.ErrorModel;
import com.hoomi.books.lib.model.VolumeDetails;
import com.hoomi.google.books.mvp.views.MVPView;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * Created by hoomanostovari on 20/02/2016.
 */
public class BookDetailsPresenterTest {

    private static final String TEST_VOLUME_ID = "TEST_VOLUME_ID";
    @Mock
    private MVPView<VolumeDetails> mockedMvpView;
    @Mock
    private HoomiGoogleBooks mockedHoomiGoogleBooks;

    private BookDetailsPresenter bookDetailsPresenter;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        bookDetailsPresenter = new BookDetailsPresenter(mockedHoomiGoogleBooks,mockedMvpView);
    }


    @Test
    public void testGetDetails_NotEmpty() throws Exception {
        ArgumentCaptor<ResponseListener> captor= ArgumentCaptor.forClass(ResponseListener.class);
        VolumeDetails mockedVolumeDetails = mock(VolumeDetails.class);

        bookDetailsPresenter.getVolumeDetails(TEST_VOLUME_ID);
        InOrder inOrder = inOrder(mockedMvpView,mockedHoomiGoogleBooks);
        inOrder.verify(mockedMvpView).showProgress();
        inOrder.verify(mockedHoomiGoogleBooks).volumeDetailsFromId(eq(TEST_VOLUME_ID),captor.capture());

        captor.getValue().onSuccess(mockedVolumeDetails);

        inOrder.verify(mockedMvpView).show(mockedVolumeDetails);
        inOrder.verify(mockedMvpView).hideProgress();
    }

    @Test
    public void testGetDetails_Empty() throws Exception {
        ArgumentCaptor<ResponseListener> captor= ArgumentCaptor.forClass(ResponseListener.class);

        bookDetailsPresenter.getVolumeDetails(TEST_VOLUME_ID);

        InOrder inOrder = inOrder(mockedMvpView,mockedHoomiGoogleBooks);
        inOrder.verify(mockedMvpView).showProgress();
        inOrder.verify(mockedHoomiGoogleBooks).volumeDetailsFromId(eq(TEST_VOLUME_ID),captor.capture());

        captor.getValue().onSuccess(null);

        inOrder.verify(mockedMvpView).showEmptyView();
        inOrder.verify(mockedMvpView).hideProgress();
    }

    @Test
    public void testGetDetails_Error() throws Exception {
        ArgumentCaptor<ResponseListener> captor= ArgumentCaptor.forClass(ResponseListener.class);

        bookDetailsPresenter.getVolumeDetails(TEST_VOLUME_ID);

        InOrder inOrder = inOrder(mockedMvpView,mockedHoomiGoogleBooks);
        inOrder.verify(mockedMvpView).showProgress();
        inOrder.verify(mockedHoomiGoogleBooks).volumeDetailsFromId(eq(TEST_VOLUME_ID),captor.capture());

        captor.getValue().onError(new ErrorModel("This is a test error model"));

        inOrder.verify(mockedMvpView).showErrorView();
        inOrder.verify(mockedMvpView).hideProgress();


    }

    @Test
    public void testOnPause() throws Exception {

    }

    @Test
    public void testOnResume() throws Exception {

    }

    @Test
    public void testOnDestroy() throws Exception {

    }
}