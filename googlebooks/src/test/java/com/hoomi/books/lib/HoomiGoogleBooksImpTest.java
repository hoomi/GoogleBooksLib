package com.hoomi.books.lib;

import com.hoomi.books.lib.HoomiGoogleBooksImp;
import com.hoomi.books.lib.listener.SearchListener;
import com.hoomi.books.lib.model.ErrorModel;
import com.hoomi.books.lib.model.Volume;
import com.squareup.okhttp.HttpUrl;
import com.squareup.okhttp.mockwebserver.MockResponse;
import com.squareup.okhttp.mockwebserver.MockWebServer;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;
import static junit.framework.TestCase.assertNotNull;

/**
 * Created by hoomanostovari on 17/02/2016.
 */
public class HoomiGoogleBooksImpTest {

    private static final String API_KEY = "API_KEY";
    private static final String TEST_PATH = "/books/v1/volumes";
    private static final String RESPONSE_BODY = "{\"kind\":\"books#volumes\",\"totalItems\":1429,\"items\":[{\"kind\":\"books#volume\",\"id\":\"9e9Kn9N8yP0C\",\"etag\":\"npURrEvSdYM\",\"selfLink\":\"https://www.googleapis.com/books/v1/volumes/9e9Kn9N8yP0C\",\"volumeInfo\":{\"title\":\"Best Android Apps\",\"authors\":[\"Mike Hendrickson\",\"Brian Sawyer\"],\"publisher\":\"\\\"O'Reilly Media, Inc.\\\"\",\"publishedDate\":\"2010-04-27\",\"description\":\"You can choose from thousands of apps to make your Android device do just about anything you can think of -- and probably a few things you'd never imagine. There are so many Android apps available, in fact, that it's been difficult to find the best of the bunch -- until now. Best Android Apps leads you beyond the titles in Android Market's \\\"Top Paid\\\" and \\\"Top Free\\\" bins to showcase apps that will truly delight, empower, and entertain you. The authors have tested and handpicked more than 200 apps and games, each listed with a description and details highlighting the app's valuable tips and special features. Flip through the book to browse their suggestions, or head directly to the category of your choice to find the best apps to use at work, on the town, at play, at home, or on the road. Discover great Android apps to help you: Juggle tasks Connect with friends Play games Organize documents Explore what's nearby Get in shape Travel the world Find new music Dine out Manage your money ...and much more!\",\"industryIdentifiers\":[{\"type\":\"ISBN_13\",\"identifier\":\"9781449393717\"},{\"type\":\"ISBN_10\",\"identifier\":\"1449393713\"}],\"readingModes\":{\"text\":true,\"image\":true},\"pageCount\":240,\"printType\":\"BOOK\",\"categories\":[\"Computers\"],\"averageRating\":4.0,\"ratingsCount\":7,\"maturityRating\":\"NOT_MATURE\",\"allowAnonLogging\":false,\"contentVersion\":\"1.4.2.0.preview.3\",\"imageLinks\":{\"smallThumbnail\":\"http://books.google.co.uk/books/content?id=9e9Kn9N8yP0C&printsec=frontcover&img=1&zoom=5&edge=curl&source=gbs_api\",\"thumbnail\":\"http://books.google.co.uk/books/content?id=9e9Kn9N8yP0C&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api\"},\"language\":\"en\",\"previewLink\":\"http://books.google.co.uk/books?id=9e9Kn9N8yP0C&printsec=frontcover&dq=android&hl=&cd=2&source=gbs_api\",\"infoLink\":\"http://books.google.co.uk/books?id=9e9Kn9N8yP0C&dq=android&hl=&source=gbs_api\",\"canonicalVolumeLink\":\"http://books.google.co.uk/books/about/Best_Android_Apps.html?hl=&id=9e9Kn9N8yP0C\"},\"saleInfo\":{\"country\":\"GB\",\"saleability\":\"FOR_SALE\",\"isEbook\":true,\"listPrice\":{\"amount\":12.6,\"currencyCode\":\"GBP\"},\"retailPrice\":{\"amount\":10.08,\"currencyCode\":\"GBP\"},\"buyLink\":\"http://books.google.co.uk/books?id=9e9Kn9N8yP0C&dq=android&hl=&buy=&source=gbs_api\",\"offers\":[{\"finskyOfferType\":1,\"listPrice\":{\"amountInMicros\":1.26E7,\"currencyCode\":\"GBP\"},\"retailPrice\":{\"amountInMicros\":1.008E7,\"currencyCode\":\"GBP\"}}]},\"accessInfo\":{\"country\":\"GB\",\"viewability\":\"PARTIAL\",\"embeddable\":true,\"publicDomain\":false,\"textToSpeechPermission\":\"ALLOWED\",\"epub\":{\"isAvailable\":true},\"pdf\":{\"isAvailable\":true},\"webReaderLink\":\"http://books.google.co.uk/books/reader?id=9e9Kn9N8yP0C&hl=&printsec=frontcover&output=reader&source=gbs_api\",\"accessViewStatus\":\"SAMPLE\",\"quoteSharingAllowed\":false},\"searchInfo\":{\"textSnippet\":\"Flip through the book to browse their suggestions, or head directly to the category of your choice to find the best apps to use at work, on the town, at play, at home, or on the road.\"}},{\"kind\":\"books#volume\",\"id\":\"9dCHkQEACAAJ\",\"etag\":\"VhAMarYBo9Y\",\"selfLink\":\"https://www.googleapis.com/books/v1/volumes/9dCHkQEACAAJ\",\"volumeInfo\":{\"title\":\"Head First Android Development\",\"authors\":[\"Jonathan Simon\"],\"publisher\":\"O'Reilly Media, Incorporated\",\"publishedDate\":\"2012-10-15\",\"description\":\"Android devices are stealing market share from the iPhone with dramatic speed, and you have a killer app idea. Where to begin? Head First Android Development will help you get your first application up and running in no time with the Android SDK and Eclipse plug-in. You'll learn how to design for devices with a variety of different screen sizes and resolutions, along with mastering core programming and design principles that will make your app stand out. Whether you're a seasoned iPhone developer who wants to jump into the the Android Market, or someone with previous programming skills but no mobile apps in your resume, this book offers a complete learning experience for creating eye-catching, top-selling Android applications. You'll learn how to: Install the Android SDK and Eclipse plug-in and get started building apps Add buttons, edit text fields, and build your own navigation options in the Android menu Customize the look of your app with theming and adding image resources Use Android's content provider mechanism to add images and contact information to an app, and establish permissions for their use Work with the Android devices' camera, GPS, and accelerometer Experiment with different Android emulator configurations to simulate different devices with a variety of screen sizes Optimize, test, and distribute your application in the Android Market We think your time is too valuable to waste struggling with new concepts. Using the latest research in cognitive science and learning theory to craft a multi-sensory learning experience, Head First Android Development uses a visually rich format designed for the way your brain works, not a text-heavy approach that puts you to sleep.\",\"industryIdentifiers\":[{\"type\":\"ISBN_10\",\"identifier\":\"1449393306\"},{\"type\":\"ISBN_13\",\"identifier\":\"9781449393304\"}],\"readingModes\":{\"text\":false,\"image\":false},\"pageCount\":608,\"printType\":\"BOOK\",\"categories\":[\"Computers\"],\"averageRating\":3.5,\"ratingsCount\":9,\"maturityRating\":\"NOT_MATURE\",\"allowAnonLogging\":false,\"contentVersion\":\"preview-1.0.0\",\"imageLinks\":{\"smallThumbnail\":\"http://books.google.co.uk/books/content?id=9dCHkQEACAAJ&printsec=frontcover&img=1&zoom=5&source=gbs_api\",\"thumbnail\":\"http://books.google.co.uk/books/content?id=9dCHkQEACAAJ&printsec=frontcover&img=1&zoom=1&source=gbs_api\"},\"language\":\"en\",\"previewLink\":\"http://books.google.co.uk/books?id=9dCHkQEACAAJ&dq=android&hl=&cd=3&source=gbs_api\",\"infoLink\":\"http://books.google.co.uk/books?id=9dCHkQEACAAJ&dq=android&hl=&source=gbs_api\",\"canonicalVolumeLink\":\"http://books.google.co.uk/books/about/Head_First_Android_Development.html?hl=&id=9dCHkQEACAAJ\"},\"saleInfo\":{\"country\":\"GB\",\"saleability\":\"NOT_FOR_SALE\",\"isEbook\":false},\"accessInfo\":{\"country\":\"GB\",\"viewability\":\"NO_PAGES\",\"embeddable\":false,\"publicDomain\":false,\"textToSpeechPermission\":\"ALLOWED\",\"epub\":{\"isAvailable\":false},\"pdf\":{\"isAvailable\":false},\"webReaderLink\":\"http://books.google.co.uk/books/reader?id=9dCHkQEACAAJ&hl=&printsec=frontcover&output=reader&source=gbs_api\",\"accessViewStatus\":\"NONE\",\"quoteSharingAllowed\":false},\"searchInfo\":{\"textSnippet\":\"Whether you&#39;re a seasoned iPhone developer who wants to jump into the the Android Market, or someone with previous programming skills but no mobile apps in your resume, this book offers a complete learning experience for creating eye ...\"}},{\"kind\":\"books#volume\",\"id\":\"hoFI5pxjGesC\",\"etag\":\"El/j8FS6iUA\",\"selfLink\":\"https://www.googleapis.com/books/v1/volumes/hoFI5pxjGesC\",\"volumeInfo\":{\"title\":\"ANDROID A PROGRAMMERS GUIDE\",\"authors\":[\"J. F. DiMarzio\"],\"publisher\":\"McGraw Hill Professional\",\"publishedDate\":\"2008-08-14\",\"description\":\"Master the Android mobile development platform Build compelling Java-based mobile applications using the Android SDK and the Eclipse open-source software development platform. Android: A Programmer's Guide shows you, step-by-step, how to download and set up all of the necessary tools, build and tune dynamic Android programs, and debug your results. Discover how to provide web and chat functions, interact with the phone dialer and GPS devices, and access the latest Google services. You'll also learn how to create custom Content Providers and database-enable your applications using SQLite. Install and configure Java, Eclipse, and Android plugin Create Android projects from the Eclipse UI or command line Integrate web content, images, galleries, and sounds Deploy menus, progress bars, and auto-complete functions Trigger actions using Android Intents, Filters, and Receivers Implement GPS, Google Maps, Google Earth, and GTalk Build interactive SQLite databases, calendars, and notepads Test applications using the Android Emulator and Debug Bridge\",\"industryIdentifiers\":[{\"type\":\"ISBN_10\",\"identifier\":\"0071599894\"},{\"type\":\"ISBN_13\",\"identifier\":\"9780071599894\"}],\"readingModes\":{\"text\":true,\"image\":true},\"pageCount\":400,\"printType\":\"BOOK\",\"categories\":[\"Computers\"],\"averageRating\":3.0,\"ratingsCount\":11,\"maturityRating\":\"NOT_MATURE\",\"allowAnonLogging\":false,\"contentVersion\":\"2.14.5.0.preview.3\",\"imageLinks\":{\"smallThumbnail\":\"http://books.google.co.uk/books/content?id=hoFI5pxjGesC&printsec=frontcover&img=1&zoom=5&edge=curl&source=gbs_api\",\"thumbnail\":\"http://books.google.co.uk/books/content?id=hoFI5pxjGesC&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api\"},\"language\":\"en\",\"previewLink\":\"http://books.google.co.uk/books?id=hoFI5pxjGesC&printsec=frontcover&dq=android&hl=&cd=4&source=gbs_api\",\"infoLink\":\"http://books.google.co.uk/books?id=hoFI5pxjGesC&dq=android&hl=&source=gbs_api\",\"canonicalVolumeLink\":\"http://books.google.co.uk/books/about/ANDROID_A_PROGRAMMERS_GUIDE.html?hl=&id=hoFI5pxjGesC\"},\"saleInfo\":{\"country\":\"GB\",\"saleability\":\"FOR_SALE\",\"isEbook\":true,\"listPrice\":{\"amount\":31.55,\"currencyCode\":\"GBP\"},\"retailPrice\":{\"amount\":20.51,\"currencyCode\":\"GBP\"},\"buyLink\":\"http://books.google.co.uk/books?id=hoFI5pxjGesC&dq=android&hl=&buy=&source=gbs_api\",\"offers\":[{\"finskyOfferType\":1,\"listPrice\":{\"amountInMicros\":3.155E7,\"currencyCode\":\"GBP\"},\"retailPrice\":{\"amountInMicros\":2.051E7,\"currencyCode\":\"GBP\"}}]},\"accessInfo\":{\"country\":\"GB\",\"viewability\":\"PARTIAL\",\"embeddable\":true,\"publicDomain\":false,\"textToSpeechPermission\":\"ALLOWED_FOR_ACCESSIBILITY\",\"epub\":{\"isAvailable\":true,\"acsTokenLink\":\"http://books.google.co.uk/books/download/ANDROID_A_PROGRAMMERS_GUIDE-sample-epub.acsm?id=hoFI5pxjGesC&format=epub&output=acs4_fulfillment_token&dl_type=sample&source=gbs_api\"},\"pdf\":{\"isAvailable\":true,\"acsTokenLink\":\"http://books.google.co.uk/books/download/ANDROID_A_PROGRAMMERS_GUIDE-sample-pdf.acsm?id=hoFI5pxjGesC&format=pdf&output=acs4_fulfillment_token&dl_type=sample&source=gbs_api\"},\"webReaderLink\":\"http://books.google.co.uk/books/reader?id=hoFI5pxjGesC&hl=&printsec=frontcover&output=reader&source=gbs_api\",\"accessViewStatus\":\"SAMPLE\",\"quoteSharingAllowed\":false},\"searchInfo\":{\"textSnippet\":\"Master the Android mobile development platform Build compelling Java-based mobile applications using the Android SDK and the Eclipse open-source software development platform.\"}},{\"kind\":\"books#volume\",\"id\":\"EF5txSsyBFUC\",\"etag\":\"gn3y+nY61Ig\",\"selfLink\":\"https://www.googleapis.com/books/v1/volumes/EF5txSsyBFUC\",\"volumeInfo\":{\"title\":\"Beginning Android 4\",\"authors\":[\"Mark Murphy\",\"Grant Allen\"],\"publisher\":\"Apress\",\"publishedDate\":\"2012-03-15\",\"description\":\"Beginning Android 4 is an update to Beginning Android 3, originally written by Mark Murphy. It is your first step on the path to creating marketable apps for the burgeoning Android Market, Amazon's Android Appstore, and more. Google’s Android operating-system has taken the industry by storm, going from its humble beginnings as a smartphone operating system to its current status as a platform for apps that run across a gamut of devices from phones to tablets to netbooks to televisions, and the list is sure to grow. Smart developers are not sitting idly by in the stands, but are jumping into the game of creating innovative and salable applications for this fast-growing, mobile- and consumer-device platform. If you’re not in the game yet, now is your chance! Beginning Android 4 is fresh with details on the latest iteration of the Android platform. Begin at the beginning by installing the tools and compiling a skeleton app. Move through creating layouts, employing widgets, taking user input, and giving back results. Soon you’ll be creating innovative applications involving multi-touch, multi-tasking, location-based feature sets using GPS. You’ll be drawing data live from the Internet using web services and delighting your customers with life-enhancing apps. Not since the PC era first began has there been this much opportunity for the common developer. What are you waiting for? Grab your copy of Beginning Android 4 and get started!\",\"industryIdentifiers\":[{\"type\":\"ISBN_13\",\"identifier\":\"9781430239857\"},{\"type\":\"ISBN_10\",\"identifier\":\"1430239859\"}],\"readingModes\":{\"text\":true,\"image\":true},\"pageCount\":604,\"printType\":\"BOOK\",\"categories\":[\"Computers\"],\"averageRating\":4.0,\"ratingsCount\":11,\"maturityRating\":\"NOT_MATURE\",\"allowAnonLogging\":false,\"contentVersion\":\"1.4.3.0.preview.3\",\"imageLinks\":{\"smallThumbnail\":\"http://books.google.co.uk/books/content?id=EF5txSsyBFUC&printsec=frontcover&img=1&zoom=5&edge=curl&source=gbs_api\",\"thumbnail\":\"http://books.google.co.uk/books/content?id=EF5txSsyBFUC&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api\"},\"language\":\"en\",\"previewLink\":\"http://books.google.co.uk/books?id=EF5txSsyBFUC&pg=PA206&dq=android&hl=&cd=5&source=gbs_api\",\"infoLink\":\"http://books.google.co.uk/books?id=EF5txSsyBFUC&dq=android&hl=&source=gbs_api\",\"canonicalVolumeLink\":\"http://books.google.co.uk/books/about/Beginning_Android_4.html?hl=&id=EF5txSsyBFUC\"},\"saleInfo\":{\"country\":\"GB\",\"saleability\":\"FOR_SALE\",\"isEbook\":true,\"listPrice\":{\"amount\":37.8,\"currencyCode\":\"GBP\"},\"retailPrice\":{\"amount\":24.57,\"currencyCode\":\"GBP\"},\"buyLink\":\"http://books.google.co.uk/books?id=EF5txSsyBFUC&dq=android&hl=&buy=&source=gbs_api\",\"offers\":[{\"finskyOfferType\":1,\"listPrice\":{\"amountInMicros\":3.78E7,\"currencyCode\":\"GBP\"},\"retailPrice\":{\"amountInMicros\":2.457E7,\"currencyCode\":\"GBP\"}}]},\"accessInfo\":{\"country\":\"GB\",\"viewability\":\"PARTIAL\",\"embeddable\":true,\"publicDomain\":false,\"textToSpeechPermission\":\"ALLOWED\",\"epub\":{\"isAvailable\":true,\"acsTokenLink\":\"http://books.google.co.uk/books/download/Beginning_Android_4-sample-epub.acsm?id=EF5txSsyBFUC&format=epub&output=acs4_fulfillment_token&dl_type=sample&source=gbs_api\"},\"pdf\":{\"isAvailable\":true,\"acsTokenLink\":\"http://books.google.co.uk/books/download/Beginning_Android_4-sample-pdf.acsm?id=EF5txSsyBFUC&format=pdf&output=acs4_fulfillment_token&dl_type=sample&source=gbs_api\"},\"webReaderLink\":\"http://books.google.co.uk/books/reader?id=EF5txSsyBFUC&hl=&printsec=frontcover&output=reader&source=gbs_api\",\"accessViewStatus\":\"SAMPLE\",\"quoteSharingAllowed\":false},\"searchInfo\":{\"textSnippet\":\"&lt;manifest xmlns:\\u003cb\\u003eandroid\\u003c/b\\u003e=&quot;http://schemas.\\u003cb\\u003eandroid\\u003c/b\\u003e.com/apk/res/\\u003cb\\u003eandroid\\u003c/b\\u003e&quot; package\\u003cbr\\u003e\\n=&quot;com.commonsware.\\u003cb\\u003eandroid\\u003c/b\\u003e.rotation.three&quot; \\u003cb\\u003eandroid\\u003c/b\\u003e:versionCode=&quot;1&quot;\\u003cb\\u003eandroid\\u003c/b\\u003e:\\u003cbr\\u003e\\nversionName=&quot;1.0.0&quot;&gt; &lt;uses-sdk \\u003cb\\u003eandroid\\u003c/b\\u003e:minSdkVersion=&quot;5&quot;&nbsp;...\"}},{\"kind\":\"books#volume\",\"id\":\"OFXJXbCXjTgC\",\"etag\":\"1p9nBiYZsQo\",\"selfLink\":\"https://www.googleapis.com/books/v1/volumes/OFXJXbCXjTgC\",\"volumeInfo\":{\"title\":\"Android Programming\",\"subtitle\":\"The Big Nerd Ranch Guide\",\"authors\":[\"Brian Hardy\",\"Bill Phillips\"],\"publisher\":\"Addison-Wesley Professional\",\"publishedDate\":\"2013-04-09\",\"description\":\"Android Programming: The Big Nerd Ranch Guide: is an introductory Android book for programmers with Java experience. Based on Big Nerd Ranch’s popular Android Bootcamp course, this guide will lead you through the wilderness using hands-on example apps combined with clear explanations of key concepts and APIs. This book focuses on practical techniques for developing apps compatible with all versions of Android widely used today (Android 2.2 - 4.2). Write and run code every step of the way – creating apps that catalog crime scenes, browse photos, track your jogging route, and more. Each chapter and app has been designed and tested to provide the knowledge and experience you need to get started in Android development. Write and run code every step of the way — creating apps that catalog crime scenes, browse photos, track your jogging route, and more. Each chapter and app has been designed and tested to provide the knowledge and experience you need to get started in Android development. \\\"Big Nerd Ranch provided the training we needed to get hundreds of engineers building skillfully on Android. This book is a great distillation of that training and will be a huge help to anyone looking to ramp up as well.\\\" – Mike Shaver, Director of Mobile Engineering, Facebook \\\"…a must-have for the developer just starting in Android or ready for more advanced techniques. I was impressed with this book’s content and clarity of presentation. The authors explain simple and complex Android topics with equal ease.\\\" – James Steele, author of The Android Developer's Cookbook\",\"industryIdentifiers\":[{\"type\":\"ISBN_13\",\"identifier\":\"9780132869102\"},{\"type\":\"ISBN_10\",\"identifier\":\"0132869101\"}],\"readingModes\":{\"text\":true,\"image\":true},\"pageCount\":580,\"printType\":\"BOOK\",\"categories\":[\"Computers\"],\"averageRating\":4.0,\"ratingsCount\":21,\"maturityRating\":\"NOT_MATURE\",\"allowAnonLogging\":false,\"contentVersion\":\"2.6.5.0.preview.3\",\"imageLinks\":{\"smallThumbnail\":\"http://books.google.co.uk/books/content?id=OFXJXbCXjTgC&printsec=frontcover&img=1&zoom=5&edge=curl&source=gbs_api\",\"thumbnail\":\"http://books.google.co.uk/books/content?id=OFXJXbCXjTgC&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api\"},\"language\":\"en\",\"previewLink\":\"http://books.google.co.uk/books?id=OFXJXbCXjTgC&printsec=frontcover&dq=android&hl=&cd=6&source=gbs_api\",\"infoLink\":\"http://books.google.co.uk/books?id=OFXJXbCXjTgC&dq=android&hl=&source=gbs_api\",\"canonicalVolumeLink\":\"http://books.google.co.uk/books/about/Android_Programming.html?hl=&id=OFXJXbCXjTgC\"},\"saleInfo\":{\"country\":\"GB\",\"saleability\":\"FOR_SALE\",\"isEbook\":true,\"listPrice\":{\"amount\":24.91,\"currencyCode\":\"GBP\"},\"retailPrice\":{\"amount\":16.19,\"currencyCode\":\"GBP\"},\"buyLink\":\"http://books.google.co.uk/books?id=OFXJXbCXjTgC&dq=android&hl=&buy=&source=gbs_api\",\"offers\":[{\"finskyOfferType\":1,\"listPrice\":{\"amountInMicros\":2.491E7,\"currencyCode\":\"GBP\"},\"retailPrice\":{\"amountInMicros\":1.619E7,\"currencyCode\":\"GBP\"}}]},\"accessInfo\":{\"country\":\"GB\",\"viewability\":\"PARTIAL\",\"embeddable\":true,\"publicDomain\":false,\"textToSpeechPermission\":\"ALLOWED_FOR_ACCESSIBILITY\",\"epub\":{\"isAvailable\":false},\"pdf\":{\"isAvailable\":false},\"webReaderLink\":\"http://books.google.co.uk/books/reader?id=OFXJXbCXjTgC&hl=&printsec=frontcover&output=reader&source=gbs_api\",\"accessViewStatus\":\"SAMPLE\",\"quoteSharingAllowed\":false},\"searchInfo\":{\"textSnippet\":\"This book focuses on practical techniques for developing apps compatible with all versions of Android widely used today (Android 2.2 - 4.2).\"}}]}";

