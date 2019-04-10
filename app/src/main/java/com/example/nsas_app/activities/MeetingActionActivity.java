package com.example.nsas_app.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.nsas_app.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class MeetingActionActivity extends AppCompatActivity {

    private ArrayList<Meetingactionmodelclass> actionMeetinglist;
    TextView name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meeting_action);
        Intent intent = getIntent();
        name = findViewById(R.id.meeting_name_action);
        String nameextra = intent.getStringExtra("name");
        name.setText(nameextra);
        callingApi();
    }

    private void callingApi() {
        String url = "http://192.168.1.40:8080/api/jsonws/ns_as.meeting/get-meeting-action/user-id/20156/task-type-id/41401/task-type/Committee";
        String resposne = "[\n" +
                "  {\n" +
                "    \"assignedDate\": \"2019-04-02 06:16:09.42\",\n" +
                "    \"taskType\": \"Emergency\",\n" +
                "    \"targetDate\": \"2019-04-30 00:00:00.0\",\n" +
                "    \"taskName\": \"Emergency\",\n" +
                "    \"taskId\": \"66101\",\n" +
                "    \"assignedTo\": \"Test,Test,\",\n" +
                "    \"status\": \"Created\"\n" +
                "  }\n" +
                "]";
        try {
            checkResponse(resposne);
            name.setText(actionMeetinglist.get(0).getAssignedTo());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void checkResponse(String meeting_response) throws Exception {
        JSONArray jsonArray = new JSONArray(meeting_response);
        for (int meetingObject = 0; meetingObject < jsonArray.length(); meetingObject++) {
            Meetingactionmodelclass meetingactionmodelclass = new Meetingactionmodelclass();
            JSONObject jsonObject=jsonArray.getJSONObject(meetingObject);
            meetingactionmodelclass.setAssignedTo(jsonObject.getString("assignedTo"));


            actionMeetinglist.add(meetingactionmodelclass);
        }
    }
}