package com.example.nsas_app.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;

import com.example.nsas_app.Logger;
import com.example.nsas_app.R;
import com.example.nsas_app.adapter.AuditDetailAdapter;
import com.example.nsas_app.modelclasses.AuditDetailModelClass;
import com.example.nsas_app.modelclasses.AuditModelclass;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class AuditDetailsActivity extends AppCompatActivity {
    private static final String TAG = "AuditDetailsActivity";
    List<AuditDetailModelClass> auditDetailModelList = new ArrayList<>();
    RecyclerView detailsRecyclerview;
    private String getDetailResponse = "[\n" +
            "  {\n" +
            "    \"name\": \"The organisation has an effective process for document control.*\",\n" +
            "    \"planId\": \"64913\",\n" +
            "    \"id\": \"33701\",\n" +
            "    \"auditId\": \"35464\",\n" +
            "    \"result\": \"\",\n" +
            "    \"uploadFile\": \"\",\n" +
            "    \"scoring\": \"Complaince\",\n" +
            "    \"resultId\": \"0\",\n" +
            "    \"implimentation\": true,\n" +
            "    \"documentation\": true\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"Formats for data collection are standardized.\",\n" +
            "    \"planId\": \"64913\",\n" +
            "    \"id\": \"33702\",\n" +
            "    \"auditId\": \"35464\",\n" +
            "    \"result\": \"\",\n" +
            "    \"uploadFile\": \"\",\n" +
            "    \"scoring\": \"Complaince\",\n" +
            "    \"resultId\": \"0\",\n" +
            "    \"implimentation\": false,\n" +
            "    \"documentation\": true\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"Necessary resources are available for analysing data.\",\n" +
            "    \"planId\": \"64913\",\n" +
            "    \"id\": \"33703\",\n" +
            "    \"auditId\": \"35464\",\n" +
            "    \"result\": \"\",\n" +
            "    \"uploadFile\": \"\",\n" +
            "    \"scoring\": \"Complaince\",\n" +
            "    \"resultId\": \"0\",\n" +
            "    \"implimentation\": false,\n" +
            "    \"documentation\": false\n" +
            "  },\n" +
            "  {\n" +
            "    \"auditId\": \"35464\",\n" +
            "    \"result\": \"\",\n" +
            "    \"uploadFile\": \"\",\n" +
            "    \"scoring\": \"Complaince\",\n" +
            "    \"resultId\": \"0\",\n" +
            "    \"implimentation\": false,\n" +
            "    \"documentation\": false,\n" +
            "    \"name\": \"Documented procedures are laid down for timely and accurate dissemination of data.*\",\n" +
            "    \"planId\": \"64913\",\n" +
            "    \"id\": \"33704\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"auditId\": \"35464\",\n" +
            "    \"result\": \"\",\n" +
            "    \"uploadFile\": \"\",\n" +
            "    \"scoring\": \"Complaince\",\n" +
            "    \"resultId\": \"0\",\n" +
            "    \"implimentation\": false,\n" +
            "    \"documentation\": false,\n" +
            "    \"name\": \"Documented procedures exist for storing and retrieving data.*\",\n" +
            "    \"planId\": \"64913\",\n" +
            "    \"id\": \"33705\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"auditId\": \"35464\",\n" +
            "    \"result\": \"\",\n" +
            "    \"uploadFile\": \"\",\n" +
            "    \"scoring\": \"Complaince\",\n" +
            "    \"resultId\": \"0\",\n" +
            "    \"implimentation\": false,\n" +
            "    \"documentation\": false,\n" +
            "    \"name\": \"Appropriate clinical and managerial staff participates in selecting, integrating and using data.\",\n" +
            "    \"planId\": \"64913\",\n" +
            "    \"id\": \"33706\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"auditId\": \"35464\",\n" +
            "    \"result\": \"\",\n" +
            "    \"uploadFile\": \"\",\n" +
            "    \"scoring\": \"Complaince\",\n" +
            "    \"resultId\": \"0\",\n" +
            "    \"implimentation\": false,\n" +
            "    \"documentation\": false,\n" +
            "    \"name\": \"A documented procedure exists on how to respond to patients / physicians and other public agencies requests for access to information in the medical record in accordance with the local and national law.*\",\n" +
            "    \"planId\": \"64913\",\n" +
            "    \"id\": \"33732\"\n" +
            "  }\n" +
            "]";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audit_details);
        detailsRecyclerview = findViewById(R.id.details_recyclerview);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //  getDeatilsFrmIntents();
        callApiToGetResponseDetails(getDetailResponse);
        AuditDetailAdapter auditDetailAdapter = new AuditDetailAdapter(AuditDetailsActivity.this, auditDetailModelList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(AuditDetailsActivity.this);
        detailsRecyclerview.setLayoutManager(mLayoutManager);
        detailsRecyclerview.setItemAnimator(new DefaultItemAnimator());
        detailsRecyclerview.setAdapter(auditDetailAdapter);
    }

    private void callApiToGetResponseDetails(String getDetailResponse) {
        try {
            JSONArray jsonArray = new JSONArray(getDetailResponse);
            for (int detailsresponse = 0; detailsresponse < jsonArray.length(); detailsresponse++) {
                AuditDetailModelClass auditDetailModelClass = new AuditDetailModelClass();
                JSONObject jsonObject = jsonArray.getJSONObject(detailsresponse);
                auditDetailModelClass.setName(jsonObject.getString("name"));
                auditDetailModelClass.setPlanId(jsonObject.getString("planId"));
                auditDetailModelClass.setId(jsonObject.getString("id"));
                auditDetailModelClass.setAuditId(jsonObject.getString("auditId"));
                auditDetailModelClass.setResult(jsonObject.getString("result"));
                auditDetailModelClass.setScoring(jsonObject.getString("scoring"));
                auditDetailModelClass.setImplimentation(jsonObject.getBoolean("implimentation"));
                auditDetailModelClass.setDocumentation(jsonObject.getBoolean("documentation"));
                auditDetailModelList.add(auditDetailModelClass);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void getDeatilsFrmIntents() {
        Intent intent = getIntent();
        AuditModelclass auditModelclass = intent.getExtras().getParcelable("AUDIT_DETAIL");
        Log.d(TAG, auditModelclass.getName());

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        } else if (id == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
