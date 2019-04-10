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
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.nsas_app.R;
import com.example.nsas_app.adapter.CurrentAuditAdapter;
import com.example.nsas_app.fragments.AuditingResponse;
import com.example.nsas_app.modelclasses.AuditModelclass;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class AuditActivityTabs extends AppCompatActivity {
    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audit_tabs2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
        // getSupportActionBar().setDisplayHomeAsUpEnabled(true);
       /* toolbar.setNavigationIcon(R.drawable.backbtn);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });*/
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_audit_activity_tabs, menu);
        return true;
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
        private static final String TAG = "PlaceholderFragment";
        private List<AuditModelclass> currentAuditList = new ArrayList<>();
        private RecyclerView recycleView;
        private String AUDIT_RESPONSE = "[\n" +
                "  {\n" +
                "    \"duration\": \"01/03/2019 - 31/03/2019\",\n" +
                "    \"name\": \"System Audit \",\n" +
                "    \"planId\": \"64913\",\n" +
                "    \"id\": \"35464\",\n" +
                "    \"status\": \"Auditing\"\n" +
                "  },{\n" +
                "    \"duration\": \"01/03/2019 - 31/03/2019\",\n" +
                "    \"name\": \"System Audit one\",\n" +
                "    \"planId\": \"64913\",\n" +
                "    \"id\": \"35464\",\n" +
                "    \"status\": \"Auditing\"\n" +
                "  },{\n" +
                "    \"duration\": \"01/03/2019 - 31/03/2019\",\n" +
                "    \"name\": \"System Audit two\",\n" +
                "    \"planId\": \"64913\",\n" +
                "    \"id\": \"35464\",\n" +
                "    \"status\": \"Auditing\"\n" +
                "  }\n" +
                "]";

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
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_audit_activity_tabs, container, false);
            recycleView = (RecyclerView) rootView.findViewById(R.id.list);
            parceResponseFrmServer(AUDIT_RESPONSE);
            CurrentAuditAdapter currentAuditAdapter = new CurrentAuditAdapter(getActivity(), currentAuditList);
            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
            recycleView.setLayoutManager(mLayoutManager);
            recycleView.setItemAnimator(new DefaultItemAnimator());
            recycleView.setAdapter(currentAuditAdapter);
            return rootView;
        }

        private void parceResponseFrmServer(String AUDIT_RESPONSE) {
            currentAuditList.clear();
            try {
                JSONArray jsonArray = new JSONArray(AUDIT_RESPONSE);
                for (int auditObject = 0; auditObject < jsonArray.length(); auditObject++) {
                    AuditModelclass auditModelclass = new AuditModelclass();
                    JSONObject jsonObject = jsonArray.getJSONObject(auditObject);
                    auditModelclass.setId(jsonObject.getString("id"));
                    auditModelclass.setDuration(jsonObject.getString("duration"));
                    auditModelclass.setName(jsonObject.getString("name"));
                    auditModelclass.setPlanId(jsonObject.getString("planId"));
                    auditModelclass.setStatus(jsonObject.getString("status"));
                    Log.d(TAG, auditModelclass.getName());
                    currentAuditList.add(auditModelclass);
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
            if (position == 0)
                return PlaceholderFragment.newInstance(position + 1);
            else if (position == 1) {
                AuditingResponse fragment = new AuditingResponse();
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
            //  return PlaceholderFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 1;
        }
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
