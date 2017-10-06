package com.blankdev.znote.ui.activity;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.blankdev.znote.R;
import com.blankdev.znote.ui.fragment.create.CreateNoteFragment;
import com.blankdev.znote.ui.fragment.home.HomeFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(savedInstanceState == null){

            FragmentManager fragmentManager = getSupportFragmentManager();

            fragmentManager.beginTransaction()
                    .add(R.id.container,new HomeFragment(),HomeFragment.class.getSimpleName())
                    .commit();
        }
    }
}
