package com.hoomi.books.lib;

import com.hoomi.books.lib.HoomiGoogleBooks;
import com.hoomi.books.lib.HoomiGoogleBooksLib;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;

/**
 * Created by hoomanostovari on 17/02/2016.
 */
public class HoomiHoomiGoogleBooksLibTest {

    private static final String TEST_API_KEY = "API_KEY";

    @Test
    public void testNotNull() throws Exception {
        assertNotNull(HoomiGoogleBooksLib.getGoogleBooks("API_KEY"));
    }

    @Test
    public void testSingletonPerAPIKey() throws Exception {
        HoomiGoogleBooks googleBooks1 = HoomiGoogleBooksLib.getGoogleBooks(TEST_API_KEY);
        HoomiGoogleBooks googleBooks2 = HoomiGoogleBooksLib.getGoogleBooks(TEST_API_KEY);
        assertSame(googleBooks1, googleBooks2);
    }

    @Test
    public void testSynchronised() throws Exception {
        int THREAD_NUMBERS = 10;
        final List<HoomiGoogleBooks> googleBooksList = new ArrayList<>(THREAD_NUMBERS);
        for (int i = 0; i < THREAD_NUMBERS; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    googleBooksList.add(HoomiGoogleBooksLib.getGoogleBooks(TEST_API_KEY));

                }
            }).start();
        }
        Thread.sleep(1000);
        for (HoomiGoogleBooks googleBooks : googleBooksList) {
            assertSame(googleBooksList.get(0), googleBooks);
        }

    }
}