    @Mock
    private SearchListener responseListener;
    private HoomiGoogleBooksImp googleBooksImp;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        googleBooksImp = new HoomiGoogleBooksImp(API_KEY);
    }

    @Test
    public void testNotNull() throws Exception {
        assertNotNull(googleBooksImp);
    }

    @Test
    public void testSearchBookTitle_Mock() throws Exception {
        final boolean[] booleanArray = {false};
        MockResponse mockResponse = generateMockResponse(null, RESPONSE_BODY);
        String baseUrl = initMockWebServer(TEST_PATH, mockResponse);
        googleBooksImp = new HoomiGoogleBooksImp(API_KEY, baseUrl);
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        googleBooksImp.searchInTitle("android", new SearchListener() {
            @Override
            public void onSuccess(List<Volume> volumes) {
                assertNotNull(volumes);
                assertEquals(5, volumes.size());
                assertEquals("Best Android Apps", volumes.get(0).getTitle());
                assertEquals("Mike Hendrickson",volumes.get(0).getAuthors().get(0));
                countDownLatch.countDown();
                booleanArray[0] = true;

            }

            @Override
            public void onError(ErrorModel errorModel) {
                assertTrue("There should not have been an error", false);
                countDownLatch.countDown();

            }
        });

        countDownLatch.await(2000, TimeUnit.MILLISECONDS);

        assertTrue(booleanArray[0]);

    }

    @Test
    public void testSearchBookTitle_Actual() throws Exception {
        final String apiKey = System.getenv("GOOGLE_BOOKS_API_KEY_WEB");
        googleBooksImp = new HoomiGoogleBooksImp(apiKey);
        final boolean[] booleanArray = {false};
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        googleBooksImp.searchInTitle("android", new SearchListener() {
            @Override
            public void onSuccess(List<Volume> volumes) {
                assertNotNull(volumes);
                countDownLatch.countDown();
                booleanArray[0] = true;
            }

            @Override
            public void onError(ErrorModel errorModel) {
                assertTrue("There should not have been an error", false);
                countDownLatch.countDown();
            }

        });
        countDownLatch.await(10000, TimeUnit.MILLISECONDS);
        assertTrue(booleanArray[0]);
    }


    private String initMockWebServer(String path, MockResponse mockResponse) throws IOException {
        MockWebServer mockWebServer = new MockWebServer();
        mockWebServer.enqueue(mockResponse);
        mockWebServer.start();
        HttpUrl baseUrl = mockWebServer.url(path);
        return baseUrl.scheme() + "://" + baseUrl.host() + ":" + baseUrl.port();
    }

    private MockResponse generateMockResponse(String testStatus, String body) {
        MockResponse mockResponse = new MockResponse();
        if (testStatus != null) {
            mockResponse.setStatus(testStatus);
        }
        if (body != null) {
            mockResponse.setBody(body);
        }
        return mockResponse;
    }
}