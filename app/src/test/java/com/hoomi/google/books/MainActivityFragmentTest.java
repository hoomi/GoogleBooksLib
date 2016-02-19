package com.hoomi.google.books;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.widget.ContentLoadingProgressBar;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hoomi.google.books.mvp.presenters.SearchPresenter;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;
import static junit.framework.TestCase.assertNotNull;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyBoolean;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by hoomanostovari on 18/02/2016.
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({Bundle.class})
public class MainActivityFragmentTest {

    private static final String TEST_TITLE = "android";
    private static final int TEST_SPAN = 4;
    private static final int TEST_SIZE = 10;

    @Mock
    private SearchPresenter mockedSearchPresenter;
    @Mock
    private LayoutInflater mockedLayoutInflater;
    @Mock
    private ViewGroup mockedContainerView;
    @Mock
    private View mockedFragmentView;
    @Mock
    private Context mockedContext;
    @Mock
    private Resources mockedResources;
    @Mock
    private RecyclerView mockedRecyclerView;
    @Mock
    private SearchView mockedSearchView;
    @Mock
    private ContentLoadingProgressBar mockedContentLoadingProgressBar;
    @Mock
    private TextView mockedErrorTextView;
    private Bundle mockedSavedInstanceState;

    private MainActivityFragment mainActivityFragment;


    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        mockedSavedInstanceState = PowerMockito.mock(Bundle.class);

        when(mockedContext.getResources()).thenReturn(mockedResources);
        when(mockedLayoutInflater.getContext()).thenReturn(mockedContext);
        when(mockedLayoutInflater.inflate(anyInt(), any(ViewGroup.class), anyBoolean())).thenReturn(mockedFragmentView);
        when(mockedFragmentView.findViewById(R.id.searchView)).thenReturn(mockedSearchView);
        when(mockedFragmentView.findViewById(R.id.recyclerView)).thenReturn(mockedRecyclerView);
        when(mockedFragmentView.findViewById(R.id.progressBar)).thenReturn(mockedContentLoadingProgressBar);
        when(mockedFragmentView.findViewById(R.id.errorTextView)).thenReturn(mockedErrorTextView);
        when(mockedResources.getInteger(R.integer.span)).thenReturn(TEST_SPAN);

        mainActivityFragment = new MainActivityFragment(mockedSearchPresenter);
    }

    @Test
    public void testSearchClicked() throws Exception {

        mainActivityFragment.searchClicked(TEST_TITLE);

        verify(mockedSearchPresenter).search(TEST_TITLE);
    }

    @Test
    public void testOnCreateView() throws Exception {

        View view = mainActivityFragment.onCreateView(mockedLayoutInflater, mockedContainerView, mockedSavedInstanceState);

        assertNotNull(view);

        verify(mockedLayoutInflater).inflate(R.layout.fragment_main, mockedContainerView, false);
        verify(mockedFragmentView).findViewById(R.id.searchView);
        verify(mockedFragmentView).findViewById(R.id.recyclerView);
        verify(mockedFragmentView).findViewById(R.id.progressBar);
        verify(mockedFragmentView).findViewById(R.id.errorTextView);
    }

    @Test
    public void testSearchViewListenersSetup() throws Exception {

        mainActivityFragment.onCreateView(mockedLayoutInflater, mockedContainerView, mockedSavedInstanceState);

        verify(mockedSearchView).setOnQueryTextListener(any(SearchView.OnQueryTextListener.class));
    }


    @Test
    public void testRecyclerViewSetup() throws Exception {

        mainActivityFragment.onCreateView(mockedLayoutInflater, mockedContainerView, mockedSavedInstanceState);

        ArgumentCaptor<RecyclerView.LayoutManager> argumentCaptor = ArgumentCaptor.forClass(RecyclerView.LayoutManager.class);

        verify(mockedRecyclerView).setLayoutManager(argumentCaptor.capture());

        RecyclerView.LayoutManager layoutManager = argumentCaptor.getValue();

        assertTrue(layoutManager instanceof GridLayoutManager);
        assertEquals(((GridLayoutManager) layoutManager).getOrientation(), RecyclerView.VERTICAL);
        assertEquals(((GridLayoutManager) layoutManager).getSpanCount(), TEST_SPAN);
    }

    @Test
    public void testOnSearchClicked() throws Exception {

        SearchView mockedSearchView = mock(SearchView.class);
        ArgumentCaptor<SearchView.OnQueryTextListener> argumentCaptor = new ArgumentCaptor<>();

        when(mockedLayoutInflater.inflate(anyInt(), any(ViewGroup.class), anyBoolean())).thenReturn(mockedFragmentView);
        when(mockedFragmentView.findViewById(R.id.searchView)).thenReturn(mockedSearchView);
        when(mockedSearchView.getQuery()).thenReturn(TEST_TITLE);

        mainActivityFragment.onCreateView(mockedLayoutInflater, mockedContainerView, mockedSavedInstanceState);

        verify(mockedSearchView).setOnQueryTextListener(argumentCaptor.capture());

        argumentCaptor.getValue().onQueryTextSubmit(TEST_TITLE);

        verify(mockedSearchPresenter).search(TEST_TITLE);
    }

    @Test
    public void testShowErrorView() throws Exception {
        mainActivityFragment.onCreateView(mockedLayoutInflater, mockedContainerView, mockedSavedInstanceState);
        mainActivityFragment.showErrorView();
        verify(mockedRecyclerView).setVisibility(View.GONE);
        verify(mockedErrorTextView).setVisibility(View.VISIBLE);

    }

    @Test
    public void testShowEmptyView() throws Exception {
        mainActivityFragment.onCreateView(mockedLayoutInflater, mockedContainerView, mockedSavedInstanceState);
        mainActivityFragment.showEmptyView();
        verify(mockedRecyclerView).setVisibility(View.GONE);
        verify(mockedErrorTextView).setVisibility(View.VISIBLE);
    }

    @Test
    public void testShowProgress() throws Exception {
        mainActivityFragment.onCreateView(mockedLayoutInflater, mockedContainerView, mockedSavedInstanceState);
        mainActivityFragment.showProgress();
        verify(mockedContentLoadingProgressBar).show();
        verify(mockedRecyclerView).setVisibility(View.GONE);
        verify(mockedErrorTextView).setVisibility(View.GONE);
    }

    @Test
    public void testHideProgress() throws Exception {
        mainActivityFragment.onCreateView(mockedLayoutInflater, mockedContainerView, mockedSavedInstanceState);
        mainActivityFragment.hideProgress();
        verify(mockedContentLoadingProgressBar).hide();
        verify(mockedRecyclerView).setVisibility(View.VISIBLE);
    }
}