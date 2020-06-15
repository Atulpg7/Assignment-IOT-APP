package com.example.assignment1.Fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.assignment1.R;
import com.jaredrummler.materialspinner.MaterialSpinner;

import java.util.ArrayList;
import java.util.Collections;

public class DowntimeFragment extends Fragment {

    MaterialSpinner spinner;
    ArrayList<String> reasons;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {



        //Getting downtime fragment view
        View view = inflater.inflate(R.layout.fragment_downtime, container, false);

        spinner=view.findViewById(R.id.spinner);

        setReasons();

        spinner.setItems(reasons);



        //returning downtime fragment view
        return view;
    }

    private void setReasons() {

        reasons=new ArrayList<>();


        reasons.add("--- Select Option ---");
        reasons.add("Absent");
        reasons.add("Breakdown");
        reasons.add("Cleaning");
        reasons.add("Water");
        reasons.add("Team Break");
        reasons.add("Setup");
        reasons.add("No Load");
        reasons.add("S-Feeding");
        reasons.add("No Power");
        reasons.add("Lunch");
        reasons.add("Gauge Not Available");
        reasons.add("Personal Need");
        reasons.add("Waiting for Instruction");
        reasons.add("No man Power");

        //Soring the list
        Collections.sort(reasons);

        reasons.add("Other");
    }
}