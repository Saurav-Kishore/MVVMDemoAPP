package com.example.mvvmdemoapp;

import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import com.example.mvvmdemoapp.view.ListFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import static com.example.mvvmdemoapp.util.constants.Constants.NAME_LIST_FRAGMENT_TAG;
import static com.example.mvvmdemoapp.util.constants.Constants.NUMBER_LIST_FRAGMENT_TAG;

public class MainActivity extends AppCompatActivity {

    private ListFragment mNameListFragment;
    private ListFragment mNumberListFragment;
    private FloatingActionButton mFab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        createFragments();
        findViewById(R.id.fab).setOnClickListener(view -> onButtonClick(view));
    }

    private void onButtonClick(View view) {
        mNameListFragment.sortDataByName();
    }

    private void createFragments() {
        mNameListFragment = (ListFragment) getFragment(NAME_LIST_FRAGMENT_TAG);
        mNumberListFragment = (ListFragment) getFragment(NUMBER_LIST_FRAGMENT_TAG);
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        if (mNameListFragment == null) {
            mNameListFragment = new ListFragment(NAME_LIST_FRAGMENT_TAG);
            fragmentTransaction.add(R.id.name_list_fragment, mNameListFragment, NAME_LIST_FRAGMENT_TAG);
        }
        if (mNumberListFragment == null) {
            mNumberListFragment = new ListFragment(NUMBER_LIST_FRAGMENT_TAG);
            fragmentTransaction.add(R.id.number_list_fragment, mNumberListFragment, NUMBER_LIST_FRAGMENT_TAG);
        }
        fragmentTransaction.commit();
    }

    private Fragment getFragment(String fragmentTag) {
        return getSupportFragmentManager().findFragmentByTag(fragmentTag);
    }
}