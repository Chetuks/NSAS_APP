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

import com.example.nsas_app.R;
import com.example.nsas_app.activities.AuditDetailsActivity;
import com.example.nsas_app.modelclasses.AuditModelclass;

import java.util.List;

public class CurrentAuditAdapter extends RecyclerView.Adapter<CurrentAuditAdapter.MyViewHolder> {

    FragmentActivity activity;
    List<AuditModelclass> currentAuditList;

    public CurrentAuditAdapter(FragmentActivity activity, List<AuditModelclass> currentAuditList) {
        this.activity = activity;
        this.currentAuditList = currentAuditList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(activity).inflate(R.layout.current_audit_list_row, viewGroup, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, final int i) {
        AuditModelclass auditModelclass= currentAuditList.get(i);
        if (!auditModelclass.getName().isEmpty()){
            myViewHolder.auditname.setText(auditModelclass.getName());
        }
            myViewHolder.duration.setText(auditModelclass.getDuration());
            myViewHolder.auditbtn.setText(auditModelclass.getStatus());

        myViewHolder.auditbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(activity, AuditDetailsActivity.class);
                intent.putExtra("AUDIT_DETAIL",currentAuditList.get(i));
                activity.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return currentAuditList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView auditname;
        private TextView duration;
        private Button auditbtn;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            auditname = itemView.findViewById(R.id.currentnamedisplay);
            duration = itemView.findViewById(R.id.duration_text);
            auditbtn = itemView.findViewById(R.id.auditing_btn);
        }
    }
}
