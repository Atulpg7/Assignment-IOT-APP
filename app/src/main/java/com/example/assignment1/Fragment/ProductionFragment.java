package com.example.assignment1.Fragment;


import android.app.TimePickerDialog;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.assignment1.GeneralClass;
import com.example.assignment1.GlobalData;
import com.example.assignment1.R;
import com.jaredrummler.materialspinner.MaterialSpinner;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class ProductionFragment extends Fragment {


    MaterialSpinner spinner_operator;
    View main_view;
    TextView start_time,end_time;
    EditText job_name,shift_target,rejection_count,other_operator;
    Button btn_submit;

    boolean isOther = false;
    String operator_name;
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //Getting Production fragment view
        main_view = inflater.inflate(R.layout.fragment_production, container, false);
        
        getReferences();

        setButtonClicks();

        setSpinner();



        //Returning Production fragment view
        return main_view;
    }

    private void setButtonClicks() {

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

        spinner_operator.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, Object item) {

                if(item.equals("Other")){
                    other_operator.setVisibility(View.VISIBLE);
                    isOther = true;
                }else {
                    isOther = false;
                    other_operator.setVisibility(View.GONE);
                    operator_name=item.toString();
                }

            }
        });

        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String job_n = job_name.getText().toString();
                String shift_t = shift_target.getText().toString();
                String rej_co = rejection_count.getText().toString();
                String start_t = start_time.getText().toString();
                String end_t = end_time.getText().toString();



                if(checkLen(job_n) || checkLen(shift_t) || checkLen(rej_co)){
                    Toast.makeText(getActivity(), "Fill all details !", Toast.LENGTH_SHORT).show();
                }else if(start_t.equals("Select Start Time") || end_t.equals("Select End Time")){
                    Toast.makeText(getActivity(), "Select time !", Toast.LENGTH_SHORT).show();
                }else{

                    if(isOther) {
                        operator_name = other_operator.getText().toString();
                    }

                    if (checkLen(operator_name)) {
                        Toast.makeText(getActivity(), "Enter Operator name", Toast.LENGTH_SHORT).show();
                    }else{
                        new sendData().execute();
                    }


                }


            }
        });

    }


    private class sendData extends AsyncTask<Void,Void,Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            Log.e("URL==> ", GlobalData.getUrl());

            StringRequest request = new StringRequest(Request.Method.POST, GlobalData.getUrl(), new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {

                    //progressDialog.setMessage("Fetching Data");

                    Log.e("Response ==> ",""+response);


                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                    Toast.makeText(getActivity(), "Something Went Wrong !", Toast.LENGTH_SHORT).show();
                    Log.e("Error ==> ",""+error);

                }
            }){
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {

                    HashMap<String,String> params = new HashMap<>();

                    params.put("client_id", GeneralClass.cid);
                    params.put("device_id",GeneralClass.did);
                    params.put("machine_id",GeneralClass.mid);
                    params.put("machine_name",GeneralClass.mname);
                    params.put("job_name",job_name.getText().toString());
                    params.put("rejection_count",rejection_count.getText().toString().trim());
                    params.put("shift_target",shift_target.getText().toString().trim());
                    params.put("shift_start_time",start_time.getText().toString().trim());
                    params.put("shift_end_time",end_time.getText().toString().trim());
                    params.put("operator_name",operator_name);

                    Log.e("Sending Data==> ",params.toString());
                    return params;
                }
            };

            RequestQueue requestQueue = Volley.newRequestQueue(getContext());
            requestQueue.add(request);

            return null;
        }
    }


    private boolean checkLen(String s) {
        return  s==null || s.equals("");
    }


    //Time Picker Dialog
    private void openTimePicker(final String btn) {

        final Calendar c = Calendar.getInstance();
        final int mHour = c.get(Calendar.HOUR_OF_DAY);
        final int mMinute = c.get(Calendar.MINUTE);

        TimePickerDialog timePickerDialog = new TimePickerDialog(getContext(), new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int i, int i1) {


                String text = i + ":" + String.format("%02d", i1);

                if (btn.equals("start")) {
                    start_time.setText(text);
                    start_time.setTextColor(getResources().getColor(R.color.black));
                }
                else {
                    end_time.setText(text);
                    end_time.setTextColor(getResources().getColor(R.color.black));
                }


            }
        },mHour,mMinute,true);

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

        operator_name=operators.get(0);
    }



//    Getting references of all input fields
    private void getReferences() {

        job_name = main_view.findViewById(R.id.job_name);
        shift_target = main_view.findViewById(R.id.shift_target);
        rejection_count = main_view.findViewById(R.id.rejection_count);
        btn_submit= main_view.findViewById(R.id.btn_submit);
        
        spinner_operator=main_view.findViewById(R.id.spinner_operator);
        other_operator = main_view.findViewById(R.id.other_operator);
        start_time=main_view.findViewById(R.id.txt_start_time);
        end_time=main_view.findViewById(R.id.txt_end_time);
    }

}
