package com.example.assignment1.Fragment;


import android.app.TimePickerDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.assignment1.R;
import com.jaredrummler.materialspinner.MaterialSpinner;

import java.util.ArrayList;
import java.util.Calendar;

public class ProductionFragment extends Fragment {


    MaterialSpinner spinner_operator;
    EditText other_operator;
    View view;
    TextView start_time,end_time;
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //Getting Production fragment view
        view = inflater.inflate(R.layout.fragment_production, container, false);
        
        getReferences();
        
        setSpinner();

        spinner_operator.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, Object item) {

                if(item.equals("Other")){
                    other_operator.setVisibility(View.VISIBLE);
                }else {
                    other_operator.setVisibility(View.GONE);
                }

            }
        });


        start_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                openTimePicker("start");

            }
        });



        end_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                openTimePicker("end");

            }
        });

        //Returning Production fragment view
        return view;
    }



    //Time Picker Dialog
    private void openTimePicker(final String btn) {

        final Calendar c = Calendar.getInstance();
        final int mHour = c.get(Calendar.HOUR_OF_DAY);
        final int mMinute = c.get(Calendar.MINUTE);

        TimePickerDialog timePickerDialog = new TimePickerDialog(getContext(), new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int i, int i1) {

                String am_pm="AM";

                if(i>12 && i<24) am_pm="PM";

                String text = i + " : " + String.format("%02d", i1) + " " + am_pm;

                if (btn.equals("start")) {
                    start_time.setText(text);
                    start_time.setTextColor(getResources().getColor(R.color.black));
                }
                else {
                    end_time.setText(text);
                    end_time.setTextColor(getResources().getColor(R.color.black));
                }


            }
        },mHour,mMinute,false);

        timePickerDialog.show();

    }




    //Spinner for Operators
    private void setSpinner() {

        ArrayList<String> operators=new ArrayList<>();

        operators.add("Operator 1");
        operators.add("Operator 2");
        operators.add("Operator 3");
        operators.add("Operator 4");
        operators.add("Operator 5");
        operators.add("Other");

        spinner_operator.setItems(operators);
    }



//    Getting references of all input fields
    private void getReferences() {
        
        spinner_operator=view.findViewById(R.id.spinner_operator);
        other_operator = view.findViewById(R.id.other_operator);
        start_time=view.findViewById(R.id.txt_start_time);
        end_time=view.findViewById(R.id.txt_end_time);
    }

}
