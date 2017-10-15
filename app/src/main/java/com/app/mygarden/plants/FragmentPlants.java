package com.app.mygarden.plants;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.mygarden.R;
import com.app.mygarden.common.base.FragmentBase;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnPlantsFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FragmentPlants#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentPlants extends FragmentBase implements ViewPlants {
    private OnPlantsFragmentInteractionListener mListener;
    private final int GARDEN_LOADER_ID = 100;
    private Context context;
    private PresenterPlants presenterPlants;
    private RecyclerView rvPlants;
    private PlantsListAdapter plantsListAdapter;

    public FragmentPlants() {
        // Required empty public constructor
    }

    public static FragmentPlants newInstance() {
        return new FragmentPlants();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_plants, container, false);
        initializeViews(rootView);
        initRecyclerView();
        context = getActivity();
        presenterPlants = new PresenterPlants(context, this, FragmentPlants.this);
        return rootView;
    }

    private void initRecyclerView() {
        rvPlants.setLayoutManager(
                new GridLayoutManager(context, 4)
        );
        plantsListAdapter = new PlantsListAdapter(context, null);
        rvPlants.setAdapter(plantsListAdapter);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        loadPlants();
    }

    private void loadPlants() {
        presenterPlants.loadPlants();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnPlantsFragmentInteractionListener) {
            mListener = (OnPlantsFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnPlantsFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    protected void initializeViews(View v) {
        rvPlants = (RecyclerView) v.findViewById(R.id.rvPlants);
    }

    @Override
    protected void setListeners() {

    }


    @Override
    public  void onPlantsLoaded(Cursor cursor) {
        if (cursor != null) {
            cursor.moveToFirst();
            plantsListAdapter.swapCursor(cursor);
        }
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
    public interface OnPlantsFragmentInteractionListener {
        void onPlantSelected(Uri uri);
    }
}
