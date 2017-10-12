package com.app.mygarden.plants;

import android.database.Cursor;

import com.app.mygarden.common.base.ViewBase;

import java.util.List;

/**
 * Created by Marianne.Wazif on 12-Oct-17.
 */

public interface ViewPlants extends ViewBase {
    void onPlantsLoaded(Cursor cursor);
}
