package com.example.nsas_app.adapter;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.nsas_app.R;
import com.example.nsas_app.activities.AuditDetailsActivity;
import com.example.nsas_app.activities.CustomDialogClass;
import com.example.nsas_app.modelclasses.AuditDetailModelClass;

import java.util.List;

public class AuditDetailAdapter extends RecyclerView.Adapter<AuditDetailAdapter.MyViewHolder> {
    private AuditDetailsActivity auditDetailsActivity;
    private List<AuditDetailModelClass> auditDetailModelList;

    public AuditDetailAdapter(AuditDetailsActivity auditDetailsActivity, List<AuditDetailModelClass> auditDetailModelList) {
        this.auditDetailsActivity = auditDetailsActivity;
        this.auditDetailModelList = auditDetailModelList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(auditDetailsActivity).inflate(R.layout.detail_row_audit, viewGroup, false);
        return new AuditDetailAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        final int position=i;
        AuditDetailModelClass auditDetailModelClass = auditDetailModelList.get(i);
        if (!auditDetailModelClass.getName().isEmpty()){
            myViewHolder.detailname.setText(auditDetailModelClass.getName());
            myViewHolder.scoring.setText(auditDetailModelClass.getScoring());
        }
        myViewHolder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CustomDialogClass customdialog = new CustomDialogClass(auditDetailsActivity, auditDetailModelList, position);
                customdialog.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return auditDetailModelList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView scoring;
        private TextView detailname;
        private Button view, save;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            detailname = itemView.findViewById(R.id.details_name);
            view = itemView.findViewById(R.id.view_btn);
            scoring = itemView.findViewById(R.id.scoring);
        }
    }
}
