package com.hoomi.google.books.ui.adapter;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.hoomi.books.lib.model.Volume;
import com.hoomi.google.books.R;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by hoomanostovari on 18/02/2016.
 */
public class BooksAdapterTest {
    private static final int TEST_SIZE = 20;
    @Mock
    private Context mockedContext;
    @Mock
    private List<Volume> mockedVolumes;
    @Mock
    private View.OnClickListener mockedOnClickListener;
    @Mock
    private LayoutInflater mockedLayoutInflater;
    private BooksAdapter booksAdapter;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        booksAdapter = new BooksAdapter(mockedContext, mockedVolumes, mockedOnClickListener);
    }

    @Test
    public void testOnCreateViewHolder() throws Exception {
        ViewGroup mockedParent = mock(ViewGroup.class);
        View mockedViewItem = mock(View.class);
        View mockedImageView = mock(ImageView.class);
        View mockedTextView = mock(TextView.class);
        Volume mockedVolume = mock(Volume.class);

        when(mockedContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).thenReturn(mockedLayoutInflater);
        when(mockedLayoutInflater.inflate(R.layout.book_item, mockedParent, false)).thenReturn(mockedViewItem);
        when(mockedViewItem.findViewById(R.id.bookTextView)).thenReturn(mockedTextView);
        when(mockedViewItem.findViewById(R.id.bookImageView)).thenReturn(mockedImageView);
        when(mockedVolumes.get(anyInt())).thenReturn(mockedVolume);

        BooksAdapter.BookViewHolder viewHolder = booksAdapter.onCreateViewHolder(mockedParent, 0);

        assertNotNull(viewHolder);
        assertSame(mockedImageView, viewHolder.bookImage);
        assertSame(mockedTextView, viewHolder.bookTitle);

        verify(mockedViewItem).setOnClickListener(mockedOnClickListener);
    }

    @Test
    public void testOnBindViewHolder_EmptyImageUrl() throws Exception {
        View mockedView = mock(View.class);
        BooksAdapter.BookViewHolder bvh = new BooksAdapter.BookViewHolder(mockedView);
        bvh.bookImage = mock(ImageView.class);
        bvh.bookTitle = mock(TextView.class);
        Volume mockedVolume = mock(Volume.class);
        int expectedPos = 1;

        when(mockedVolumes.get(expectedPos)).thenReturn(mockedVolume);

        booksAdapter.onBindViewHolder(bvh, expectedPos);

        verify(mockedVolumes).get(expectedPos);
        verify(mockedView).setTag(mockedVolume);
        verify(bvh.bookImage).setImageBitmap(null);

    }

    @Test
    public void testGetItemCount_Empty() throws Exception {

        when(mockedVolumes.size()).thenReturn(0);

        assertEquals(0, booksAdapter.getItemCount());
    }

    @Test
    public void testGetItemCount_Ten() throws Exception {

        when(mockedVolumes.size()).thenReturn(TEST_SIZE);

        assertEquals(TEST_SIZE, booksAdapter.getItemCount());
    }
}