package com.androidproject.activity.laureate;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.androidproject.R;
import com.androidproject.dbLocal.MyDatabaseHelper;
import com.androidproject.models.Laureate.Laureate;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class SchoolDashboardActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    List<Laureate> laureates;
    private ProgressBar loadingPB;
    ImageView empty_imageview;
    TextView no_data;
    private CustomLaureateAdapter customLaureateAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_school_dashboard);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("School Dashboard");
        laureates = new ArrayList<>();
        empty_imageview = findViewById(R.id.empty_imageview);
        no_data = findViewById(R.id.no_data);
        recyclerView = findViewById(R.id.recyclerView);
        loadingPB = findViewById(R.id.idPBLoading);
        getLaureates();
    }

    private void getLaureates() {
        //on below line clearing our list.
        laureates.clear();
        MyDatabaseHelper myDB = new MyDatabaseHelper(SchoolDashboardActivity.this);
        laureates = myDB.getAllLaureates();
        LaureateData.laureateList = laureates;
        loadingPB.setVisibility(View.GONE);
        storeDataInArrays();
    }


    void storeDataInArrays(){
        if (LaureateData.laureateList.isEmpty()){
            empty_imageview.setVisibility(View.VISIBLE);
            no_data.setVisibility(View.VISIBLE);
        }
        else {

                customLaureateAdapter = new CustomLaureateAdapter(SchoolDashboardActivity.this,this,
                        LaureateData.laureateList);
                recyclerView.setAdapter(customLaureateAdapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(SchoolDashboardActivity.this));
        }
    }


    public void goToAddELaureate(View view) {
        Intent intent = new Intent(SchoolDashboardActivity.this, AddLaureateActivity.class);
        startActivity(intent);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_header, menu);

        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) searchItem.getActionView();

        searchView.setImeOptions(EditorInfo.IME_ACTION_DONE);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                customLaureateAdapter.getFilter().filter(newText);
                return false;
            }
        });
        return true;
    }
}