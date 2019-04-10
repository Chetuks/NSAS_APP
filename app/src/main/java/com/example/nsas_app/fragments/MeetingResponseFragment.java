package com.example.nsas_app.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.nsas_app.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MeetingResponseFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MeetingResponseFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MeetingResponseFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    RecyclerView meeting_recycler;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private List<MeetingCompletedmodelclass> completedmeetinglist = new ArrayList<>();
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


    private OnFragmentInteractionListener mListener;

    public MeetingResponseFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MeetingResponseFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MeetingResponseFragment newInstance(String param1, String param2) {
        MeetingResponseFragment fragment = new MeetingResponseFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_meeting_response, container, false);
        meeting_recycler = rootView.findViewById(R.id.meeting_recycler_response);
        parseresponse(MEETING_RESPONSE);
        MeetingCompletedAdapter meetingCompletedAdapter = new MeetingCompletedAdapter(getActivity(), completedmeetinglist);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        meeting_recycler.setLayoutManager(mLayoutManager);
        meeting_recycler.setItemAnimator(new DefaultItemAnimator());
        meeting_recycler.setAdapter(meetingCompletedAdapter);
        return rootView;
    }

    private void parseresponse(String meeting_response) {
        completedmeetinglist.clear();
        try {
            JSONArray jsonArray = new JSONArray(meeting_response);
            for (int meetingObject = 0; meetingObject < jsonArray.length(); meetingObject++) {
                MeetingCompletedmodelclass meetingCompletedmodelclass = new MeetingCompletedmodelclass();
                JSONObject jsonObject = jsonArray.getJSONObject(meetingObject);
                meetingCompletedmodelclass.setMeetingtime(jsonObject.getString("meetingTime"));
                meetingCompletedmodelclass.setDuration(jsonObject.getString("duration"));
                meetingCompletedmodelclass.setMeetingname(jsonObject.getString("meetingName"));
                meetingCompletedmodelclass.setMeetingdate(jsonObject.getString("meetingDate"));
                meetingCompletedmodelclass.setDescription(jsonObject.getString("description"));
                meetingCompletedmodelclass.setMeetingId(jsonObject.getString("meetingId"));
                meetingCompletedmodelclass.setMeetingtype(jsonObject.getString("meetingType"));
                meetingCompletedmodelclass.setMeetingStatus(jsonObject.getString("status"));
                String status = jsonObject.getString("status");
               // if (status.equals("Ongoing")) {
                if (status.equals("Completed")) {
                    completedmeetinglist.add(meetingCompletedmodelclass);
                } else {
                    Toast.makeText(getActivity(), "Status is completed", Toast.LENGTH_SHORT).show();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
       /* if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }*/
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
