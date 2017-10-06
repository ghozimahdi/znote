package com.blankdev.znote.util;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import com.blankdev.znote.ui.fragment.home.HomeFragment;

/**
 * Created by knalb on 17/07/17.
 */

public class ActivityUtils {
    public static void addFragment(Context context, int container, Fragment fragment) {
        ((AppCompatActivity) context).getSupportFragmentManager()
                .beginTransaction()
                .add(container, fragment)
                .commit();
    }

    public static void addFragment(Context context, int container, Fragment fragment, String TAG) {
        ((AppCompatActivity) context).getSupportFragmentManager()
                .beginTransaction()
                .add(container, fragment, TAG)
                .commit();
    }

    public static void replaceFragment(Context context, int container, Fragment fragment, String TAG) {
        ((AppCompatActivity) context).getSupportFragmentManager()
                .beginTransaction()
                .replace(container, fragment, TAG)
                .commit();
    }

    public static void replaceFragment(Context context, int container, Fragment fragment) {
        ((AppCompatActivity) context).getSupportFragmentManager()
                .beginTransaction()
                .replace(container, fragment)
                .commit();
    }

    public static void removeFragment(Context context,Fragment fragment){
        ((AppCompatActivity)context).getSupportFragmentManager()
                .beginTransaction()
                .remove(fragment)
                .commit();
    }


}
