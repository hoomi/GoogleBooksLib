package com.hoomi.books.lib;

import com.hoomi.books.lib.comms.NetworkHelper;
import com.hoomi.books.lib.listener.SearchListener;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

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
}