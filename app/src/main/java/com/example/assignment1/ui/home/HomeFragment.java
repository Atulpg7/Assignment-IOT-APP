package com.example.assignment1.ui.home;

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


    PieChartView pieChartView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);


        pieChartView=view.findViewById(R.id.chart);

        List pieData = new ArrayList<>();
        pieData.add(new SliceValue(70, Color.parseColor("#FF5722")));
        pieData.add(new SliceValue(30, Color.parseColor("#E91E63")));

        PieChartData pieChartData = new PieChartData(pieData);
        pieChartData.setHasLabels(true).setValueLabelTextSize(14);
        pieChartData.setHasCenterCircle(true).setCenterText1("OEE 30%")
                .setCenterText1FontSize(30).setCenterText1Color(Color.parseColor("#FF5722"));
        pieChartView.setPieChartData(pieChartData);



        return view;
    }
}