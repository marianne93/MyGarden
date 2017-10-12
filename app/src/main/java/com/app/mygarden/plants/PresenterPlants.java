package com.app.mygarden.plants;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;

import com.app.mygarden.common.base.PresenterBase;
import com.app.mygarden.provider.PlantContract;

import static com.app.mygarden.provider.PlantContract.BASE_CONTENT_URI;
import static com.app.mygarden.provider.PlantContract.PATH_PLANTS;

/**
 * Created by Marianne.Wazif on 12-Oct-17.
 */

public class PresenterPlants extends PresenterBase {
    private ViewPlants viewPlants;
    private Context context;
    private final int GARDEN_LOADER_ID = 100;
    private Fragment fragment;


    public PresenterPlants(Context context, ViewPlants viewPlants, Fragment fragment) {
        this.viewPlants = viewPlants;
        this.context = context;
        this.fragment = fragment;
    }

    public void loadPlants() {
        fragment.getLoaderManager().initLoader(GARDEN_LOADER_ID, null, plantsLoaderCallBacks);
    }

    private LoaderManager.LoaderCallbacks plantsLoaderCallBacks = new LoaderManager.LoaderCallbacks<Cursor>() {
        @Override
        public Loader onCreateLoader(int id, Bundle args) {
            Uri PLANT_URI = BASE_CONTENT_URI.buildUpon().appendPath(PATH_PLANTS).build();
            return new CursorLoader(context, PLANT_URI, null,
                    null, null, PlantContract.PlantEntry.COLUMN_CREATION_TIME);
        }

        @Override
        public void onLoadFinished(Loader loader, Cursor cursor) {
            viewPlants.onPlantsLoaded(cursor);
        }

        @Override
        public void onLoaderReset(Loader loader) {

        }
    };
}
