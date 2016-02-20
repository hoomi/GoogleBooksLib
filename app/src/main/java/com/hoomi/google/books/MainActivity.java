package com.hoomi.google.books;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.hoomi.books.lib.model.Volume;
import com.hoomi.google.books.ui.fragment.BookDetailsFragment;
import com.hoomi.google.books.ui.fragment.MainActivityFragment;

public class MainActivity extends AppCompatActivity implements NavigationHandler {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
        replaceFragment(MainActivityFragment.class, null, false);
    }

    @Override
    public void switchToDetails(String volumeId) {
        Bundle bundle = new Bundle();
        bundle.putString(BookDetailsFragment.VOLUME_ID, volumeId);
        replaceFragment(BookDetailsFragment.class, bundle, true);
    }

    private void replaceFragment(Class fragmentClass, Bundle bundle, boolean addToBackstack) {
        Fragment fragment = Fragment.instantiate(this, fragmentClass.getName(), bundle);
        FragmentTransaction fragmentTransaction = getSupportFragmentManager()
                .beginTransaction().replace(R.id.fragmentPlace, fragment);
        if (addToBackstack) {
            fragmentTransaction.addToBackStack(fragmentClass.getName());
        }
        fragmentTransaction.commit();
    }
}
