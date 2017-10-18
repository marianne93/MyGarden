package com.app.mygarden.add_plants;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;

import com.app.mygarden.R;
import com.app.mygarden.common.base.ActivityBase;
import com.app.mygarden.plants.FragmentPlants;
import com.app.mygarden.provider.PlantContract;

public class ActivityAddPlant extends ActivityBase implements FragmentAddPlant.OnAddPlantFragmentInteractionListener {
    private Toolbar toolbar;

    public static void startActivity(Context context) {
        Intent i = new Intent(context, ActivityAddPlant.class);
        context.startActivity(i);
    }

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
        setToolbar(toolbar, getString(R.string.title_activity_add_plant), false, R.color.primary, true);
    }

    @Override
    protected void setListeners() {

    }

    @Override
    protected void loadFragment() {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frmFragmentContainer, FragmentAddPlant.newInstance()).commit();
    }

    @Override
    public void onAddPlant(int plantType) {
        long timeNow = System.currentTimeMillis();
        // Insert the new plant into DB
        ContentValues contentValues = new ContentValues();
        contentValues.put(PlantContract.PlantEntry.COLUMN_PLANT_TYPE, plantType);
        contentValues.put(PlantContract.PlantEntry.COLUMN_CREATION_TIME, timeNow);
        contentValues.put(PlantContract.PlantEntry.COLUMN_LAST_WATERED_TIME, timeNow);
        getContentResolver().insert(PlantContract.PlantEntry.CONTENT_URI, contentValues);
        // Close this activity
        finish();
    }
}
