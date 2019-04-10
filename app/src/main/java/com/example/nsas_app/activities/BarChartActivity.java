package com.example.nsas_app.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.nsas_app.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.time.Year;
import java.util.ArrayList;
import java.util.List;

public class BarChartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bar_chart);
        BarChart chart = findViewById(R.id.barchart);

        ArrayList NoOfEmp = new ArrayList();


        NoOfEmp.add(new BarEntry(4f, 5));
        NoOfEmp.add(new BarEntry(8f, 2));
        NoOfEmp.add(new BarEntry(6f, 3));
        NoOfEmp.add(new BarEntry(12f, 4));
        NoOfEmp.add(new BarEntry(13f, 5));
        NoOfEmp.add(new BarEntry(9f, 6));

        ArrayList year = new ArrayList();


        year.add("2/7/2016");
        year.add("3/7/2016");
        year.add("5/7/2016");
        year.add("6/7/2016");
        year.add("23/7/2016");
        year.add("30/7/2016");

        BarDataSet bardataset = new BarDataSet(NoOfEmp, "No Of Employee");
        chart.animateY(5000);
       // BarData data = new BarData(year, bardataset);

        ArrayList<IBarDataSet> dataSets = new ArrayList<>();
        dataSets.add((IBarDataSet) bardataset);
        BarData Data = new BarData(dataSets);


     //   BarData data= new BarData();
        bardataset.setColors(ColorTemplate.COLORFUL_COLORS);
        chart.setData(Data);
    }
}