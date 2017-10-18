package com.app.mygarden.add_plants;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.mygarden.R;
import com.app.mygarden.add_plants.FragmentAddPlant.OnAddPlantFragmentInteractionListener;
import com.app.mygarden.common.helpers.PlantUtils;

/**
 * Created by Marianne.Wazif on 16-Oct-17.
 */

public class PlantTypesAdapter extends RecyclerView.Adapter<PlantTypesAdapter.PlantViewHolder> {
    private Context context;
    private TypedArray plantTypes;
    private OnAddPlantFragmentInteractionListener addPlantFragmentInteractionListener;

    public PlantTypesAdapter(Context context, OnAddPlantFragmentInteractionListener addPlantFragmentInteractionListener) {
        this.context = context;
        Resources resources = context.getResources();
        plantTypes = resources.obtainTypedArray(R.array.plant_types);
        this.addPlantFragmentInteractionListener = addPlantFragmentInteractionListener;
    }

    @Override
    public PlantViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Get the RecyclerView item layout
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_plant_type, parent, false);
        return new PlantViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final PlantViewHolder holder, int position) {
        int imgRes = PlantUtils.getPlantImgRes(
                context, position,
                PlantUtils.PlantStatus.ALIVE,
                PlantUtils.PlantSize.FULLY_GROWN);
        holder.imgPlantType.setImageResource(imgRes);
        holder.txtPlantType.setText(PlantUtils.getPlantTypeName(context, position));
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (addPlantFragmentInteractionListener != null)
                    addPlantFragmentInteractionListener.onAddPlant(holder.getAdapterPosition());
            }
        });
    }


    @Override
    public int getItemCount() {
        if (plantTypes == null) return 0;
        return plantTypes.length();
    }

    class PlantViewHolder extends RecyclerView.ViewHolder {
        private ImageView imgPlantType;
        private TextView txtPlantType;
        private View view;

        PlantViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            imgPlantType = (ImageView) itemView.findViewById(R.id.imgPlantType);
            txtPlantType = (TextView) itemView.findViewById(R.id.txtPlantType);
        }
    }
}
