package com.hoomi.google.books.utils;

import android.support.annotation.Nullable;

/**
 * Created by hoomanostovari on 19/02/2016.
 */
public class StringUtils {

    public static boolean isEmpty(@Nullable CharSequence str) {
        return str == null || str.length() == 0;
    }
}
