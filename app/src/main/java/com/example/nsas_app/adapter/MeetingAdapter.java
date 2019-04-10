package com.example.nsas_app.adapter;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.nsas_app.Logger;
import com.example.nsas_app.R;
import com.example.nsas_app.activities.MeetingActionActivity;
import com.example.nsas_app.activities.Meetingmodelclass;

import java.util.List;

public class MeetingAdapter extends RecyclerView.Adapter<MeetingAdapter.MyViewHolder> {
    FragmentActivity activity;
    List<Meetingmodelclass> meetingmodelclassList;

    public MeetingAdapter(FragmentActivity activity, List<Meetingmodelclass> meetingmodelclassList) {
        this.activity = activity;
        this.meetingmodelclassList = meetingmodelclassList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(activity).inflate(R.layout.current_meeting_list_row, viewGroup, false);
        return new MeetingAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, final int i) {
        final int position = i;
        Meetingmodelclass meetingmodelclass = meetingmodelclassList.get(i);
        if (!meetingmodelclass.getMeetingname().isEmpty()) {
            myViewHolder.meetingname.setText(meetingmodelclass.getMeetingname());
        }
        myViewHolder.meetingdate.setText(meetingmodelclass.getMeetingdate());
        myViewHolder.meetingtime.setText(meetingmodelclass.getMeetingtime());
        myViewHolder.meetingstatus.setText(meetingmodelclass.getMeetingStatus());
        myViewHolder.meetingtype.setText(meetingmodelclass.getMeetingtype());

        myViewHolder.meetingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = meetingmodelclassList.get(position).getMeetingId();
                Logger.logD("idmeeting", id);
                Intent intent = new Intent(activity, MeetingActionActivity.class);
                intent.putExtra("name", meetingmodelclassList.get(position).getMeetingname());
                activity.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return meetingmodelclassList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView meetingname, meetingtime, meetingstatus, meetingdate, meetingtype;
        Button meetingBtn;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            meetingname = itemView.findViewById(R.id.meeting_name);
            meetingtime = itemView.findViewById(R.id.meeting_time);
            meetingstatus = itemView.findViewById(R.id.meeting_status);
            meetingdate = itemView.findViewById(R.id.meeting_date);
            meetingBtn = itemView.findViewById(R.id.meeting_btn);
            meetingtype = itemView.findViewById(R.id.meeting_type_text);
        }
    }
}
