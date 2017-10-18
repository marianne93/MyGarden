package com.app.mygarden.plant_details;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.mygarden.R;
import com.app.mygarden.common.base.FragmentBase;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FragmentPlantDetails.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FragmentPlantDetails#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentPlantDetails extends FragmentBase {

    private OnFragmentInteractionListener mListener;
    private long plantId;

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
        return rootView;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    protected void initializeViews(View v) {

    }

    @Override
    protected void setListeners() {

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
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
