package com.example.assignment1.Fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.assignment1.LoginActivity;
import com.example.assignment1.MainActivity;
import com.example.assignment1.MyPrefs;
import com.example.assignment1.R;
import com.example.assignment1.SettingsActivity;

import java.util.ArrayList;
import java.util.List;
import lecho.lib.hellocharts.model.PieChartData;
import lecho.lib.hellocharts.model.SliceValue;
import lecho.lib.hellocharts.view.PieChartView;

public class HomeFragment extends Fragment {



    //Var for pie chart
    PieChartView chartView1,chartView2;
    View main_view;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {



        //Getting main fragment view
        main_view = inflater.inflate(R.layout.fragment_home, container, false);


        //Setting Pie Chart
        chartView1=main_view.findViewById(R.id.chart1);
        chartView2=main_view.findViewById(R.id.chart2);


        checkAdminLogin();
        setChart1();
        setChart2();



        //return view
        return main_view;
    }


    private void checkAdminLogin() {

        MyPrefs.prefs = getActivity().getSharedPreferences(MyPrefs.MY_PREF_NAME,getActivity().MODE_PRIVATE);
        String isAdmin = MyPrefs.prefs.getString("isAdmin","false");

        //Toast.makeText(getActivity(), ""+isAdmin, Toast.LENGTH_SHORT).show();

        if (isAdmin.equals("true")){
            main_view.findViewById(R.id.configure_settings_layout).setVisibility(View.VISIBLE);
            main_view.findViewById(R.id.settings_icon).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getActivity(), SettingsActivity.class);
                    startActivity(intent);
                }
            });

        }
    }

    private void setChart2() {


        //Making partitions how many partitions required in pie chart
        List pieData = new ArrayList<>();
        pieData.add(new SliceValue(44, getResources().getColor(R.color.not_active)));
        pieData.add(new SliceValue(56, getResources().getColor(R.color.active)));


        //Loading the data in pie chart
        PieChartData pieChartData = new PieChartData(pieData);
        pieChartData.setHasLabels(true).setValueLabelTextSize(10);

        //Setting Circle in center of pie-chart, and heading in center.
        pieChartData.setHasCenterCircle(true).setCenterText1("PRODUCTION 56%")
                .setCenterText1FontSize(10).setCenterText1Color(Color.parseColor("#FF5722"));
        chartView2.setPieChartData(pieChartData);
        chartView2.setChartRotationEnabled(false);
    }

    private void setChart1() {

        //Making partitions how many partitions required in pie chart
        List pieData = new ArrayList<>();
        pieData.add(new SliceValue(46, getResources().getColor(R.color.not_active)));
        pieData.add(new SliceValue(54, getResources().getColor(R.color.active)));


        //Loading the data in pie chart
        PieChartData pieChartData = new PieChartData(pieData);
        pieChartData.setHasLabels(true).setValueLabelTextSize(10);

        //Setting Circle in center of pie-chart, and heading in center.
        pieChartData.setHasCenterCircle(true).setCenterText1("OEE 54%")
                .setCenterText1FontSize(10).setCenterText1Color(Color.parseColor("#FF5722"));
        chartView1.setPieChartData(pieChartData);
        chartView1.setChartRotationEnabled(false);
    }
}