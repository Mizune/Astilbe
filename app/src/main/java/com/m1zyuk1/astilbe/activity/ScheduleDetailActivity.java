package com.m1zyuk1.astilbe.activity;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.m1zyuk1.astilbe.R;
import com.m1zyuk1.astilbe.databinding.ActivityScheduleDetailBinding;

public class ScheduleDetailActivity extends AppCompatActivity { // 時計画面と工程のやつ 上部がClockFragment, 下部RecyclerView

    public ActivityScheduleDetailBinding binding;

    public static final String SCHEDULE = "schedule";

    public static Intent makeIntent(Context context, String schedule) {
        Intent intent = new Intent(context, ScheduleDetailActivity.class);
        if(!schedule.isEmpty()){
            // putExtras(schedule:bundle)
            intent.putExtra(SCHEDULE,schedule);
        }
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_schedule_detail);
        setupUi();
    }

    public void setupUi(){
        setupActionBar();
    }

    public void setupActionBar(){
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return super.onSupportNavigateUp();
    }
}
