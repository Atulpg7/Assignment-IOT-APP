package com.example.assignment1.Fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.assignment1.R;
import com.jaredrummler.materialspinner.MaterialSpinner;

import java.util.ArrayList;
import java.util.Collections;

public class DowntimeFragment extends Fragment {

    MaterialSpinner spinner;
    ArrayList<String> reasons;
    LinearLayout more_reason;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {



        //Getting downtime fragment view
        View view = inflater.inflate(R.layout.fragment_downtime, container, false);

        spinner=view.findViewById(R.id.spinner);
        more_reason=view.findViewById(R.id.more_reason);

        setReasons();

        spinner.setItems(reasons);


        spinner.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, Object item) {


                if(!item.toString().equals("--- Select Option ---")){
                    spinner.setTextColor(getResources().getColor(R.color.downtime_color));
                }else{
                    spinner.setTextColor(getResources().getColor(R.color.grey));
                }

                if (item.toString().equals("Other")){
                    more_reason.setVisibility(View.VISIBLE);
                }else{
                    more_reason.setVisibility(View.GONE);
                }

            }
        });



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