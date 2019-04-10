package com.example.nsas_app.activities;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.nsas_app.Logger;
import com.example.nsas_app.R;
import com.example.nsas_app.modelclasses.ModuleModelclass;
import com.example.nsas_app.modelclasses.OrganizationModelclass;
import com.example.nsas_app.modelclasses.RegistrationModelclass;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RegistrationPage extends AppCompatActivity implements MultiSelectSpinner.OnMultipleItemsSelectedListener {
    private EditText inputName, inputEmail, inputPassword, inputRePassword;
    private TextInputLayout inputLayoutName, inputLayoutEmail, inputLayoutPassword, inputLayoutRePassword;
    private Button btnSignUp;
    private TextView logintext;
    private LinearLayout login_layout;
    private ArrayList<RegistrationModelclass> deptlist = new ArrayList<>();
    private ArrayList<OrganizationModelclass> orglist = new ArrayList<>();
    private ArrayList<ModuleModelclass> modulelist = new ArrayList<>();

    MultiSelectSpinner multiSelectDeptSpinner;
    MultiSelectSpinner multiSelectOrgSpinner;
    MultiSelectSpinner multiSelectRoleSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        inputLayoutName = (TextInputLayout) findViewById(R.id.input_layout_name);
        inputLayoutEmail = (TextInputLayout) findViewById(R.id.input_layout_email);
        inputLayoutPassword = (TextInputLayout) findViewById(R.id.input_layout_password);
        inputLayoutRePassword = (TextInputLayout) findViewById(R.id.input_layout_re_password);
        inputName = (EditText) findViewById(R.id.input_name);
        inputEmail = (EditText) findViewById(R.id.input_email);
        inputPassword = (EditText) findViewById(R.id.input_password);
        inputRePassword = (EditText) findViewById(R.id.input_re_password);
        btnSignUp = (Button) findViewById(R.id.btn_signup);
        logintext = findViewById(R.id.login_text);
        login_layout = findViewById(R.id.login_layout);
        multiSelectOrgSpinner = findViewById(R.id.organizationSpinner);
        multiSelectRoleSpinner = findViewById(R.id.rolespinner);
        multiSelectDeptSpinner = findViewById(R.id.departmentSpinner);

        callingapi();

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Validation()) {
                    inputLayoutName.setError(null);
                    inputLayoutPassword.setError(null);
                    inputLayoutRePassword.setError(null);
                    inputLayoutEmail.setError(null);
                    String name = inputName.getText().toString();
                    String password = inputPassword.getText().toString();
                    String email = inputEmail.getText().toString();
                    String deptid = "";
                    String orgid = "";
                    String roleid = "";

                    List<Integer> selectedplaceDept = multiSelectDeptSpinner.getSelectedIndices();
                    Logger.logD("sizeofdept", "" + selectedplaceDept.size());
                    for (int i = 0; i < selectedplaceDept.size(); i++) {
                        if (i == 0) {
                            deptid = deptlist.get(selectedplaceDept.get(i) - 1).getDeptid();
                        } else
                            deptid = deptid + "," + deptlist.get(selectedplaceDept.get(i) - 1).getDeptid();
                    }
                    Logger.logD("deptidcheck", deptid);

                    List<Integer> selectedplaceOrg = multiSelectOrgSpinner.getSelectedIndices();
                    for (int i = 0; i < selectedplaceOrg.size(); i++) {
                        if (i == 0) {
                            orgid = orglist.get(selectedplaceOrg.get(i) - 1).getOrgid();
                        } else
                            orgid = orgid + "," + orglist.get(selectedplaceOrg.get(i) - 1).getOrgid();
                    }
                    Logger.logD("orgidcheck", orgid);

                    List<Integer> selectedplaceRole = multiSelectRoleSpinner.getSelectedIndices();
                    for (int i = 0; i < selectedplaceRole.size(); i++) {
                        if (i == 0) {
                            roleid = modulelist.get(selectedplaceRole.get(i) - 1).getCategoryid();
                        } else
                            roleid = roleid + "," + modulelist.get(selectedplaceRole.get(i) - 1).getCategoryid();
                    }
                    Logger.logD("roleidcheck", roleid);

                    regapi("test@liferay.com", "test", name, password, email, deptid, orgid, roleid);

                    Toast.makeText(RegistrationPage.this, "Signed In", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(RegistrationPage.this, "Not SignedIn", Toast.LENGTH_SHORT).show();
                }
            }
        });

        login_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegistrationPage.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void callingapi() {
        deptapi("test@liferay.com", "test");
        orgapi("test@liferay.com", "test");
        moduleapi("test@liferay.com", "test");
    }

    private void regapi(final String s, final String test, String name, String password, String email, String deptid, String orgid, String roleid) {
        String url1="http://192.168.1.40:8080/api/jsonws/ns_as.documents/save-and-update-users-details-remotely/username/"+name+"/password/"+password+"/email/"+email+"/dept/"+deptid+"/org/"+orgid+"/roles/"+roleid;
        String convertedURL = url1.replace(" ", "%20");
        Logger.logV("Location", " callServerToSendParams " + convertedURL);
        StringRequest postRequest = new StringRequest(Request.Method.POST, convertedURL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Logger.logV("responseReg", "registration" + response);
                        try {
                            checkresponseRegistration(response);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                        Toast.makeText(RegistrationPage.this, "Not registered in server", Toast.LENGTH_SHORT).show();
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
        Volley.newRequestQueue(this).add(postRequest);
    }

    private void checkresponseRegistration(String response) throws Exception {
        JSONObject jsonObject = new JSONObject(response);
        if (jsonObject.getInt("response") == 0) {
            Intent intent = new Intent(RegistrationPage.this, HomePage.class);
            startActivity(intent);
        } else {
            Toast.makeText(RegistrationPage.this, "Not registered in server", Toast.LENGTH_SHORT).show();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void deptapi(final String s, final String test) {
        final String urlstaticresponse="[\n" +
                "  {\n" +
                "    \"groupName\": \"Nursing\",\n" +
                "    \"groupId\": \"35420\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"groupName\": \"Pharmacy\",\n" +
                "    \"groupId\": \"35424\"\n" +
                "  }\n" +
                "]";
        String url = "http://192.168.1.40:8080/api/jsonws/ns_as.documents/get-department";
        String convertedURL = url.replace(" ", "%20");
        Logger.logV("Location", " callServerToSendParams " + convertedURL);
        StringRequest postRequest = new StringRequest(Request.Method.POST, convertedURL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Logger.logV("response", "department" + response);
                        try {
                            checkresponse(urlstaticresponse);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
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
        Volley.newRequestQueue(this).add(postRequest);
    }

    private void checkresponse(String Response) throws Exception {
        JSONArray jsonArray = new JSONArray(Response);
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject1 = jsonArray.getJSONObject(i);
            RegistrationModelclass registrationModelclass = new RegistrationModelclass(jsonObject1.getString("groupId"), jsonObject1.getString("groupName"));
            deptlist.add(registrationModelclass);
            Logger.logD("checking", "" + deptlist.size());
        }
        settingdataSpinners(deptlist);
        List<Integer> selectedplace = multiSelectDeptSpinner.getSelectedIndices();
        Logger.logD("selectedplaceafter", "" + selectedplace);

    }

    private void settingdataSpinners(ArrayList<RegistrationModelclass> deptlist) {
       /* ArrayAdapter<RegistrationModelclass> adapter = new ArrayAdapter<RegistrationModelclass>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, deptlist);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        deptspinner.setAdapter(adapter);*/

        //setting multi spinner
        String[] arrayname = new String[deptlist.size() + 1];
        for (int i = 0; i < arrayname.length; i++) {
            if (i == 0) {
                arrayname[i] = "None";
            } else {
                arrayname[i] = deptlist.get(i - 1).getDeptname();
            }
        }
        multiSelectDeptSpinner.setItems(arrayname);
        multiSelectDeptSpinner.hasNoneOption(true);
        multiSelectDeptSpinner.setSelection(new int[]{0});
        multiSelectDeptSpinner.setListener(this);
        List<Integer> selectedplace = multiSelectDeptSpinner.getSelectedIndices();
        Logger.logD("selectedplace", "" + selectedplace);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void orgapi(final String s, final String test) {
        final String responsestatic="[\n" +
                "  {\n" +
                "    \"orgName\": \"Bangalore\",\n" +
                "    \"orgId\": \"35400\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"orgName\": \"Bombay\",\n" +
                "    \"orgId\": \"35407\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"orgName\": \"Delhi\",\n" +
                "    \"orgId\": \"35413\"\n" +
                "  }\n" +
                "]";
        String url = "http://192.168.1.40:8080/api/jsonws/ns_as.documents/get-organization";
        String convertedURL = url.replace(" ", "%20");
        Logger.logV("Location", " callServerToSendParams " + convertedURL);
        StringRequest postRequest = new StringRequest(Request.Method.POST, convertedURL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //count = 0;
                        Logger.logV("response", "organization" + response);
                        try {
                            checkOrgresponse(responsestatic);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
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
        Volley.newRequestQueue(this).add(postRequest);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void moduleapi(final String s, final String test) {
        final String responsemodulestatic="[\n" +
                "  {\n" +
                "    \"categoryName\": \"Audits\",\n" +
                "    \"categoryId\": \"64306\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"categoryName\": \"CAPA\",\n" +
                "    \"categoryId\": \"64308\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"categoryName\": \"Certificates\",\n" +
                "    \"categoryId\": \"64305\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"categoryName\": \"Documents\",\n" +
                "    \"categoryId\": \"64304\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"categoryName\": \"Incidents\",\n" +
                "    \"categoryId\": \"64307\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"categoryName\": \"Meetings\",\n" +
                "    \"categoryId\": \"64309\"\n" +
                "  }\n" +
                "]";
        String url = "http://192.168.1.40:8080/api/jsonws/ns_as.documents/get-modules";
        String convertedURL = url.replace(" ", "%20");
        Logger.logV("Location", " callServerToSendParams " + convertedURL);
        StringRequest postRequest = new StringRequest(Request.Method.POST, convertedURL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Logger.logV("response", "module" + response);
                        try {
                            checkmoduleresponse(responsemodulestatic);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
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
        Volley.newRequestQueue(this).add(postRequest);
    }

    private void checkmoduleresponse(String response) throws Exception {
        JSONArray jsonArray = new JSONArray(response);
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject1 = jsonArray.getJSONObject(i);
            ModuleModelclass moduleModelclass = new ModuleModelclass(jsonObject1.getString("categoryName"), jsonObject1.getString("categoryId"));
            modulelist.add(moduleModelclass);
        }
        settingModuleSpinner(modulelist);
    }

    private void checkOrgresponse(String response) throws Exception {
        JSONArray jsonArray = new JSONArray(response);
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject1 = jsonArray.getJSONObject(i);
            OrganizationModelclass organizationModelclass = new OrganizationModelclass(jsonObject1.getString("orgName"), jsonObject1.getString("orgId"));
            orglist.add(organizationModelclass);
        }
        settingOrgspinner(orglist);
    }

    private void settingOrgspinner(final ArrayList<OrganizationModelclass> orglist) {
       /* ArrayAdapter<OrganizationModelclass> adapter = new ArrayAdapter<OrganizationModelclass>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, deptlist);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        multiSelectOrgSpinner.setAdapter(adapter);*/
        String[] arrayname = new String[orglist.size() + 1];
        for (int i = 0; i < arrayname.length; i++) {
            if (i == 0) {
                arrayname[i] = "None";
            } else {
                arrayname[i] = orglist.get(i - 1).getOrgname();
            }
        }
        multiSelectOrgSpinner.setItems(arrayname);
        multiSelectOrgSpinner.hasNoneOption(true);
        multiSelectOrgSpinner.setSelection(new int[]{0});
        multiSelectOrgSpinner.setListener(this);
    }

    private void settingModuleSpinner(final ArrayList<ModuleModelclass> moduleinsidelist) {
       /* ArrayAdapter<ModuleModelclass> adapter = new ArrayAdapter<ModuleModelclass>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, moduleinsidelist);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        multiSelectRoleSpinner.setAdapter(adapter);*/

        String[] arrayname = new String[moduleinsidelist.size() + 1];
        for (int i = 0; i < arrayname.length; i++) {
            if (i == 0) {
                arrayname[i] = "None";
            } else {
                arrayname[i] = moduleinsidelist.get(i - 1).getCategoryname();
            }
        }
        multiSelectRoleSpinner.setItems(arrayname);
        multiSelectRoleSpinner.hasNoneOption(true);
        multiSelectRoleSpinner.setSelection(new int[]{0});
        multiSelectRoleSpinner.setListener(this);
    }

    private boolean Validation() {
        if (inputName.getText().toString().isEmpty()) {
            inputLayoutName.setError("Name cannot be empty");
            return false;
        }
        if (inputPassword.getText().toString().isEmpty()) {
            inputLayoutPassword.setError("Password cannot be empty");
            inputLayoutName.setError(null);
            return false;
        }
        if (inputRePassword.getText().toString().isEmpty()) {
            inputLayoutRePassword.setError("Re Enter Password cannot be empty");
            inputLayoutPassword.setError(null);
            inputLayoutName.setError(null);
            return false;
        }
        if (!inputPassword.getText().toString().equals(inputRePassword.getText().toString())) {
            inputLayoutRePassword.setError("Re Enter Password is not matching");
            return false;
        }
        if (inputEmail.getText().toString().isEmpty()) {
            inputLayoutEmail.setError("Email cannot be empty");
            inputLayoutRePassword.setError(null);
            return false;
        }
        if (multiSelectDeptSpinner.getSelectedItem().equals("None")) {
            int selectedItemOfMySpinner = multiSelectDeptSpinner.getSelectedItemPosition();
            String actualPositionOfMySpinner = (String) multiSelectDeptSpinner.getItemAtPosition(selectedItemOfMySpinner);
            if (actualPositionOfMySpinner.equals("None")) {
                setSpinnerError(multiSelectDeptSpinner, "Department field mandatory");
            }
            return false;
        }
        if (multiSelectOrgSpinner.getSelectedItem().equals("None")) {
            int selectedItemOfMySpinner = multiSelectOrgSpinner.getSelectedItemPosition();
            String actualPositionOfMySpinner = (String) multiSelectOrgSpinner.getItemAtPosition(selectedItemOfMySpinner);
            if (actualPositionOfMySpinner.equals("None")) {
                setSpinnerError(multiSelectOrgSpinner, "Organization field mandatory");
            }
            return false;
        }
        if (multiSelectRoleSpinner.getSelectedItem().equals("None")) {
            int selectedItemOfMySpinner = multiSelectRoleSpinner.getSelectedItemPosition();
            String actualPositionOfMySpinner = (String) multiSelectRoleSpinner.getItemAtPosition(selectedItemOfMySpinner);
            if (actualPositionOfMySpinner.equals("None")) {
                setSpinnerError(multiSelectRoleSpinner, "Role field mandatory");
            }
            return false;
        }
        return true;
    }

    private void setSpinnerError(Spinner spinner, String error) {
        View selectedView = spinner.getSelectedView();
        if (selectedView instanceof TextView) {
            spinner.requestFocus();
            TextView selectedTextView = (TextView) selectedView;
            selectedTextView.setError("error"); // any name of the error will do
            selectedTextView.setTextColor(Color.RED); //text color in which you want your error message to be displayed
            selectedTextView.setText(error); // actual error message
            spinner.performClick(); // to open the spinner list if error is found.
        }
    }

    @Override
    public void selectedIndices(List<Integer> indices) {
        //Toast.makeText(this.getApplicationContext(), "Selected indicies" + indices, Toast.LENGTH_LONG).show();
    }

    @Override
    public void selectedStrings(List<String> strings) {
        //Toast.makeText(this.getApplicationContext(), "Selected Companies" + strings, Toast.LENGTH_LONG).show();
    }
}