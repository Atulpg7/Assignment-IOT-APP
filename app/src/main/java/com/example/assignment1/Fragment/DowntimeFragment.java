package com.example.assignment1.Fragment;


import android.content.Context;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.assignment1.Adapter.Adapter_Reason;
import com.example.assignment1.R;

import java.util.ArrayList;
import java.util.Collections;

public class DowntimeFragment extends Fragment {

    //MaterialSpinner spinner;
    ArrayList<String> reasons;
    static LinearLayout more_reason;

    RecyclerView recyclerView;
    Adapter_Reason adapter_reason;

    public  static String reason;
    Button btn_submit;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {



        //Getting downtime fragment view
        View view = inflater.inflate(R.layout.fragment_downtime, container, false);

        btn_submit=view.findViewById(R.id.btnsubmit);

        recyclerView=view.findViewById(R.id.recycler_view_reason);
        recyclerView.setHasFixedSize(true);
        GridLayoutManager gridLayoutManager=new GridLayoutManager(getContext(),getCols(getContext(),200),GridLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(gridLayoutManager);

        //Toast.makeText(getContext(), ""+getCols(getContext(),200), Toast.LENGTH_SHORT).show();



        //spinner=view.findViewById(R.id.spinner);
        more_reason=view.findViewById(R.id.more_reason);

        setReasons();

        adapter_reason=new Adapter_Reason(getContext(),reasons);


        recyclerView.setAdapter(adapter_reason);

/*
        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(getContext() ,   "Done", Toast.LENGTH_SHORT).show();


            }
        });*/

        //spinner.setItems(reasons);


        /*
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
        });*/



        //returning downtime fragment view
        return view;
    }


    public static void moreArea(){

        if (reason.equals("Other")){
            more_reason.setVisibility(View.VISIBLE);
        }else{
            more_reason.setVisibility(View.GONE);
        }

    }



    public int getCols(Context context,int columnWidthDp) {
            DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();


       // Toast.makeText(context, ""+displayMetrics.toString(), Toast.LENGTH_LONG).show();
            float screenWidthDp = displayMetrics.widthPixels / displayMetrics.density;
            int noOfColumns = (int) (screenWidthDp / columnWidthDp + 0.5);
            return noOfColumns;
        }




    private void setReasons() {

        reasons=new ArrayList<>();

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