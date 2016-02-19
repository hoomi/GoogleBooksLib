package com.hoomi.books.lib;

public final class HoomiGoogleBooksLib {

    private static HoomiGoogleBooks googleBooks;

    /**
     * This is to get the google books api. Once used once cannot be changed
     * @param apiKey It is the api key that you get from google api console
     * @return It is te object that you should use to communicate with Google APIs
     */
    public static HoomiGoogleBooks getGoogleBooks(String apiKey) {
        synchronized (HoomiGoogleBooksLib.class){
            if (googleBooks == null) {
                googleBooks = new HoomiGoogleBooksImp(apiKey);
            }
        }
        return googleBooks;
    }
}
