package com.m1zyuk1.astilbe.activity;

import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.m1zyuk1.astilbe.R;
import com.m1zyuk1.astilbe.ScheduleRecyclerViewAdapter;
import com.m1zyuk1.astilbe.databinding.ActivityMainBinding;
import com.m1zyuk1.astilbe.model.Schedule;
import com.m1zyuk1.astilbe.model.SerializeUtil;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<Schedule> schedules = new ArrayList<>();

    ActivityMainBinding binding;

    ScheduleRecyclerViewAdapter adapter;

    public static final int REQUEST_CREATE_SCHEDULE = 10;
    public static final String SCHEDULES_PREF = "schedules_pref";
    public static final String SCHEDULES_STRING = "schedules_string";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        initializeSchedules();
        setupUi();
    }


    private void initializeSchedules() {
        SharedPreferences data = getSharedPreferences(SCHEDULES_PREF, Context.MODE_PRIVATE);
        String schedulesRaw = data.getString(SCHEDULES_STRING, "");
        // pref見て binding
        if (schedulesRaw.isEmpty()) {
            schedules = new ArrayList();
        } else {
            schedules = SerializeUtil.toSchedules(schedulesRaw);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            //SecondActivityから戻ってきた場合
            case (REQUEST_CREATE_SCHEDULE):
                if (resultCode == ScheduleActivity.SUCCESS_CREATE_SCHEDULE) {
                    Schedule schedule = (Schedule) data.getSerializableExtra(ScheduleActivity.CREATED_SCHEDULE);
                    if (schedules.contains(schedule)) {

                    }
                    updateSchedules(schedule);//あれば更新 なければ追加
                    saveSchedules();
                }
                break;
            default:
                throw new UnsupportedOperationException("This request code does not support.");
        }
    }

    private void updateSchedules(Schedule createdSchedule) {
        for (int i = 0; i < schedules.size(); i++) {
            if (createdSchedule.getId() == schedules.get(i).getId()) {
                adapter.notifyItemChanged(i, createdSchedule);
                Log.d("Schedule", "Update schedule");
                return;
            }
        }
        schedules.add(0, createdSchedule); // add位置大丈夫か?
        adapter.notifyItemInserted(0);
        Log.d("Schedule", "Add Schedule");
        return;
    }

    private void setupUi() {
        // binding追加してonClickとか titleとか dialog初回だしとか
        setupFloatingActionButton();
        setupRecyclerView();
    }

    private void setupFloatingActionButton() {
        binding.addScheduleButton.setOnClickListener((view) -> {
            Toast.makeText(getApplicationContext(), "Clicked", Toast.LENGTH_SHORT).show();
            startActivityForResult(ScheduleActivity.makeIntent(this), REQUEST_CREATE_SCHEDULE);
        });
    }

    private void setupRecyclerView() {
        RecyclerView recyclerView = binding.recyclerView;
        adapter = new ScheduleRecyclerViewAdapter(schedules);

        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(itemDecoration);
    }

    @Deprecated
    public void insertToRecyclerView(Schedule item) {
        if (schedules != null) {
            int index = schedules.indexOf(item);
            // check whether object has or not
            if (index == -1) {
                schedules.add(0, item); // add位置大丈夫か?
                adapter.notifyItemInserted(0);
                Log.d("Schedule", "Add Schedule");
            }
        }
    }

    @Deprecated
    public void updateToRecyclerView(Schedule item) {
        if (schedules != null) {
            int index = schedules.indexOf(item);
            if (index != -1) {
                adapter.notifyItemChanged(index, item);
                Log.d("Schedule", "Update schedule");
            }
        }
    }

    @Deprecated
    public void deleteFromRecyclerView(Schedule item) {
        if (schedules != null) {
            int index = schedules.indexOf(item);
            if (-1 != index) {
                boolean isDelete = schedules.remove(item);
                if (isDelete) {
                    adapter.notifyItemRemoved(index);
                    Log.d("Schedule", "Remove Schedule");
                }
            }
        }
    }

    public void saveSchedules() {
        SharedPreferences data = getSharedPreferences(SCHEDULES_PREF, Context.MODE_PRIVATE);
        String convertedSchedules = SerializeUtil.toBase64(schedules);
        SharedPreferences.Editor editor = data.edit();
        editor.putString(SCHEDULES_STRING, convertedSchedules);
        editor.apply();
    }


}
