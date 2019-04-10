package com.example.nsas_app.activities;

import android.support.design.widget.TabLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Toast;

import com.example.nsas_app.R;
import com.example.nsas_app.adapter.MeetingAdapter;
import com.example.nsas_app.fragments.AuditingResponse;
import com.example.nsas_app.fragments.MeetingResponseFragment;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MeetingActivityTabs extends AppCompatActivity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meeting_tabs);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);

        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_meeting_activity_tabs, menu);
        return true;
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
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";
        private RecyclerView meeting_recycler;
        private List<Meetingmodelclass> currentMeetinglist = new ArrayList<>();
        private String MEETING_RESPONSE = "[\n" +
                "  {\n" +
                "    \"meetingTime\": \"9:00 AM\",\n" +
                "    \"duration\": \"1\",\n" +
                "    \"meetingName\": \"Quality Improvement Meeting\",\n" +
                "    \"minutes\": \"Should Follow Quality parameters as per provided in manual.\",\n" +
                "    \"meetingDate\": \"2019-02-01 11:07:33.769\",\n" +
                "    \"description\": \"\",\n" +
                "    \"scheduleStatus\": \"Completed\",\n" +
                "    \"meetingId\": \"41401\",\n" +
                "    \"meetingType\": \"Committee\",\n" +
                "    \"status\": \"Completed\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"meetingTime\": \"9:00 AM\",\n" +
                "    \"duration\": \"1\",\n" +
                "    \"meetingName\": \"Research Committe meeting\",\n" +
                "    \"minutes\": \"Instruction must and should follow\",\n" +
                "    \"meetingDate\": \"2019-02-01 11:10:15.165\",\n" +
                "    \"description\": \"\",\n" +
                "    \"scheduleStatus\": \"Completed\",\n" +
                "    \"meetingId\": \"41801\",\n" +
                "    \"meetingType\": \"Committee\",\n" +
                "    \"status\": \"Completed\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"meetingTime\": \"9:00 AM\",\n" +
                "    \"duration\": \"1\",\n" +
                "    \"meetingName\": \"1st level Quality Meeting\",\n" +
                "    \"minutes\": \"Follow Quality maintainence process\",\n" +
                "    \"meetingDate\": \"2019-02-01 11:12:29.023\",\n" +
                "    \"description\": \"\",\n" +
                "    \"scheduleStatus\": \"Completed\",\n" +
                "    \"meetingId\": \"42201\",\n" +
                "    \"meetingType\": \"Committee\",\n" +
                "    \"status\": \"Completed\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"meetingTime\": \"9:00 AM\",\n" +
                "    \"duration\": \"1\",\n" +
                "    \"meetingName\": \"2nd Level Quality  Meeting\",\n" +
                "    \"minutes\": \"Distrubutions of manuals/sops\",\n" +
                "    \"meetingDate\": \"2019-02-01 11:16:15.861\",\n" +
                "    \"description\": \"\",\n" +
                "    \"scheduleStatus\": \"Completed\",\n" +
                "    \"meetingId\": \"42701\",\n" +
                "    \"meetingType\": \"Committee\",\n" +
                "    \"status\": \"Ongoing\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"meetingTime\": \"9:00 AM\",\n" +
                "    \"duration\": \"1\",\n" +
                "    \"meetingName\": \"Meeting 1\",\n" +
                "    \"minutes\": \"description\",\n" +
                "    \"meetingDate\": \"2019-02-01 14:19:36.713\",\n" +
                "    \"description\": \"\",\n" +
                "    \"scheduleStatus\": \"Completed\",\n" +
                "    \"meetingId\": \"47001\",\n" +
                "    \"meetingType\": \"Committee\",\n" +
                "    \"status\": \"Completed\"\n" +
                "  }\n" +
                "]\n";
        private String TAG = "MeetingActivityTabs";


        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_meeting_activity_tabs, container, false);
            meeting_recycler = rootView.findViewById(R.id.meeting_recycler);
            parseresponsefromServer(MEETING_RESPONSE);
            MeetingAdapter meetingAdapter = new MeetingAdapter(getActivity(), currentMeetinglist);
            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
            meeting_recycler.setLayoutManager(mLayoutManager);
            meeting_recycler.setItemAnimator(new DefaultItemAnimator());
            meeting_recycler.setAdapter(meetingAdapter);
            return rootView;
        }

        private void parseresponsefromServer(String meeting_response) {
            currentMeetinglist.clear();
            try {
                JSONArray jsonArray = new JSONArray(meeting_response);
                for (int meetingObject = 0; meetingObject < jsonArray.length(); meetingObject++) {
                    Meetingmodelclass meetingmodelclass = new Meetingmodelclass();
                    JSONObject jsonObject = jsonArray.getJSONObject(meetingObject);
                    meetingmodelclass.setMeetingtime(jsonObject.getString("meetingTime"));
                    meetingmodelclass.setDuration(jsonObject.getString("duration"));
                    meetingmodelclass.setMeetingname(jsonObject.getString("meetingName"));
                    meetingmodelclass.setMeetingdate(jsonObject.getString("meetingDate"));
                    meetingmodelclass.setDescription(jsonObject.getString("description"));
                    meetingmodelclass.setMeetingId(jsonObject.getString("meetingId"));
                    meetingmodelclass.setMeetingtype(jsonObject.getString("meetingType"));
                    meetingmodelclass.setMeetingStatus(jsonObject.getString("status"));
                    String status = jsonObject.getString("status");
                    if (!status.equals("Completed")) {
                        currentMeetinglist.add(meetingmodelclass);
                    } else {
                        Toast.makeText(getActivity(), "Status is in progress", Toast.LENGTH_SHORT).show();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            if (position == 0) {
                return PlaceholderFragment.newInstance(position + 1);
            } else if (position == 1) {
                MeetingResponseFragment fragment = new MeetingResponseFragment();
                Bundle args = new Bundle();
                fragment.setArguments(args);
                return fragment;
            } else if (position == 2) {
                AuditingResponse fragment = new AuditingResponse();
                Bundle args = new Bundle();
                fragment.setArguments(args);
                return fragment;
            } else {
                AuditingResponse fragment = new AuditingResponse();
                Bundle args = new Bundle();
                fragment.setArguments(args);
                return fragment;
            }
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 2;
        }
    }
}
