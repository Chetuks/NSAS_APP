package com.example.nsas_app.fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.example.nsas_app.R;
import com.example.nsas_app.activities.AuditActivityTabs;
import com.example.nsas_app.activities.DocumentActivity;
import com.example.nsas_app.activities.MeetingActivityTabs;
import com.example.nsas_app.activities.RegistrationPage;

import java.util.Objects;


public class DashboardFragment extends Fragment implements OnClickListener {
    Button departmentbtn, marks, attendance, notice, personaldetails, my_institution, syllabus, adduser;
    ImageView logout;
    private View rootView;

    public DashboardFragment() {
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        rootView = inflater.inflate(R.layout.fragment_registration, container, false);
        departmentbtn = (Button) rootView.findViewById(R.id.view_departmant);
        marks = (Button) rootView.findViewById(R.id.view_marks);
        attendance = (Button) rootView.findViewById(R.id.view_attendance_report);
        notice = (Button) rootView.findViewById(R.id.view_notice_board);
        my_institution = (Button) rootView.findViewById(R.id.viewmyinstitution);
        personaldetails = (Button) rootView.findViewById(R.id.personal_details);
        syllabus = (Button) rootView.findViewById(R.id.syllabus);
        adduser = (Button) rootView.findViewById(R.id.adduser);
        departmentbtn.setOnClickListener(this);
        marks.setOnClickListener(this);
        attendance.setOnClickListener(this);
        notice.setOnClickListener(this);
        my_institution.setOnClickListener(this);
        personaldetails.setOnClickListener(this);
        adduser.setOnClickListener(this);
        return rootView;
    }

    public void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(
                getActivity());
        builder.setMessage("Do you want to exit?")
                .setCancelable(false)
                .setPositiveButton("Quit",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                getActivity().finish();
                            }
                        })
                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                            }
                        });
        AlertDialog alert = builder.create();
        alert.show();
    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch (v.getId()) {
            case R.id.view_departmant:
                System.out.println("Inside switch");
                Intent newIntent1 = new Intent(getActivity(), DocumentActivity.class);
                Objects.requireNonNull(getActivity()).startActivity(newIntent1);
                break;
            case R.id.adduser:
                newIntent1 = new Intent(getActivity(), RegistrationPage.class);
                Objects.requireNonNull(getActivity()).startActivity(newIntent1);
                break;
            case R.id.view_marks:
                newIntent1 = new Intent(getActivity(), AuditActivityTabs.class);
               // startActivity(newIntent1);
                Objects.requireNonNull(getActivity()).startActivity(newIntent1);
                break;
            case R.id.view_attendance_report:
                Intent newIntent4 = new Intent(getActivity(), MeetingActivityTabs.class);
                startActivity(newIntent4);
                break;
           /* case R.id.view_notice_board:
                Intent newIntent4 = new Intent(
                        "com.prakyath.pocketcollege.VIEW_NOTICE");
                startActivity(newIntent4);
                getActivity().overridePendingTransition(R.anim.right, R.anim.left);
                break;

            case R.id.viewmyinstitution:
                Intent newIntent5 = new Intent(
                        "com.prakyath.pocketcollege.MYINSTITUTION");
                startActivity(newIntent5);
                getActivity().overridePendingTransition(R.anim.right, R.anim.left);
                break;


            case R.id.personal_details:
                Intent newIntent7 = new Intent(
                        "com.prakyath.pocketcollege.PERSONALDETAILS");
                startActivity(newIntent7);
                getActivity().overridePendingTransition(R.anim.right, R.anim.left);
                break;


            case R.id.events:
                Intent newIntent9 = new Intent(
                        "com.prakyath.pocketcollege.EVENTS");
                startActivity(newIntent9);
                getActivity().overridePendingTransition(R.anim.right, R.anim.left);
                break;

            case R.id.syllabus:
                Intent newIntent12 = new Intent(
                        "com.prakyath.pocketcollege.STUDENTSYLLABUS");
                startActivity(newIntent12);
                getActivity().overridePendingTransition(R.anim.right, R.anim.left);
                break;*/

            default:
                break;
        }
    }
}