package com.m1zyuk1.astilbe;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.m1zyuk1.astilbe.databinding.ActivityScheduleDetailBinding;

public class ScheduleDetailActivity extends AppCompatActivity {

    public ActivityScheduleDetailBinding binding;

    public final String SCHEDULE = "schedule";

    public Intent makeIntent(String schedule) {
        Intent intent = new Intent(this, ScheduleActivity.class);
        if(!schedule.isEmpty()){
            // putExtras(schedule:bundle)
            intent.putExtra(SCHEDULE,schedule);
        }
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_schedule_detail);
    }
}
