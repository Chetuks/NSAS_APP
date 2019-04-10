package com.example.nsas_app.activities;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nsas_app.R;
import com.github.barteksc.pdfviewer.PDFView;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class PdfActivity extends AppCompatActivity {
    private Toolbar mToolbar;
    String pdfurl;
    private static final String TAG = null;
    PDFView pdfView;
    TextView errortext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdf);
        mToolbar = (Toolbar) findViewById(R.id.backbar);
        pdfView = findViewById(R.id.pdfview);
        errortext = findViewById(R.id.error_text);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            pdfurl = bundle.getString("all");
        }
        //pdfView.fromAsset("52613.pdf").load();
        new RetreivePdfStream().execute(pdfurl);

        mToolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    class RetreivePdfStream extends AsyncTask<String, Void, InputStream> {

        @Override
        protected InputStream doInBackground(String... strings) {
            InputStream inputStream = null;
            try {
                URL url = new URL(strings[0]);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                if (urlConnection.getResponseCode() == 200) {
                    inputStream = new BufferedInputStream(urlConnection.getInputStream());
                } else {
                    return null;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return inputStream;
        }

        @Override
        protected void onPostExecute(InputStream inputStream) {
            if (inputStream != null) {
                pdfView.fromStream(inputStream).load();
                errortext.setVisibility(View.GONE);
            } else {
                errortext.setVisibility(View.VISIBLE);
                Toast.makeText(PdfActivity.this, "no pdf", Toast.LENGTH_SHORT).show();
            }
        }
    }
}