package com.example.assignment1.Fragment;


import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.assignment1.Adapter.Adapter_Reason;
import com.example.assignment1.SavedDetailsClass;
import com.example.assignment1.ServerDataClass;
import com.example.assignment1.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class DowntimeFragment extends Fragment {

    ArrayList<String> reasons;
    static LinearLayout more_reason;
    RecyclerView recyclerView;
    Adapter_Reason adapter_reason;

    View main_view;

    TextView machine_name,start_time,end_time;
    Button btn_submit;
    public  static String reason;

    ProgressDialog progressDialog;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        //Getting downtime fragment view
         main_view = inflater.inflate(R.layout.fragment_downtime, container, false);


        getReferences();

        setRecyclerView();

        setButtonClicks();

        //Setting machine name
        machine_name.setText(SavedDetailsClass.mname);


        //returning downtime fragment view
        return main_view;
    }


    //Function for button clicks
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

        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (reason==null){
                    Toast.makeText(getActivity(), "Select any reason !", Toast.LENGTH_SHORT).show();
                }
                else if(start_time.getText().toString().equals("Start Time") || end_time.getText().toString().equals("End Time") ){
                    Toast.makeText(getActivity(), "Select timings !", Toast.LENGTH_SHORT).show();

                }else{
                    new sendData().execute();
                }

            }
        });
    }


//    Class for send data to the server
    private class sendData extends AsyncTask<Void,Void,Void> {

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
            progressDialog.setMessage("Fetching Data...");
            progressDialog.setCancelable(false);
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();
    }

    @Override
        protected Void doInBackground(Void... voids) {
            //Log.e("URL==> ", GlobalData.getUrl());

            Log.e("URL DT Page==> ", ServerDataClass.getUrlDowntime());

            JSONObject params = new JSONObject();

            try {

                params.put("client_id", SavedDetailsClass.cid);
                params.put("device_id", SavedDetailsClass.did);
                params.put("machine_id", SavedDetailsClass.mid);
                params.put("machine_name", SavedDetailsClass.mname);
                params.put("start_time",start_time.getText().toString().trim()+":00");
                params.put("end_time",end_time.getText().toString().trim()+":00");
                params.put("reason",reason);

                Date time = new java.util.Date(System.currentTimeMillis());
                params.put("sent_time",""+new SimpleDateFormat("HH:mm:ss").format(time));

                Log.e("Sending Data DT==> ",params.toString());

            } catch (Exception e) {
                Toast.makeText(getActivity(), "Exception: " + e.getMessage(), Toast.LENGTH_SHORT).show();
            }


            JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, ServerDataClass.getUrlDowntime(), params, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {

                    Log.e("Response DT Page ==> ", "" + response);

                    try {
                        String status  = response.getString("status");

                        if (status.equals("1")){
                            Toast.makeText(getActivity(), "Successful", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(getActivity(), "Didn't update the data !", Toast.LENGTH_SHORT).show();
                        }

                    } catch (JSONException e) {
                        Toast.makeText(getActivity(), "Exception !", Toast.LENGTH_SHORT).show();
                    }

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                    Toast.makeText(getActivity(), "Something Went Wrong !", Toast.LENGTH_SHORT).show();
                    Log.e("Error DT Page ==> ", "" + error);
                    //progressDialog.dismiss();

                }
            });


            RequestQueue requestQueue = Volley.newRequestQueue(getContext());
            requestQueue.add(request);

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            start_time.setText("Start Time");
            end_time.setText("End Time");
            setRecyclerView();
            progressDialog.dismiss();
        }
    }



    //Function for showing time picker dialog
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
                    start_time.setTextColor(getResources().getColor(R.color.white));
                }
                else {
                    end_time.setText(text);
                    end_time.setTextColor(getResources().getColor(R.color.white));
                }


            }
        },mHour,mMinute,true);

        timePickerDialog.show();
    }


    //Function for get reference of all components
    private void getReferences() {
        btn_submit=main_view.findViewById(R.id.btnsubmit);
        recyclerView=main_view.findViewById(R.id.recycler_view_reason);
        more_reason=main_view.findViewById(R.id.more_reason);
        machine_name = main_view.findViewById(R.id.machine_name);
        start_time = main_view.findViewById(R.id.start_time);
        end_time = main_view.findViewById(R.id.end_time);
        progressDialog = new ProgressDialog(getActivity());
    }


    //Function for set Recycler View
    private void setRecyclerView() {
        setReasons();
        recyclerView.setHasFixedSize(true);
        GridLayoutManager gridLayoutManager=new GridLayoutManager(getContext(),getCols(getContext(),130),GridLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(gridLayoutManager);
        adapter_reason=new Adapter_Reason(getContext(),reasons);
        recyclerView.setAdapter(adapter_reason);
    }

    //Function Not in use now
    public static void moreArea(){

        if (reason.equals("Other")){
            more_reason.setVisibility(View.VISIBLE);
        }else{
            more_reason.setVisibility(View.GONE);
        }

    }


    //Function for setting widths according to Tablet for reasons
    public int getCols(Context context,int columnWidthDp) {
            DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();


       // Toast.makeText(context, ""+displayMetrics.toString(), Toast.LENGTH_LONG).show();
            float screenWidthDp = displayMetrics.widthPixels / displayMetrics.density;
            int noOfColumns = (int) (screenWidthDp / columnWidthDp + 0.5);
            return noOfColumns;
        }

    //Function for setting reasons for downtime
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

      //  reasons.add("Other");
    }
}