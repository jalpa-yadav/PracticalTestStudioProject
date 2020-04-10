package com.practicaltest;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.fragments.FragmentA;
import com.fragments.FragmentB;
import com.fragments.FragmentC;

public class NavigationControllerActivity extends AppCompatActivity implements FragmentA.OnFragmentInteractionListener, FragmentB.OnFragmentInteractionListener, FragmentC.OnFragmentInteractionListener {
     @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_controller);
        Log.e("Activity","NavigationControllerActivity");
    }


    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
