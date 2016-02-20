package com.hoomi.google.books.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.support.v4.app.Fragment;
import android.support.v4.widget.ContentLoadingProgressBar;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hoomi.books.lib.HoomiGoogleBooksLib;
import com.hoomi.books.lib.model.Volume;
import com.hoomi.google.books.BuildConfig;
import com.hoomi.google.books.NavigationHandler;
import com.hoomi.google.books.R;
import com.hoomi.google.books.mvp.presenters.SearchPresenter;
import com.hoomi.google.books.mvp.presenters.SearchPresenterImp;
import com.hoomi.google.books.mvp.views.MVPView;
import com.hoomi.google.books.ui.adapter.BooksAdapter;

import java.util.List;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment implements MVPView<List<Volume>> {


    private SearchPresenter presenter;
    private SearchView searchView;
    private RecyclerView recyclerView;
    private ContentLoadingProgressBar progressBar;
    private TextView errorTextView;
    private BooksAdapter adapter;

    private View.OnClickListener onItemClickedListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Volume volume = (Volume) v.getTag();
            ((NavigationHandler) getActivity()).switchToDetails(volume.getId());
        }
    };

    @VisibleForTesting
    MainActivityFragment(SearchPresenter presenter) {
        this.presenter = presenter;
    }

    public MainActivityFragment() {
        this.presenter = new SearchPresenterImp(HoomiGoogleBooksLib.getGoogleBooks(BuildConfig.API_KEY), this);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        searchView = (SearchView) view.findViewById(R.id.searchView);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                searchClicked(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        // Using inflater context so we can  inject the context from our tests
        recyclerView.setLayoutManager(new GridLayoutManager(inflater.getContext(),
                inflater.getContext().getResources().getInteger(R.integer.span),
                RecyclerView.VERTICAL, true));
        progressBar = (ContentLoadingProgressBar) view.findViewById(R.id.progressBar);

        errorTextView = (TextView) view.findViewById(R.id.errorTextView);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        presenter.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }

    @Override
    public void show(List<Volume> volumes) {
        if (adapter == null) {
            adapter = new BooksAdapter(getContext(), volumes, onItemClickedListener);
            recyclerView.setAdapter(adapter);
        }
        adapter.setVolumes(volumes);

    }

    @Override
    public void showErrorView() {
        recyclerView.setVisibility(View.GONE);
        errorTextView.setVisibility(View.VISIBLE);
    }

    @Override
    public void showEmptyView() {
        recyclerView.setVisibility(View.GONE);
        errorTextView.setVisibility(View.VISIBLE);
    }

    @Override
    public void showProgress() {
        progressBar.show();
        recyclerView.setVisibility(View.GONE);
        errorTextView.setVisibility(View.GONE);
    }

    @Override
    public void hideProgress() {
        progressBar.hide();
        recyclerView.setVisibility(View.VISIBLE);

    }

    public void searchClicked(String title) {
        presenter.search(title);
    }
}
