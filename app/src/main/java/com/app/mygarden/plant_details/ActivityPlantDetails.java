package com.app.mygarden.plant_details;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.app.mygarden.R;
import com.app.mygarden.add_plants.ActivityAddPlant;
import com.app.mygarden.add_plants.FragmentAddPlant;
import com.app.mygarden.common.base.ActivityBase;
import com.app.mygarden.provider.PlantContract;

public class ActivityPlantDetails extends ActivityBase {
    public static final String EXTRA_PLANT_ID = "plant_id";
    private long plantId;

    public static void startActivity(Context context, int plantId) {
        Intent intent = new Intent(context, ActivityPlantDetails.class);
        intent.putExtra(EXTRA_PLANT_ID, plantId);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plant_details);
        if (getIntent() != null)
            plantId = getIntent().getLongExtra(EXTRA_PLANT_ID, PlantContract.INVALID_PLANT_ID);
        loadFragment();
    }

    @Override
    protected void initializeViews() {

    }

    @Override
    protected void setListeners() {

    }

    @Override
    protected void loadFragment() {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frmFragmentContainer, FragmentPlantDetails.newInstance( plantId)).commit();
    }
}
