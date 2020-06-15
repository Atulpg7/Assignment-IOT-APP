package com.example.assignment1.Fragment;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.assignment1.R;

public class ProductionFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //Getting Production fragment view
        View view= inflater.inflate(R.layout.fragment_production, container, false);


        //Returning Production fragment view
        return view;
    }

}
