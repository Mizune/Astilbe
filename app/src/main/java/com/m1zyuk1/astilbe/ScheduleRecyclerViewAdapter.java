package com.m1zyuk1.astilbe;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.m1zyuk1.astilbe.activity.ScheduleDetailActivity;

import java.util.List;

/**
 * Created by yukian on 2018/03/03.
 */

public class ScheduleRecyclerViewAdapter extends RecyclerView.Adapter<ScheduleListViewHolder>{
    private List<String> scheduleList;

    public ScheduleRecyclerViewAdapter(List<String> schedules){
        scheduleList = schedules;
    }

    @Override
    public ScheduleListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.schedule_listitem, parent,false);
        ScheduleListViewHolder viewHolder = new ScheduleListViewHolder(inflate);

        inflate.setOnClickListener(v -> {
            final int position = viewHolder.getAdapterPosition();
            parent.getContext().startActivity(ScheduleDetailActivity.makeIntent(parent.getContext(),scheduleList.get(position)));
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ScheduleListViewHolder holder, int position) {
        holder.titleView.setText(scheduleList.get(position));
    }

    @Override
    public int getItemCount() {
        return scheduleList.size();
    }
}
