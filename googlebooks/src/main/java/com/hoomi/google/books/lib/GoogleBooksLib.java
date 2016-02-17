package com.hoomi.google.books.lib;

public final class GoogleBooksLib {

    private static GoogleBooks googleBooks;

    /**
     * This is to get the google books api. Once used once cannot be changed
     * @param api_key It is the api key that you get from google api console
     * @return It is te object that you should use to communicate with Google APIs
     */
    public static GoogleBooks getGoogleBooks(String api_key) {
        synchronized (GoogleBooksLib.class){
            if (googleBooks == null) {
                googleBooks = new GoogleBooksImp(api_key);
            }
        }
        return googleBooks;
    }
}
