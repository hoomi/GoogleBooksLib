package com.hoomi.google.books.utils;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by hoomanostovari on 19/02/2016.
 */
public class StringUtilsTest {

    @Test
    public void testEmptyString() throws Exception {
        assertTrue(StringUtils.isEmpty(""));
    }

    @Test
    public void testNull() throws Exception {
        assertTrue(StringUtils.isEmpty(null));
    }
}