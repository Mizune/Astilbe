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

    private void setupUi() {
        // TODO switching mode
        // if schedules == empty or not
        // binding. hogehoge
        Intent intent = getIntent();
        String schedule = intent.getStringExtra(SCHEDULE);
        if (schedule != null) {
            // データをセット
            Toast.makeText(getApplicationContext(), "Activity launched, mode = edit", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getApplicationContext(), "Activity launched, mode = create", Toast.LENGTH_SHORT).show();
        }
        setupActionBar();
        setupOnClickListener();
        setupText();
    }

    private void setupActionBar(){
        setTitle(R.string.schedule_edit);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    private void setupOnClickListener(){
        binding.scheduleDepartureTimeForm.itemView.setOnClickListener(v -> {
            Toast.makeText(getApplicationContext(), "Select departure time form", Toast.LENGTH_SHORT).show();
        });

        binding.scheduleToDepartureTimeForm.itemView.setOnClickListener(v -> {
            Toast.makeText(getApplicationContext(), "Select to station time form", Toast.LENGTH_SHORT).show();
        });

        binding.scheduleDepartureStationNameForm.itemView.setOnClickListener(v -> {
            Toast.makeText(getApplicationContext(), "Select departure station name form", Toast.LENGTH_SHORT).show();
        });

        binding.scheduleArrivalStationNameForm.itemView.setOnClickListener(v -> {
            Toast.makeText(getApplicationContext(), "Select arrival station name form",Toast.LENGTH_SHORT).show();
        });

        binding.scheduleFromArrivalStationTimeForm.itemView.setOnClickListener(v -> {
            Toast.makeText(getApplicationContext(), "Select from arrival station", Toast.LENGTH_SHORT).show();
        });

        binding.schedulePushNotificationToggleForm.itemView.setOnClickListener(v -> {
            Toast.makeText(getApplicationContext(), "Select push notification toggle", Toast.LENGTH_SHORT).show();
        });

        binding.scheduleNotificationSoundForm.itemView.setOnClickListener(v -> {
            Toast.makeText(getApplicationContext(), "Select notification sound", Toast.LENGTH_SHORT).show();
        });

        binding.scheduleVibrationToggleForm.itemView.setOnClickListener(v -> {
            Toast.makeText(getApplicationContext(), "Select vibration toggle", Toast.LENGTH_SHORT).show();
        });
    }

    private void setupText(){
        binding.scheduleDepartureTimeForm.formTitle.setText(R.string.schedule_departure_time);
        binding.scheduleToDepartureTimeForm.formTitle.setText(R.string.schedule_to_departure_time);
        binding.scheduleDepartureStationNameForm.formTitle.setText(R.string.schedule_departure_station_name);

        binding.scheduleArrivalStationNameForm.formTitle.setText(R.string.schedule_arrival_station_name);
        binding.scheduleFromArrivalStationTimeForm.formTitle.setText(R.string.schedule_from_arrival_time);

        binding.schedulePushNotificationToggleForm.formTitle.setText(R.string.schedule_push_notification);
        binding.scheduleNotificationSoundForm.formTitle.setText(R.string.schedule_notification_sound);
        binding.scheduleVibrationToggleForm.formTitle.setText(R.string.schdule_vibration);
    }

    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return super.onSupportNavigateUp();
    }


}
