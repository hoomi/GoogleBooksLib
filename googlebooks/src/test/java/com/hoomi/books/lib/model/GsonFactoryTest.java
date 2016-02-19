package com.hoomi.books.lib.model;

import com.google.gson.Gson;
import com.hoomi.books.lib.model.Volume;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by hoomanostovari on 17/02/2016.
 */
public class GsonFactoryTest {

    private final String VOLUME_JSON = "{\"kind\":\"books#volume\",\"id\":\"9e9Kn9N8yP0C\",\"etag\":\"npURrEvSdYM\",\"selfLink\":\"https://www.googleapis.com/books/v1/volumes/9e9Kn9N8yP0C\",\"volumeInfo\":{\"title\":\"Best Android Apps\",\"authors\":[\"Mike Hendrickson\",\"Brian Sawyer\"],\"publisher\":\"\\\"O'Reilly Media, Inc.\\\"\",\"publishedDate\":\"2010-04-27\",\"description\":\"You can choose from thousands of apps to make your Android device do just about anything you can think of -- and probably a few things you'd never imagine. There are so many Android apps available, in fact, that it's been difficult to find the best of the bunch -- until now. Best Android Apps leads you beyond the titles in Android Market's \\\"Top Paid\\\" and \\\"Top Free\\\" bins to showcase apps that will truly delight, empower, and entertain you. The authors have tested and handpicked more than 200 apps and games, each listed with a description and details highlighting the app's valuable tips and special features. Flip through the book to browse their suggestions, or head directly to the category of your choice to find the best apps to use at work, on the town, at play, at home, or on the road. Discover great Android apps to help you: Juggle tasks Connect with friends Play games Organize documents Explore what's nearby Get in shape Travel the world Find new music Dine out Manage your money ...and much more!\",\"industryIdentifiers\":[{\"type\":\"ISBN_13\",\"identifier\":\"9781449393717\"},{\"type\":\"ISBN_10\",\"identifier\":\"1449393713\"}],\"readingModes\":{\"text\":true,\"image\":true},\"pageCount\":240,\"printType\":\"BOOK\",\"categories\":[\"Computers\"],\"averageRating\":4,\"ratingsCount\":7,\"maturityRating\":\"NOT_MATURE\",\"allowAnonLogging\":false,\"contentVersion\":\"1.4.2.0.preview.3\",\"imageLinks\":{\"smallThumbnail\":\"http://books.google.co.uk/books/content?id=9e9Kn9N8yP0C&printsec=frontcover&img=1&zoom=5&edge=curl&source=gbs_api\",\"thumbnail\":\"http://books.google.co.uk/books/content?id=9e9Kn9N8yP0C&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api\"},\"language\":\"en\",\"previewLink\":\"http://books.google.co.uk/books?id=9e9Kn9N8yP0C&printsec=frontcover&dq=android&hl=&cd=2&source=gbs_api\",\"infoLink\":\"http://books.google.co.uk/books?id=9e9Kn9N8yP0C&dq=android&hl=&source=gbs_api\",\"canonicalVolumeLink\":\"http://books.google.co.uk/books/about/Best_Android_Apps.html?hl=&id=9e9Kn9N8yP0C\"},\"saleInfo\":{\"country\":\"GB\",\"saleability\":\"FOR_SALE\",\"isEbook\":true,\"listPrice\":{\"amount\":12.6,\"currencyCode\":\"GBP\"},\"retailPrice\":{\"amount\":10.08,\"currencyCode\":\"GBP\"},\"buyLink\":\"http://books.google.co.uk/books?id=9e9Kn9N8yP0C&dq=android&hl=&buy=&source=gbs_api\",\"offers\":[{\"finskyOfferType\":1,\"listPrice\":{\"amountInMicros\":12600000,\"currencyCode\":\"GBP\"},\"retailPrice\":{\"amountInMicros\":10080000,\"currencyCode\":\"GBP\"}}]},\"accessInfo\":{\"country\":\"GB\",\"viewability\":\"PARTIAL\",\"embeddable\":true,\"publicDomain\":false,\"textToSpeechPermission\":\"ALLOWED\",\"epub\":{\"isAvailable\":true},\"pdf\":{\"isAvailable\":true},\"webReaderLink\":\"http://books.google.co.uk/books/reader?id=9e9Kn9N8yP0C&hl=&printsec=frontcover&output=reader&source=gbs_api\",\"accessViewStatus\":\"SAMPLE\",\"quoteSharingAllowed\":false},\"searchInfo\":{\"textSnippet\":\"Flip through the book to browse their suggestions, or head directly to the category of your choice to find the best apps to use at work, on the town, at play, at home, or on the road.\"}}";

    @Test
    public void testNotNull() throws Exception {
        assertNotNull(GsonFactory.getGson());
    }

    @Test
    public void testDeserialising() throws Exception {
        Gson gson = GsonFactory.getGson();
        Volume volume = gson.fromJson(VOLUME_JSON, Volume.class);
        assertNotNull(volume);
        assertEquals("https://www.googleapis.com/books/v1/volumes/9e9Kn9N8yP0C", volume.getSelfLink());
        assertEquals("9e9Kn9N8yP0C", volume.getId());
        assertEquals("Best Android Apps", volume.getTitle());
        assertEquals("Mike Hendrickson", volume.getAuthors().get(0));
        assertEquals("Brian Sawyer", volume.getAuthors().get(1));
        assertNotEquals("",volume.getDescription());
    }
}