package com.m1zyuk1.astilbe.activity;

import android.content.ClipData;
import android.content.Intent;
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

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List schedules = new ArrayList<String>();

    ActivityMainBinding binding;

    ScheduleRecyclerViewAdapter adapter;

    public static final int REQUEST_CREATE_SCHEDULE = 10;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        initializeSchedules();
        setupUi();

    }

    // TODO recyclerViewを持たせる
    // TODO とりあえずStringデータが表示できるようにする
    //

    private void initializeSchedules() {
        // pref見て binding
        if (schedules == null) {
            schedules = new ArrayList();
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
                    updateToRecyclerView(schedule);
                    insertToRecyclerView(schedule);//あれば更新 なければ追加
                    // この方法だと多分更新が上手くいかん(元々の状態と更新後の状態持って来て　前の状態で検索かければいけるか)
                }
                break;
            default:
                throw new UnsupportedOperationException("This request code does not support.");
        }
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

    public void insertToRecyclerView(Schedule item) {
        if (schedules != null) {
            int index = schedules.indexOf(item);
            // check whether object has or not
            if (index == -1) {
                schedules.add(0, item); // add位置大丈夫か?
                adapter.notifyItemInserted(0);
                Log.d("Schedule","Add Schedule");
            }
        }
    }

    public void updateToRecyclerView(Schedule item) {
        if (schedules != null) {
            int index = schedules.indexOf(item);
            if (index != -1) {
                adapter.notifyItemChanged(index, item);
                Log.d("Schedule","Update schedule");
            }
        }
    }

    public void deleteFromRecyclerView(Schedule item) {
        if (schedules != null) {
            int index = schedules.indexOf(item);
            if (-1 != index) {
                boolean isDelete = schedules.remove(item);
                if (isDelete) {
                    adapter.notifyItemRemoved(index);
                    Log.d("Schedule","Remove Schedule");
                }
            }
        }
    }


}
