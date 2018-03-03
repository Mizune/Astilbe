package com.m1zyuk1.astilbe;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

/**
 * Created by yukian on 2018/03/03.
 */

public class ScheduleListViewHolder extends RecyclerView.ViewHolder {
    public TextView titleView;
    public ScheduleListViewHolder(View itemView) {
        super(itemView);
        titleView = itemView.findViewById(R.id.schedule_title);
    }


}
