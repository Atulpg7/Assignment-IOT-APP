package com.example.assignment1.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.assignment1.Fragment.DowntimeFragment;
import com.example.assignment1.R;


import java.util.ArrayList;

public class Adapter_Reason extends RecyclerView.Adapter<Adapter_Reason.ViewHolder> {

    Context context;
    ArrayList<String> reasons;
    View oldView;

    public Adapter_Reason(Context context, ArrayList<String> reasons) {
        this.context = context;
        this.reasons = reasons;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.custom_grid_layout,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {

        final String title=reasons.get(position);

        holder.reason.setText(title);

       holder.reason.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {

               if(oldView!=null){
               oldView.setBackgroundColor(context.getResources().getColor(R.color.card));
               }
               view.setBackgroundColor(context.getResources().getColor(R.color.pd_main));


               DowntimeFragment.reason=holder.reason.getText().toString();
               DowntimeFragment.moreArea();

               oldView=view;


           }
       });


    }

    @Override
    public int getItemCount() {
        return reasons.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView reason;
        CardView cardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            reason=itemView.findViewById(R.id.reason);
            cardView=itemView.findViewById(R.id.cardView);
        }
    }
}
