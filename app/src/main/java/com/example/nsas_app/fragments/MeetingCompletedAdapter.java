package com.example.nsas_app.fragments;

import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.nsas_app.R;

import java.util.List;

public class MeetingCompletedAdapter extends RecyclerView.Adapter<MeetingCompletedAdapter.MyViewHolder> {
    FragmentActivity activity;
    List<MeetingCompletedmodelclass> completedmeetinglist;

    public MeetingCompletedAdapter(FragmentActivity activity, List<MeetingCompletedmodelclass> completedmeetinglist) {
        this.activity=activity;
        this.completedmeetinglist=completedmeetinglist;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(activity).inflate(R.layout.current_meeting_list_row, viewGroup, false);
        return new MeetingCompletedAdapter.MyViewHolder(itemView);    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        MeetingCompletedmodelclass meetingmodelclass=completedmeetinglist.get(i);
        if (!meetingmodelclass.getMeetingname().isEmpty()){
            myViewHolder.meetingname.setText(meetingmodelclass.getMeetingname());
        }
        myViewHolder.meetingdate.setText(meetingmodelclass.getMeetingdate());
        myViewHolder.meetingtime.setText(meetingmodelclass.getMeetingtime());
        myViewHolder.meetingstatus.setText(meetingmodelclass.getMeetingStatus());
    }

    @Override
    public int getItemCount() {
        return completedmeetinglist.size();
    }

    public  class MyViewHolder extends RecyclerView.ViewHolder{
        TextView meetingname,meetingtime,meetingstatus,meetingdate;
        Button meetingBtn;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            meetingname=itemView.findViewById(R.id.meeting_name);
            meetingtime=itemView.findViewById(R.id.meeting_time);
            meetingstatus=itemView.findViewById(R.id.meeting_status);
            meetingdate=itemView.findViewById(R.id.meeting_date);
            meetingBtn=itemView.findViewById(R.id.meeting_btn);
        }
    }
}
