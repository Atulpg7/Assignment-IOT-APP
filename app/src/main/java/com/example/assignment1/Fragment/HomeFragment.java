package com.example.assignment1.Fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import com.example.assignment1.R;
import java.util.ArrayList;
import java.util.List;
import lecho.lib.hellocharts.model.PieChartData;
import lecho.lib.hellocharts.model.SliceValue;
import lecho.lib.hellocharts.view.PieChartView;

public class HomeFragment extends Fragment {



    //Var for pie chart
    PieChartView chartView1,chartView2;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        //Getting main fragment view
        View view = inflater.inflate(R.layout.fragment_home, container, false);



        //Setting Pie Chart
        chartView1=view.findViewById(R.id.chart1);
        chartView2=view.findViewById(R.id.chart2);


        setChart1();
        setChart2();



        //return view
        return view;
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