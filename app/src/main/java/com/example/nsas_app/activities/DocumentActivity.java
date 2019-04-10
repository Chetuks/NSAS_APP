package com.example.nsas_app.activities;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.nsas_app.adapter.GridAdapter;
import com.example.nsas_app.Logger;
import com.example.nsas_app.R;
import com.example.nsas_app.adapter.StaticGridAdapter;
import com.example.nsas_app.modelclasses.CellValue;
import com.example.nsas_app.modelclasses.DashboardModelClass;
import com.example.nsas_app.modelclasses.Detail;
import com.example.nsas_app.modelclasses.DocumentModelClas;
import com.example.nsas_app.modelclasses.Table;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DocumentActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    DashboardModelClass dashboardModelClass;
    private ArrayList<DashboardModelClass> mAll = new ArrayList<>();
    private ArrayList<DocumentModelClas> staticList = new ArrayList<>();
    // private ArrayList<Detail> detailList = new ArrayList<>();
    ArrayList<Detail> detailList;
    private GridAdapter adapter;
    private StaticGridAdapter staticGridAdapter;
    TextView errortxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        recyclerView = findViewById(R.id.recycler_view);
        errortxt = findViewById(R.id.noresponse);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        contentsApi("test@liferay.com", "test");
    }

    /**
     * @param s
     * @param test Calling api to get the documents
     */
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void contentsApi(final String s, final String test) {
        final String respoStatic = "[\n" +
                "  {\n" +
                "    \"DocId\": \"34674\",\n" +
                "    \"external\": true,\n" +
                "    \"CreatedBy\": \"Test\",\n" +
                "    \"CreatedDate\": \"31/01/2019\",\n" +
                "    \"DocName\": \" NABH BOOK...\",\n" +
                "    \"DocFullName\": \"NABH BOOK 2\",\n" +
                "    \"url\": \"http://192.168.1.40:8080/documents/20143/34609/34676.pdf/b4772a48-4026-c4f3-260c-7602604b6fe9\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"DocId\": \"34702\",\n" +
                "    \"external\": true,\n" +
                "    \"CreatedBy\": \"Test\",\n" +
                "    \"CreatedDate\": \"31/01/2019\",\n" +
                "    \"DocName\": \" NABH BOOK...\",\n" +
                "    \"DocFullName\": \"NABH BOOK 4\",\n" +
                "    \"url\": \"http://192.168.1.40:8080/documents/20143/34609/34704.pdf/57ac2020-c581-d89c-128d-a8913998c2c1\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"DocId\": \"52611\",\n" +
                "    \"external\": true,\n" +
                "    \"CreatedBy\": \"Test\",\n" +
                "    \"CreatedDate\": \"14/02/2019\",\n" +
                "    \"DocName\": \" Document 1...\",\n" +
                "    \"DocFullName\": \"Document 1\",\n" +
                "    \"url\": \"http://192.168.1.40:8080/documents/20143/34609/52613.pdf/14b1264b-c9d0-b32f-08c0-bb4a58755eb5\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"DocId\": \"59309\",\n" +
                "    \"external\": false,\n" +
                "    \"CreatedBy\": \"Test\",\n" +
                "    \"CreatedDate\": \"27/02/2019\",\n" +
                "    \"DocName\": \" ACCREDITATION STANDARDS...\",\n" +
                "    \"DocFullName\": \"ACCREDITATION STANDARDS ON BLOOD BANKS\",\n" +
                "    \"Detail\": [\n" +
                "      {\n" +
                "        \"docDetailId\": \"59311\",\n" +
                "        \"detailType\": \"Chapter\",\n" +
                "        \"heading\": \"123ORGANISATION AND MANAGEMENT\",\n" +
                "        \"subHeading\": \"\",\n" +
                "        \"label\": \"ORGANISATION AND MANAGEMENT\",\n" +
                "        \"content\": \"\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"docDetailId\": \"59312\",\n" +
                "        \"detailType\": \"Cover Page\",\n" +
                "        \"heading\": \"NABH\",\n" +
                "        \"coverHeading\": \"NABH\",\n" +
                "        \"url\": \"http://192.168.1.40:8080/documents/20143/34609/59313.jpg/acbc2105-0802-94e5-0031-30c06f58d047\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"docDetailId\": \"59325\",\n" +
                "        \"detailType\": \"Image\",\n" +
                "        \"heading\": \"NABH\",\n" +
                "        \"coverHeading\": \"NABH\",\n" +
                "        \"url\": \"http://192.168.1.40:8080/documents/20143/34609/59326.jpg/638a62e6-fdcb-cabe-6691-0e93689dd28f\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"docDetailId\": \"59338\",\n" +
                "        \"detailType\": \"Main Heading\",\n" +
                "        \"heading\": \"Legal identity\",\n" +
                "        \"content\": \"1.1.1 The blood bank/ blood centre shall have a valid licence from Regulatory Authorities, as\\napplicable or the blood bank/ blood centre shall have applied for renewal of the licence\\nin time as per existing regulations.\\n1.1.2 The organisation under which the blood bank/ blood centre functions shall be legally\\nidentifiable.\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"docDetailId\": \"59339\",\n" +
                "        \"detailType\": \"Paragraph\",\n" +
                "        \"label\": \"[Ljava.lang.String;@488557ca(...)\",\n" +
                "        \"content\": \"\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"docDetailId\": \"59340\",\n" +
                "        \"detailType\": \"Sub Heading\",\n" +
                "        \"heading\": \"Responsibility\",\n" +
                "        \"label\": \"Responsibility\",\n" +
                "        \"content\": \"An organization chart (organogram) shall be defined.The organization chart of the\\nblood bank/ blood centre shall show linkage with the parent organization as applicable.\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"docDetailId\": \"59341\",\n" +
                "        \"detailType\": \"Shared\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"docDetailId\": \"59343\",\n" +
                "        \"detailType\": \"Table\",\n" +
                "        \"heading\": \"Blood Bank\",\n" +
                "        \"label\": \"Blood Bank\",\n" +
                "        \"table\": {\n" +
                "          \"cellValues\": [\n" +
                "            {\n" +
                "              \"rowNo\": 1,\n" +
                "              \"colNo\": 1,\n" +
                "              \"tableDetailId\": \"59345\",\n" +
                "              \"value\": \"Sl.No\"\n" +
                "            },\n" +
                "            {\n" +
                "              \"rowNo\": 1,\n" +
                "              \"colNo\": 2,\n" +
                "              \"tableDetailId\": \"59346\",\n" +
                "              \"value\": \"Type\"\n" +
                "            },\n" +
                "            {\n" +
                "              \"rowNo\": 1,\n" +
                "              \"colNo\": 3,\n" +
                "              \"tableDetailId\": \"59347\",\n" +
                "              \"value\": \"Avalability\"\n" +
                "            },\n" +
                "            {\n" +
                "              \"rowNo\": 2,\n" +
                "              \"colNo\": 1,\n" +
                "              \"tableDetailId\": \"59348\",\n" +
                "              \"value\": \"1\"\n" +
                "            },\n" +
                "            {\n" +
                "              \"rowNo\": 2,\n" +
                "              \"colNo\": 2,\n" +
                "              \"tableDetailId\": \"59349\",\n" +
                "              \"value\": \"A group\"\n" +
                "            },\n" +
                "            {\n" +
                "              \"rowNo\": 2,\n" +
                "              \"colNo\": 3,\n" +
                "              \"tableDetailId\": \"59350\",\n" +
                "              \"value\": \"true\"\n" +
                "            },\n" +
                "            {\n" +
                "              \"rowNo\": 3,\n" +
                "              \"colNo\": 1,\n" +
                "              \"tableDetailId\": \"59351\",\n" +
                "              \"value\": \"2\"\n" +
                "            },\n" +
                "            {\n" +
                "              \"rowNo\": 3,\n" +
                "              \"colNo\": 2,\n" +
                "              \"tableDetailId\": \"59352\",\n" +
                "              \"value\": \"B group\"\n" +
                "            },\n" +
                "            {\n" +
                "              \"rowNo\": 3,\n" +
                "              \"colNo\": 3,\n" +
                "              \"tableDetailId\": \"59353\",\n" +
                "              \"value\": \"true\"\n" +
                "            }\n" +
                "          ],\n" +
                "          \"tableId\": \"59344\",\n" +
                "          \"rows\": 3,\n" +
                "          \"cols\": 3,\n" +
                "          \"tableName\": \"Blood Bank\"\n" +
                "        }\n" +
                "      },\n" +
                "      {\n" +
                "        \"docDetailId\": \"59354\",\n" +
                "        \"detailType\": \"Signature\",\n" +
                "        \"heading\": \"NABH\",\n" +
                "        \"coverHeading\": \"NABH\",\n" +
                "        \"url\": \"http://192.168.1.40:8080/documents/20143/34609/59355.png/6c09723e-6c53-a522-8d5c-5801d8f8254f\"\n" +
                "      }\n" +
                "    ]\n" +
                "  }\n" +
                "]";
        parceResponse(respoStatic);

        String url = "http://192.168.1.40:8080/api/jsonws/ns_as.documents/get-document-for-view/user-id/20156";
        String convertedURL = url.replace(" ", "%20");
        Logger.logV("contents", " callServerToSendParams " + convertedURL);
        StringRequest postRequest = new StringRequest(Request.Method.POST, convertedURL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Logger.logV("documentresponse", " apiResponse " + response);
                        try {
                            //checkresponse(response);
                            // parceResponse(respoStatic);
                            errortxt.setVisibility(View.GONE);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                        errortxt.setVisibility(View.VISIBLE);
                        Logger.logV("Location", " ERROR " + error);
                    }
                }
        ) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<>();
                String credentials = s + ":" + test;
                String auth = "Basic " + Base64.encodeToString(credentials.getBytes(), Base64.NO_WRAP);
                headers.put("Content-Type", "application/json");
                headers.put("Authorization", auth);
                return headers;
            }
        };
        Volley.newRequestQueue(DocumentActivity.this).add(postRequest);
    }

    /**
     * @param response parsing the response
     */
    private void parceResponse(String response) {
        DocumentModelClas document = null;
        try {
            JSONArray jsonArray = new JSONArray(response);
            if (jsonArray.length()!=0) {
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    if (jsonObject.getBoolean("external")) {
                        document = new DocumentModelClas();
                        document.setDocId(String.valueOf(jsonObject.getInt("DocId")));
                        document.setDocName(String.valueOf(jsonObject.getString("DocName")));
                        document.setCreatedBy(String.valueOf(jsonObject.getString("CreatedBy")));
                        document.setCreatedDate(String.valueOf(jsonObject.getString("CreatedDate")));
                        document.setUrl(String.valueOf(jsonObject.getString("url")));
                        document.setDocFullName(String.valueOf(jsonObject.getString("DocFullName")));
                        document.setExternal((jsonObject.getBoolean("external")));
                        staticList.add(document);
                    } else {
                        Logger.logD("insideelse", "");
                        JSONArray detailArray = jsonObject.getJSONArray("Detail");
                        detailList = new ArrayList<>();
                        for (int detailLoop = 0; detailLoop < detailArray.length(); detailLoop++) {
                            JSONObject innerDetail = detailArray.getJSONObject(detailLoop);
                            Detail detail = new Detail();
                            if (innerDetail.has("heading")) {
                                detail.setHeading(innerDetail.getString("heading"));
                            }
                            if (innerDetail.has("label")) {
                                detail.setLabel(innerDetail.getString("label"));
                            }
                            if (innerDetail.has("content")) {
                                detail.setContent(innerDetail.getString("content"));
                            }
                            if (innerDetail.has("coverHeading")) {
                                detail.setCoverHeading(innerDetail.getString("coverHeading"));
                            }
                            if (innerDetail.has("detailType")) {
                                detail.setDetailType(innerDetail.getString("detailType"));
                            }
                            if (innerDetail.has("subHeading")) {
                                detail.setSubHeading(innerDetail.getString("subHeading"));
                            }
                            if (innerDetail.has("url")) {
                                detail.setUrl(innerDetail.getString("url"));
                            }
                            if (innerDetail.has("table")) {
                                Table table = new Table();
                                List<CellValue> cellValues = new ArrayList<>();
                                JSONObject jsonObject1 = innerDetail.getJSONObject("table");
                                JSONArray jsonArray1 = jsonObject1.getJSONArray("cellValues");
                                for (int k = 0; k < jsonArray1.length(); k++) {
                                    CellValue cellValue = new CellValue();
                                    cellValue.setColNo(jsonArray1.getJSONObject(k).getInt("colNo"));
                                    cellValue.setRowNo(jsonArray1.getJSONObject(k).getInt("rowNo"));
                                    cellValue.setTableDetailId(jsonArray1.getJSONObject(k).getString("tableDetailId"));
                                    cellValue.setValue(jsonArray1.getJSONObject(k).getString("value"));
                                    Logger.logD("tableidgetting", "" + cellValue.getTableDetailId());
                                    cellValues.add(cellValue);
                                }
                                table.setTableId(jsonObject1.getString("tableId"));
                                table.setTableName(jsonObject1.getString("tableName"));
                                table.setRows(jsonObject1.getInt("rows"));
                                table.setCols(jsonObject1.getInt("cols"));
                                table.setCellValues(cellValues);
                                detail.setTable(table);
                            }
                            detailList.add(detail);
                        }
                        document = new DocumentModelClas();
                        document.setDocId(String.valueOf(jsonObject.getInt("DocId")));
                        document.setDocName(String.valueOf(jsonObject.getString("DocName")));
                        document.setCreatedBy(String.valueOf(jsonObject.getString("CreatedBy")));
                        document.setCreatedDate(String.valueOf(jsonObject.getString("CreatedDate")));
                        // document.setUrl(String.valueOf(jsonObject.getInt("url")));
                        document.setDocFullName(String.valueOf(jsonObject.getString("DocFullName")));
                        document.setExternal((jsonObject.getBoolean("external")));
                        document.setDetail(detailList);
                        staticList.add(document);
                    }
                }
                settingStaticAdapter(staticList);

            } else {
                //todo
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    /**
     * @param documentgrid calling adapter class to set the data
     */
    private void settingStaticAdapter(ArrayList<DocumentModelClas> documentgrid) {
        if (!documentgrid.isEmpty()) {
            GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
            recyclerView.setLayoutManager(layoutManager);
            staticGridAdapter = new StaticGridAdapter(DocumentActivity.this, documentgrid);
            recyclerView.setAdapter(staticGridAdapter);
            recyclerView.setHasFixedSize(true);
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