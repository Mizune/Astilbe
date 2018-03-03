package com.m1zyuk1.astilbe.activity;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.m1zyuk1.astilbe.R;
import com.m1zyuk1.astilbe.ScheduleRecyclerViewAdapter;
import com.m1zyuk1.astilbe.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List schedules = new ArrayList<String>();
    ActivityMainBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        setupUi();
    }

    // TODO recyclerViewを持たせる
    // TODO とりあえずStringデータが表示できるようにする
    //

    private void initializeSchedules() {
        // pref見て binding
    }

    private void setupUi() {
        // binding追加してonClickとか titleとか dialog初回だしとか
        setupFloatingActionButton();
        setupRecyclerView();
    }

    private void setupFloatingActionButton() {
        binding.addScheduleButton.setOnClickListener((view) -> {
            Toast.makeText(getApplicationContext(), "Clicked", Toast.LENGTH_SHORT).show();
            startActivity(ScheduleActivity.makeIntent(this));
        });
    }

    private void setupRecyclerView() {
        RecyclerView recyclerView = binding.recyclerView;
        ScheduleRecyclerViewAdapter adapter = new ScheduleRecyclerViewAdapter(this.createDataset());

        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(itemDecoration);
    }

    // TODO use only debug
    private List<String> createDataset() {
        List<String> schedules = new ArrayList<>();
        schedules.add("Test1");
        schedules.add("Test2");
        schedules.add("Test1");
        schedules.add("Test2");
        schedules.add("Test1");
        schedules.add("Test2");
        schedules.add("Test1");
        schedules.add("Test2");
        schedules.add("Test1");
        schedules.add("Test2");
        schedules.add("Test1");
        schedules.add("Test2");
        return schedules;
    }

}
