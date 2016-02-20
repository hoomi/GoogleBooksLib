package com.hoomi.books.lib;

import com.hoomi.books.lib.comms.NetworkHelper;
import com.hoomi.books.lib.listener.ResponseListener;
import com.hoomi.books.lib.listener.SearchListener;
import com.hoomi.books.lib.model.VolumeDetails;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;


/**
 * Created by hoomanostovari on 17/02/2016.
 */
public class HoomiGoogleBooksImpTest {

    @Mock
    private NetworkHelper mockedNetworkHelper;
    @Mock
    private SearchListener mockedSearchListener;

    private HoomiGoogleBooksImp hoomiGoogleBooks;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        hoomiGoogleBooks = new HoomiGoogleBooksImp(mockedNetworkHelper);
    }

    @Test
    public void testSearchInTitle() throws Exception {
        hoomiGoogleBooks.searchInTitle("android", mockedSearchListener);

        verify(mockedNetworkHelper).searchInTitle("android", mockedSearchListener);
    }

    @Test
    public void testGetVolumeDetails() throws Exception {
        String TEST_VOLUME_ID = "testVolumeId";
        ResponseListener<VolumeDetails> mockedResponseListener = mock(ResponseListener.class);

        hoomiGoogleBooks.volumeDetailsFromId(TEST_VOLUME_ID, mockedResponseListener);

        verify(mockedNetworkHelper).volumeDetailsFromId(TEST_VOLUME_ID, mockedResponseListener);

    }
}