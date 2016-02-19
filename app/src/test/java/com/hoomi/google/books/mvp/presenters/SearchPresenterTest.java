package com.hoomi.google.books.mvp.presenters;

import com.hoomi.books.lib.HoomiGoogleBooks;
import com.hoomi.books.lib.listener.SearchListener;
import com.hoomi.books.lib.model.ErrorModel;
import com.hoomi.books.lib.model.Volume;
import com.hoomi.google.books.mvp.views.MVPView;


import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.util.List;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

/**
 * Created by hoomanostovari on 18/02/2016.
 */
public class SearchPresenterTest {

    private static final String TEST_TITLE = "android";
    @Mock
    private HoomiGoogleBooks mockedGoogleBooks;
    @Mock
    private MVPView mockedMVPView;

    private SearchPresenterImp searchPresenter;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        searchPresenter = new SearchPresenterImp(mockedGoogleBooks, mockedMVPView);
    }

    @Test
    public void testSearchInTitle() throws Exception {
        searchPresenter.search(TEST_TITLE);
        verify(mockedGoogleBooks).searchInTitle(eq(TEST_TITLE),any(SearchListener.class));
    }

    @Test
    public void testShowSearchResult() throws Exception {
        final List<Volume> mockedVolumes = mock(List.class);

        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                SearchListener searchListener = (SearchListener) invocation.getArguments()[1];
                searchListener.onSuccess(mockedVolumes);
                return null;
            }
        }).when(mockedGoogleBooks).searchInTitle(eq(TEST_TITLE),any(SearchListener.class));

        searchPresenter.search(TEST_TITLE);

        InOrder inOrder = inOrder(mockedMVPView);
        inOrder.verify(mockedMVPView).showProgress();
        inOrder.verify(mockedMVPView).show(mockedVolumes);
        inOrder.verify(mockedMVPView).hideProgress();
    }

    @Test
    public void testShowEmptySearchResult() throws Exception {
        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                SearchListener searchListener = (SearchListener) invocation.getArguments()[1];
                searchListener.onSuccess(null);
                return null;
            }
        }).when(mockedGoogleBooks).searchInTitle(eq(TEST_TITLE),any(SearchListener.class));

        searchPresenter.search(TEST_TITLE);

        InOrder inOrder = inOrder(mockedMVPView);
        inOrder.verify(mockedMVPView).showProgress();
        inOrder.verify(mockedMVPView).showEmptyView();
        inOrder.verify(mockedMVPView).hideProgress();
        verify(mockedMVPView,never()).show(any(List.class));
    }

    @Test
    public void testShowSearchError() throws Exception {

        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                SearchListener searchListener = (SearchListener) invocation.getArguments()[1];
                searchListener.onError(new ErrorModel("This is a test error"));
                return null;
            }
        }).when(mockedGoogleBooks).searchInTitle(eq(TEST_TITLE),any(SearchListener.class));

        searchPresenter.search(TEST_TITLE);
        InOrder inOrder = inOrder(mockedMVPView);
        inOrder.verify(mockedMVPView).showProgress();
        inOrder.verify(mockedMVPView).showErrorView();
        inOrder.verify(mockedMVPView).hideProgress();

    }
}