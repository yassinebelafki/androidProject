package com.androidproject.activity.laureate.experience;

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

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private Context context;
    private Activity activity;
    private ArrayList  experience_titles, experience_start_dates, experience_end_dates;

    CustomAdapter(Activity activity, Context context,
                  ArrayList experience_titles, ArrayList experience_start_dates,
                  ArrayList experience_end_dates){
        this.activity = activity;
        this.context = context;
//        this.experience_ids = experience_ids;
        this.experience_titles = experience_titles;
        this.experience_start_dates = experience_start_dates;
        this.experience_end_dates = experience_end_dates;
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
//        holder.experience_id.setText(String.valueOf(experience_ids.get(position)));
        holder.experience_title.setText(String.valueOf(experience_titles.get(position)));
        holder.experience_start_date.setText("from : " +  String.valueOf(experience_start_dates.get(position)));
        holder.experience_end_date.setText("to : " +String.valueOf(experience_end_dates.get(position)));
        //Recyclerview onClickListener
        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, EditExperienceActivity.class);
//                intent.putExtra("experience_id", String.valueOf(experience_ids.get(position)));
                intent.putExtra("experience_title", String.valueOf(experience_titles.get(position)));
                intent.putExtra("experience_start_date", String.valueOf(experience_start_dates.get(position)));
                intent.putExtra("experience_end_date", String.valueOf(experience_end_dates.get(position)));
                activity.startActivityForResult(intent, 1);
                ((Activity) context).finish();
            }
        });
    }

    @Override
    public int getItemCount() {
        return experience_titles.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView experience_id, experience_title, experience_start_date, experience_end_date;
        LinearLayout mainLayout;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);
//            experience_id = itemView.findViewById(R.id.experience_id);
            experience_title = itemView.findViewById(R.id.experience_title);
            experience_start_date = itemView.findViewById(R.id.experience_start_date);
            experience_end_date = itemView.findViewById(R.id.experience_end_date);
            mainLayout = itemView.findViewById(R.id.mainLayout);
            //Animate Recyclerview
            Animation translate_anim = AnimationUtils.loadAnimation(context, R.anim.translate_anim);
            mainLayout.setAnimation(translate_anim);
        }

    }

}
