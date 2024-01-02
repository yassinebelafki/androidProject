package com.androidproject.activity.laureate.skill;

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
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.androidproject.R;
import com.androidproject.activity.laureate.experience.EditExperienceActivity;

import java.util.ArrayList;

public class CustomSkillAdapter extends RecyclerView.Adapter<CustomSkillAdapter.MyViewHolder> {

    private Context context;
    private Activity activity;
    private ArrayList skill_names, skill_types;

    CustomSkillAdapter(Activity activity, Context context,
                       ArrayList skill_names, ArrayList skill_types){
        this.activity = activity;
        this.context = context;
        this.skill_names = skill_names;
        this.skill_types = skill_types;
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
        holder.skill_name.setText(String.valueOf(skill_names.get(position)));
        holder.skill_type.setText("type : " +  String.valueOf(skill_types.get(position)));
        //Recyclerview onClickListener
        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, EditSkills.class);
//                intent.putExtra("experience_id", String.valueOf(experience_ids.get(position)));
                intent.putExtra("skill_name", String.valueOf(skill_names.get(position)));
                intent.putExtra("skill_type", String.valueOf(skill_types.get(position)));
                activity.startActivityForResult(intent, 1);
            }
        });
    }

    @Override
    public int getItemCount() {
        return skill_names.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView skill_name , skill_type;
        LinearLayout mainLayout;


        MyViewHolder(@NonNull View itemView) {
            super(itemView);
//            experience_id = itemView.findViewById(R.id.experience_id);
            skill_name = itemView.findViewById(R.id.experience_title);
            skill_type = itemView.findViewById(R.id.experience_start_date);
            mainLayout = itemView.findViewById(R.id.mainLayout);
            //Animate Recyclerview
            Animation translate_anim = AnimationUtils.loadAnimation(context, R.anim.translate_anim);
            mainLayout.setAnimation(translate_anim);
        }

    }

}
