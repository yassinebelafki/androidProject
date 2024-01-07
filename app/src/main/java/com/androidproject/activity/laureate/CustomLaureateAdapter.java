package com.androidproject.activity.laureate;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.androidproject.R;
import com.androidproject.activity.laureate.experience.EditExperienceActivity;

import java.util.ArrayList;

public class CustomLaureateAdapter extends RecyclerView.Adapter<CustomLaureateAdapter.MyViewHolder> {

    private Context context;
    private Activity activity;
    private ArrayList laureateIds,laureateNames, laureateTraining, laureateCity;

    CustomLaureateAdapter(Activity activity, Context context,ArrayList laureateIds,
                          ArrayList laureateNames, ArrayList laureateTraining,
                          ArrayList laureateCity){
        this.activity = activity;
        this.context = context;
        this.laureateIds = laureateIds;
        this.laureateNames = laureateNames;
        this.laureateTraining = laureateTraining;
        this.laureateCity = laureateCity;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row, parent, false);
        return new MyViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        holder.laureate_id.setText(String.valueOf(laureateIds.get(position)));
        holder.laureate_name.setText(String.valueOf(laureateNames.get(position)));
        holder.laureate_training.setText("Training : " +  String.valueOf(laureateTraining.get(position)));
        holder.laureate_city.setText("city : " +String.valueOf(laureateCity.get(position)));
        //Recyclerview onClickListener
        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, LaureateDetailsActivity.class);
                intent.putExtra("laureate_id", String.valueOf(laureateIds.get(position)));
//                intent.putExtra("laureate_name", String.valueOf(laureateNames.get(position)));
//                intent.putExtra("laureate_training", String.valueOf(laureateTraining.get(position)));
//                intent.putExtra("laureate_city", String.valueOf(laureateCity.get(position)));
                activity.startActivityForResult(intent, 1);
               //  ((Activity) context).finish();
            }
        });
    }

    @Override
    public int getItemCount() {
        return laureateIds.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView laureate_id,laureate_name, laureate_training, laureate_city;
        LinearLayout mainLayout;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            laureate_id = itemView.findViewById(R.id.element_id);
            laureate_name = itemView.findViewById(R.id.element_title);
            laureate_training = itemView.findViewById(R.id.element_sub_title);
            laureate_city = itemView.findViewById(R.id.element_sub_title2);
            mainLayout = itemView.findViewById(R.id.mainLayout);
            //Animate Recyclerview
            Animation translate_anim = AnimationUtils.loadAnimation(context, R.anim.translate_anim);
            mainLayout.setAnimation(translate_anim);
        }

    }

}
