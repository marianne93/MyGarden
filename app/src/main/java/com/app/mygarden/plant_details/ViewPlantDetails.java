package com.app.mygarden.plant_details;

import android.database.Cursor;

import com.app.mygarden.common.base.ViewBase;

/**
 * Created by Marianne.Wazif on 18-Oct-17.
 */

public interface ViewPlantDetails extends ViewBase {
    void onPlantsDetailsLoaded(Cursor cursor);
}
