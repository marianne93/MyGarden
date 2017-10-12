package com.app.mygarden.plants;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.mygarden.R;
import com.app.mygarden.common.helpers.PlantUtils;
import com.app.mygarden.provider.PlantContract;
import com.app.mygarden.provider.PlantContract.PlantEntry;

/**
 * Created by Marianne.Wazif on 12-Oct-17.
 */

public class PlantsListAdapter extends RecyclerView.Adapter<PlantsListAdapter.PlantViewHolder> {
    private Context context;
    private Cursor cursor;

    public PlantsListAdapter(Context context, Cursor cursor) {
        this.context = context;
        this.cursor = cursor;
    }

    @Override
    public PlantViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_plant, parent, false);
        return new PlantViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PlantViewHolder holder, int position) {
        cursor.moveToPosition(position);
        int idIndex = cursor.getColumnIndex(PlantEntry._ID);
        int createTimeIndex = cursor.getColumnIndex(PlantEntry.COLUMN_CREATION_TIME);
        int waterTimeIndex = cursor.getColumnIndex(PlantEntry.COLUMN_LAST_WATERED_TIME);
        int plantTypeIndex = cursor.getColumnIndex(PlantEntry.COLUMN_PLANT_TYPE);

        long plantId = cursor.getLong(idIndex);
        int plantType = cursor.getInt(plantTypeIndex);
        long createdAt = cursor.getLong(createTimeIndex);
        long wateredAt = cursor.getLong(waterTimeIndex);
        long timeNow = System.currentTimeMillis();

        int imgRes = PlantUtils.getPlantImageRes(context, timeNow - createdAt, timeNow - wateredAt, plantType);
        holder.imgPlant.setImageResource(imgRes);
        holder.txtPlantName.setText(String.valueOf(plantId));
    }

    public void swapCursor(Cursor newCursor) {
        if (cursor != null) {
            cursor.close();
        }
        cursor = newCursor;
        if (cursor != null) {
            // Force the RecyclerView to refresh
            this.notifyDataSetChanged();
        }
    }

    @Override
    public int getItemCount() {
        if (cursor == null) return 0;
        return cursor.getCount();
    }

    public class PlantViewHolder extends RecyclerView.ViewHolder {
        private View view;
        private ImageView imgPlant;
        private TextView txtPlantName;

        public PlantViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            imgPlant = (ImageView) itemView.findViewById(R.id.imgPlant);
            txtPlantName = (TextView) itemView.findViewById(R.id.txtPlantName);
        }
    }
}
