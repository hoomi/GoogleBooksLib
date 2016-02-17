package com.hoomi.google.books.lib;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;

/**
 * Created by hoomanostovari on 17/02/2016.
 */
public class GoogleBooksLibTest {

    private static final String TEST_API_KEY = "API_KEY";

    @Test
    public void testNotNull() throws Exception {
        assertNotNull(GoogleBooksLib.getGoogleBooks("API_KEY"));
    }

    @Test
    public void testSingletonPerAPIKey() throws Exception {
        GoogleBooks googleBooks1 = GoogleBooksLib.getGoogleBooks(TEST_API_KEY);
        GoogleBooks googleBooks2 = GoogleBooksLib.getGoogleBooks(TEST_API_KEY);
        assertSame(googleBooks1, googleBooks2);
    }

    @Test
    public void testSynchronised() throws Exception {
        int THREAD_NUMBERS = 10;
        List<GoogleBooks> googleBooksList = new ArrayList<>(THREAD_NUMBERS);
        for (int i = 0; i < THREAD_NUMBERS; i++) {
            new Thread(() -> {
                googleBooksList.add(GoogleBooksLib.getGoogleBooks(TEST_API_KEY));
            }).start();
        }
        Thread.sleep(1000);
        for(GoogleBooks googleBooks: googleBooksList) {
            assertSame(googleBooksList.get(0),googleBooks);
        }

    }
}