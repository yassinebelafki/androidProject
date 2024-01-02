package com.androidproject.activity.laureate.interest;

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

public class CustomInterestAdapter extends RecyclerView.Adapter<CustomInterestAdapter.MyViewHolder> {

    private Context context;
    private Activity activity;
    private ArrayList  interest_names;

    CustomInterestAdapter(Activity activity, Context context,
                          ArrayList interest_names){
        this.activity = activity;
        this.context = context;
//        this.experience_ids = experience_ids;
        this.interest_names = interest_names;
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
        holder.interest_name.setText(String.valueOf(interest_names.get(position)));
        //Recyclerview onClickListener
        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, EditInterest.class);
//                intent.putExtra("experience_id", String.valueOf(experience_ids.get(position)));
                intent.putExtra("interest_name", String.valueOf(interest_names.get(position)));
                activity.startActivityForResult(intent, 1);
            }
        });
    }

    @Override
    public int getItemCount() {
        return interest_names.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView interest_name;
        LinearLayout mainLayout;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);
//            experience_id = itemView.findViewById(R.id.experience_id);
            interest_name = itemView.findViewById(R.id.experience_title);
            mainLayout = itemView.findViewById(R.id.mainLayout);
            //Animate Recyclerview
            Animation translate_anim = AnimationUtils.loadAnimation(context, R.anim.translate_anim);
            mainLayout.setAnimation(translate_anim);
        }

    }

}
