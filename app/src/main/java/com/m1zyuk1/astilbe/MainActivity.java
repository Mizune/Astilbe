package com.m1zyuk1.astilbe;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    List schedules = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupUi();
    }

    // TODO recyclerViewを持たせる
    // TODO DataBinding使えるようにする
    // TODO とりあえずStringデータが表示できるようにする
    //

    private void initializeSchedules(){
        // pref見て binding
    }

    private void setupUi(){
        // binding追加してonClickとか titleとか dialog初回だしとか
        setupFloatingActionButton();
    }

    private void setupFloatingActionButton(){
        FloatingActionButton fab = findViewById(R.id.add_schedule_button);
        fab.setOnClickListener((view) -> {
            Toast.makeText(getApplicationContext(),"Clicked",Toast.LENGTH_SHORT).show();
        });
    }

}
