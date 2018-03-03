package com.m1zyuk1.astilbe.activity;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.m1zyuk1.astilbe.R;
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
    }

    private void setupFloatingActionButton() {
        binding.addScheduleButton.setOnClickListener((view) -> {
            Toast.makeText(getApplicationContext(), "Clicked", Toast.LENGTH_SHORT).show();
            startActivity(ScheduleActivity.makeIntent(this));
        });
    }

}
