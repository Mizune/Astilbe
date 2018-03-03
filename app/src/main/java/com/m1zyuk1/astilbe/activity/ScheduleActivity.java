package com.m1zyuk1.astilbe.activity;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.m1zyuk1.astilbe.R;
import com.m1zyuk1.astilbe.databinding.ActivityScheduleBinding;

public class ScheduleActivity extends AppCompatActivity {

    private ActivityScheduleBinding binding;

    public static final String SCHEDULE = "schedule";

    public static Intent makeIntent(Context context) {
        return makeIntent(context, "");
    }

    public static Intent makeIntent(Context context,@Nullable String schedule) {
        Intent intent = new Intent(context, ScheduleActivity.class);
        if (!schedule.isEmpty()) {
            // putExtras(schedule:bundle)
            intent.putExtra(SCHEDULE, schedule);
        }
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_schedule);
        setupUi();
    }

    public void setupUi() {
        // TODO switching mode
        // if schedules == empty or not
        // binding. hogehoge
        Intent intent = getIntent();
        String schedule = intent.getStringExtra(SCHEDULE);
        if (schedule != null) {
            Toast.makeText(getApplicationContext(), "Activity launched, mode = edit", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getApplicationContext(), "Activity launched, mode = create", Toast.LENGTH_SHORT).show();
        }
    }


}
