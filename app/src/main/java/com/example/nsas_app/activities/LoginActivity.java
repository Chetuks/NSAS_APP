package com.example.nsas_app.activities;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.nsas_app.R;

public class LoginActivity extends AppCompatActivity {
    Button loginbtn;
    LinearLayout loginlayout;
    EditText loginpassword;
    EditText loginname;
    TextInputLayout passwordlayout;
    TextInputLayout loginnamelayout;
    private Context context;
    SharedPreferencemanager preferencemanager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        loginbtn = findViewById(R.id.login_btn);
        loginlayout = findViewById(R.id.login_layout);
        loginname = findViewById(R.id.login_name);
        loginpassword = findViewById(R.id.login_password);
        loginnamelayout = findViewById(R.id.login_layout_name);
        passwordlayout = findViewById(R.id.login_layout_password);
        context = this;
        preferencemanager = new SharedPreferencemanager(context);
        String response="{\n" +
                "  \"agreedToTermsOfUse\": true,\n" +
                "  \"comments\": \"\",\n" +
                "  \"companyId\": \"20116\",\n" +
                "  \"contactId\": \"20157\",\n" +
                "  \"createDate\": 1517815341200,\n" +
                "  \"defaultUser\": false,\n" +
                "  \"emailAddress\": \"test@liferay.com\",\n" +
                "  \"emailAddressVerified\": true,\n" +
                "  \"facebookId\": \"0\",\n" +
                "  \"failedLoginAttempts\": 0,\n" +
                "  \"firstName\": \"Test\",\n" +
                "  \"googleUserId\": \"\",\n" +
                "  \"graceLoginCount\": 0,\n" +
                "  \"greeting\": \"Welcome Test Test!\",\n" +
                "  \"jobTitle\": \"\",\n" +
                "  \"languageId\": \"en_US\",\n" +
                "  \"lastFailedLoginDate\": 1539088756589,\n" +
                "  \"lastLoginDate\": 1553239714097,\n" +
                "  \"lastLoginIP\": \"127.0.0.1\",\n" +
                "  \"lastName\": \"Test\",\n" +
                "  \"ldapServerId\": \"-1\",\n" +
                "  \"lockout\": false,\n" +
                "  \"lockoutDate\": null,\n" +
                "  \"loginDate\": 1553249815062,\n" +
                "  \"loginIP\": \"127.0.0.1\",\n" +
                "  \"middleName\": \"\",\n" +
                "  \"modifiedDate\": 1553249815062,\n" +
                "  \"mvccVersion\": \"1251\",\n" +
                "  \"openId\": \"\",\n" +
                "  \"portraitId\": \"0\",\n" +
                "  \"reminderQueryAnswer\": \"test\",\n" +
                "  \"reminderQueryQuestion\": \"what-is-your-father's-middle-name\",\n" +
                "  \"screenName\": \"test\",\n" +
                "  \"status\": 0,\n" +
                "  \"timeZoneId\": \"UTC\",\n" +
                "  \"userId\": \"20156\",\n" +
                "  \"uuid\": \"11ee9f80-94eb-9a68-6b0e-c612b35b5986\"\n" +
                "}";
        String url="http://localhost:8080/api/jsonws/user/get-user-by-email-address/company-id/20116/email-address/test%40liferay.com";

        loginlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegistrationPage.class);
                startActivity(intent);
                finish();
            }
        });

        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validation()) {
                    Toast.makeText(LoginActivity.this, "Login successfull", Toast.LENGTH_SHORT).show();
                    preferencemanager.saveIsLoggedIn(context, true);
                    //Intent intent = new Intent(LoginActivity.this, DocumentActivity.class);
                    Intent intent = new Intent(LoginActivity.this, HomePage.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(LoginActivity.this, "Login invalid", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private boolean validation() {
        if (loginname.getText().toString().isEmpty()) {
            loginnamelayout.setError("Name/Email cannot be empty");
            return false;
        }
        if (loginpassword.getText().toString().isEmpty()) {
            passwordlayout.setError("Password cannot be empty");
            loginnamelayout.setError(null);
            return false;
        }
        passwordlayout.setError(null);
        return true;
    }
}