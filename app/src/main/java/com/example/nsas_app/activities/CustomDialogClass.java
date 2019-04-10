package com.example.nsas_app.activities;

import android.app.Activity;
import android.app.Dialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import com.example.nsas_app.Logger;
import com.example.nsas_app.R;
import com.example.nsas_app.modelclasses.AuditDetailModelClass;

import java.util.ArrayList;
import java.util.List;

public class CustomDialogClass extends Dialog implements android.view.View.OnClickListener {
    public Activity activity;
    public Button save, update;
    Spinner scoringdialog;
    private EditText dName;
    private Switch docDialog, implementDialog;
    List<AuditDetailModelClass> auditDetailModelList;
    int auditDetailModelListposition;
    AuditDetailModelClass auditSubModelClass;
    List<String> mySpinnerList = new ArrayList<String>();

    public CustomDialogClass(Activity activity, List<AuditDetailModelClass> auditDetailModelList, int auditDetailModelClasslistposition) {
        super(activity);
        // TODO Auto-generated constructor stub
        this.activity = activity;
        this.auditDetailModelList = auditDetailModelList;
        this.auditDetailModelListposition = auditDetailModelClasslistposition;
        this.auditSubModelClass = auditDetailModelList.get(auditDetailModelClasslistposition);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_custom_dialog_class);
        init();
        validationandSetModel();
        save.setOnClickListener(this);
    }

    private void validationandSetModel() {
        if (!auditSubModelClass.getName().isEmpty())
            dName.setText(auditSubModelClass.getName());
        docDialog.setChecked(auditSubModelClass.isDocumentation());
        implementDialog.setChecked(auditSubModelClass.isImplimentation());
       String score= auditSubModelClass.getScoring();
       Logger.logD("score",score);

        mySpinnerList.add("0 compilence");
        mySpinnerList.add("5 compilence");
        mySpinnerList.add("10 compilence");

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(activity, android.R.layout.simple_spinner_item, mySpinnerList);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        scoringdialog.setAdapter(dataAdapter);
        scoringdialog.setGravity(Gravity.CENTER_HORIZONTAL);

        /*
            updated code form Avinash Raj checking the preselected values and setting the same.
         */
        updatePreSelectedSpinnervalue();

    }

    private void updatePreSelectedSpinnervalue() {
        for (int i=0;i<mySpinnerList.size();i++){
            if (mySpinnerList.get(i).equalsIgnoreCase(auditSubModelClass.getScoring())){
                scoringdialog.setSelection(i);
            }
        }

    }

    private void init() {
        save = findViewById(R.id.save_btn);
        dName = findViewById(R.id.details_name_dialog);
        docDialog = findViewById(R.id.doc_switch_dialog);
        implementDialog = findViewById(R.id.imple_switch_dialog);
        scoringdialog = findViewById(R.id.spinner_dialog);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.save_btn:
                Logger.logD("namecheckinglist", dName.getText().toString());
                Logger.logD("bolean", "" + implementDialog.isChecked());
                auditSubModelClass.setName(dName.getText().toString());
                auditSubModelClass.setDocumentation(docDialog.isChecked());
                auditSubModelClass.setImplimentation(implementDialog.isChecked());
                /*
                    Updated the code from avinash updated the dropdown values to model class.

                 */
                auditSubModelClass.setScoring(mySpinnerList.get(scoringdialog.getSelectedItemPosition()));
                Toast.makeText(activity,auditSubModelClass.getScoring()+"",Toast.LENGTH_SHORT).show();
                auditDetailModelList.add(auditSubModelClass);
                callingSaveUpdateApi();
                break;
            default:
                break;
        }
        dismiss();
    }

    private void callingSaveUpdateApi() {
        String response="[\n" +
                "  {\n" +
                "    \"result\": \"record saved successfully\"\n" +
                "  }\n" +
                "]";
        String url1="http://192.168.1.33:8080/api/jsonws/ns_as.auditresultfinal/save-or-update-autiting/json-object1/%5B%5D/user-id/20156/org-id/35400/save/true/update/true\n";
    }
}

