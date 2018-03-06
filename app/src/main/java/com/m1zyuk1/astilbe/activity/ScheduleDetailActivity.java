package com.m1zyuk1.astilbe.activity;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.m1zyuk1.astilbe.R;
import com.m1zyuk1.astilbe.databinding.ActivityScheduleDetailBinding;
import com.m1zyuk1.astilbe.model.Schedule;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScheduleDetailActivity extends AppCompatActivity { // 時計画面と工程のやつ 上部がClockFragment, 下部RecyclerView

    public ActivityScheduleDetailBinding binding;

    public static final String SCHEDULE = "schedule";

    private Schedule schedule;

    public static Intent makeIntent(Context context, Schedule schedule) {
        Intent intent = new Intent(context, ScheduleDetailActivity.class);
        if (!schedule.getTitle().isEmpty()) {
            intent.putExtra(SCHEDULE, schedule);
        }
        return intent;
    }
    // menu edit button(2 type), delete button

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_schedule_detail);
        Intent data = getIntent();
        schedule = (Schedule) data.getSerializableExtra(SCHEDULE);
        setupUi();
    }

    public void setupUi() {
        setupActionBar();
        binding.scheduleDetailTitle.setText(schedule.getTitle());
        final DateFormat df = new SimpleDateFormat("HH:mm:ss");
        final Date date = new Date(System.currentTimeMillis());
        binding.clockRoot.clockTime.setText(df.format(date));
    }

    public void setupActionBar() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_schedule_detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.edit_button:
                Toast.makeText(getApplicationContext(), "Select edit button.", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.delete_button:
                Toast.makeText(getApplicationContext(), "Select delete button.", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
