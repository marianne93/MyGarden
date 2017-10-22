package com.app.mygarden.plant_details;

import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;

import com.app.mygarden.common.base.PresenterBase;
import com.app.mygarden.plants.ViewPlants;

import static com.app.mygarden.provider.PlantContract.BASE_CONTENT_URI;
import static com.app.mygarden.provider.PlantContract.PATH_PLANTS;

/**
 * Created by Marianne.Wazif on 18-Oct-17.
 */

public class PresenterPlantDetails extends PresenterBase {
    private ViewPlantDetails viewPlantDetails;
    private Context context;
    private Fragment fragment;
    private final int PLANT_LOADER_ID = 200;
    private long plantId;

    public PresenterPlantDetails(long plantId, ViewPlantDetails viewPlantDetails, Context context, Fragment fragment) {
        this.viewPlantDetails = viewPlantDetails;
        this.context = context;
        this.fragment = fragment;
        this.plantId = plantId;
    }

    public void loadPlantDetails() {
        fragment.getLoaderManager().initLoader(PLANT_LOADER_ID, null, plantDetailsLoaderCallBacks);
    }

    private LoaderManager.LoaderCallbacks plantDetailsLoaderCallBacks = new LoaderManager.LoaderCallbacks<Cursor>() {
        @Override
        public Loader onCreateLoader(int id, Bundle args) {
            Uri SINGLE_PLANT_URI = ContentUris.withAppendedId(
                    BASE_CONTENT_URI.buildUpon().appendPath(PATH_PLANTS).build(), plantId);
            return new CursorLoader(context, SINGLE_PLANT_URI, null,
                    null, null, null);
        }

        @Override
        public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
            viewPlantDetails.onPlantsDetailsLoaded(data);
        }

        @Override
        public void onLoaderReset(Loader loader) {

        }
    };
}
