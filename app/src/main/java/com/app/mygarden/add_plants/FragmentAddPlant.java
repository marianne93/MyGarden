package com.app.mygarden.add_plants;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.mygarden.R;
import com.app.mygarden.common.base.FragmentBase;
import com.app.mygarden.plants.PlantsListAdapter;

import static android.support.v7.widget.LinearLayoutManager.*;

public class FragmentAddPlant extends FragmentBase {

    private OnAddPlantFragmentInteractionListener onAddPlantFragmentInteractionListener;
    private Context context;
    private RecyclerView rvPlantType;
    private FloatingActionButton btnBack;
    private PlantTypesAdapter plantTypesAdapter;

    public FragmentAddPlant() {
        // Required empty public constructor
    }

    public static FragmentAddPlant newInstance() {
        FragmentAddPlant fragment = new FragmentAddPlant();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_add_plant, container, false);
        context = getActivity();
        initializeViews(rootView);
        setListeners();
        initRecyclerView();
        return rootView;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnAddPlantFragmentInteractionListener) {
            onAddPlantFragmentInteractionListener = (OnAddPlantFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement onAddPlantFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        onAddPlantFragmentInteractionListener = null;
    }

    @Override
    protected void initializeViews(View v) {
        rvPlantType = (RecyclerView) v.findViewById(R.id.rvPlantType);
        btnBack = (FloatingActionButton) v.findViewById(R.id.btnBack);
    }

    @Override
    protected void setListeners() {
        btnBack.setOnClickListener(btnBackClickListener);
    }

    private View.OnClickListener btnBackClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            ((Activity) context).finish();
        }
    };

    private void initRecyclerView() {
        plantTypesAdapter = new PlantTypesAdapter(context, onAddPlantFragmentInteractionListener);
        rvPlantType.setLayoutManager(
                new LinearLayoutManager(context, VERTICAL, false)
        );
        rvPlantType.setAdapter(plantTypesAdapter);
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnAddPlantFragmentInteractionListener {
        void onAddPlant(int plantType);
    }
}
