package com.example.assignment1.Fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.assignment1.R;

public class DowntimeFragment extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {



        //Getting downtime fragment view
        View view = inflater.inflate(R.layout.fragment_downtime, container, false);



        //returning downtime fragment view
        return view;
    }
}