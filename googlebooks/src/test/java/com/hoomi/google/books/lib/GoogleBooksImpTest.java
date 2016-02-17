package com.hoomi.google.books.lib;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertNotNull;

/**
 * Created by hoomanostovari on 17/02/2016.
 */
public class GoogleBooksImpTest {

    private static final String API_KEY = "API_KEY";
    private GoogleBooksImp googleBooksImp;

    @Before
    public void setUp() throws Exception {
        googleBooksImp = new GoogleBooksImp(API_KEY);
    }

    @Test
    public void testNotNull() throws Exception {
        assertNotNull(googleBooksImp);
    }
}