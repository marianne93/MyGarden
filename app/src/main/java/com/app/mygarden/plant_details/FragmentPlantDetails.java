package com.app.mygarden.plant_details;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.mygarden.R;
import com.app.mygarden.common.base.FragmentBase;
import com.app.mygarden.common.helpers.PlantUtils;
import com.app.mygarden.provider.PlantContract;

public class FragmentPlantDetails extends FragmentBase implements ViewPlantDetails {
    private long plantId;
    private Context context;

    public FragmentPlantDetails() {
        // Required empty public constructor
    }

    public static FragmentPlantDetails newInstance(long plantId) {
        FragmentPlantDetails fragment = new FragmentPlantDetails();
        Bundle args = new Bundle();
        args.putLong(ActivityPlantDetails.EXTRA_PLANT_ID, plantId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            plantId = getArguments().getLong(ActivityPlantDetails.EXTRA_PLANT_ID);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_plant_details, container, false);
        initializeViews(rootView);
        context = getActivity();
        return rootView;
    }


    @Override
    protected void initializeViews(View v) {

    }

    @Override
    protected void setListeners() {

    }

    @Override
    public void onPlantsDetailsLoaded(Cursor cursor) {
        if (cursor == null || cursor.getCount() < 1) return;
        cursor.moveToFirst();
        int createTimeIndex = cursor.getColumnIndex(PlantContract.PlantEntry.COLUMN_CREATION_TIME);
        int waterTimeIndex = cursor.getColumnIndex(PlantContract.PlantEntry.COLUMN_LAST_WATERED_TIME);
        int planTypeIndex = cursor.getColumnIndex(PlantContract.PlantEntry.COLUMN_PLANT_TYPE);

        int plantType = cursor.getInt(planTypeIndex);
        long createdAt = cursor.getLong(createTimeIndex);
        long wateredAt = cursor.getLong(waterTimeIndex);
        long timeNow = System.currentTimeMillis();

        int plantImgRes = PlantUtils.getPlantImageRes(context, timeNow - createdAt, timeNow - wateredAt, plantType);
    }
}
