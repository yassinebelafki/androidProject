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
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.androidproject.R;
import com.androidproject.activity.laureate.experience.EditExperienceActivity;
import com.androidproject.models.Laureate.Laureate;

import java.util.ArrayList;
import java.util.List;

public class CustomLaureateAdapter extends RecyclerView.Adapter<CustomLaureateAdapter.MyViewHolder> implements Filterable {

    private Context context;
    private Activity activity;

    private List<Laureate> laureateList;
    private List<Laureate> laureateListFull;
    CustomLaureateAdapter(Activity activity, Context context,List<Laureate> laureateList){
        this.activity = activity;
        this.context = context;
        this.laureateList = laureateList;
        this.laureateListFull = new ArrayList<>(laureateList);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_laureate_row, parent, false);
        return new MyViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        holder.laureate_id.setText(String.valueOf(laureateList.get(position).getId()));
        holder.laureate_name.setText(String.valueOf(laureateList.get(position).getName()));
        holder.laureate_training.setText("Training : " +  String.valueOf(laureateList.get(position).getTraining()));
        holder.laureate_city.setText("city : " +String.valueOf(laureateList.get(position).getCity()));
        //Recyclerview onClickListener
        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, LaureateDetailsActivity.class);
                intent.putExtra("laureate_id", String.valueOf(laureateList.get(position).getId()));
                activity.startActivityForResult(intent, 1);
               //  ((Activity) context).finish();
            }
        });
    }

    @Override
    public int getItemCount() {
        return laureateList.size();
    }

    @Override
    public Filter getFilter() {
        return exampleFilter;
    }

    private Filter exampleFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<Laureate> filteredList = new ArrayList<>();

            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(laureateListFull);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();

                for (Laureate item : laureateListFull) {
                    if (item.getName().toLowerCase().contains(filterPattern)) {
                        filteredList.add(item);
                    }
                }
            }

            FilterResults results = new FilterResults();
            results.values = filteredList;

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            laureateList.clear();
            laureateList.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };


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
