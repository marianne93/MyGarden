package com.app.mygarden.plants;

import android.net.Uri;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.app.mygarden.R;
import com.app.mygarden.common.base.ActivityBase;

public class ActivityPlants extends ActivityBase implements FragmentPlants.OnPlantsFragmentInteractionListener {
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_with_toolbar_fragment);
        initializeViews();
        if (savedInstanceState == null) {
            loadFragment();
        }
    }

    @Override
    protected void initializeViews() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setToolbar(toolbar, getString(R.string.title_activity_my_garden), false, R.color.primary, true);
    }

    @Override
    protected void setListeners() {

    }

    @Override
    protected void loadFragment() {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frmFragmentContainer, FragmentPlants.newInstance()).commit();
    }

    @Override
    public void onPlantSelected(Uri uri) {

    }
}
