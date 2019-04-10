package com.example.nsas_app.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nsas_app.Logger;
import com.example.nsas_app.R;
import com.example.nsas_app.modelclasses.CellValue;
import com.example.nsas_app.modelclasses.Detail;
import com.example.nsas_app.modelclasses.Table;
import com.squareup.picasso.Picasso;

import java.util.List;

public class StaticPdfActivity extends AppCompatActivity {
    TextView heading, mainsubheading, coverpagenameText, headingcontentText, subheadingText, subheadingContent, signaturetext;
    String docname, docfullname, docid;
    ImageView coverimage, belowcoverimage, signature;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_static_pdf);
        init();
        //createMatricTables();
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            docfullname = bundle.getString("documentfullname");
            List<Detail> details = (List<Detail>) bundle.getSerializable("objectdata");
            heading.setText(docfullname);
            assert details != null;
            for (int i = 0; i < details.size(); i++) {
                if (details.get(i).getTable() != null) {
                    //tablevalues
                    Table table = details.get(i).getTable();
                    Integer cols = table.getCols();
                    Integer rows = table.getRows();
                    String name = table.getTableName();
                    String tableidcheck = table.getTableId();
                    //Cellvalues
                    List<CellValue> cellValues = table.getCellValues();
                    Logger.logD("namea", "" + cols);
                    Logger.logD("names", "" + rows);
                    Logger.logD("named", "" + name);
                    Logger.logD("namef", "" + tableidcheck);
                    for (int i1 = 0; i1 < cellValues.size(); i1++) {
                        Logger.logD("cellvaluestable", "" + cellValues.get(i1).getTableDetailId());
                    }
                }
            }
            String headingcheck = details.get(0).getHeading();
            mainsubheading.setText(headingcheck);
            String coverpagename = details.get(1).getCoverHeading();
            coverpagenameText.setText(coverpagename);
            String coverpageimage = details.get(1).getUrl();
            String belowcoverpageimage = details.get(2).getUrl();
            Picasso.get().load(coverpageimage).into(coverimage);
            Picasso.get().load(belowcoverpageimage).into(belowcoverimage);
            String headingcontent = details.get(3).getContent();
            headingcontentText.setText(headingcontent);
            String subheading = details.get(5).getHeading();
            String subheadingcontent = details.get(5).getContent();
            subheadingText.setText(subheading);
            subheadingContent.setText(subheadingcontent);
            String subheadingSignature = details.get(7).getHeading();
            String signatureimage = details.get(8).getUrl();
            Picasso.get().load(signatureimage).into(signature);
            signaturetext.setText(subheadingSignature);
        }
    }

    private void createMatricTables() {

    }

    private void init() {
        heading = findViewById(R.id.heading);
        mainsubheading = findViewById(R.id.mainsub_heading);
        coverpagenameText = findViewById(R.id.coverpage_name);
        coverimage = findViewById(R.id.cover_image);
        belowcoverimage = findViewById(R.id.below_cover_image);
        headingcontentText = findViewById(R.id.heading_text);
        subheadingText = findViewById(R.id.subheading);
        subheadingContent = findViewById(R.id.subheading_content);
        signaturetext = findViewById(R.id.subbheading);
        signature = findViewById(R.id.signature);
    }
}
