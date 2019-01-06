package com.example.android.hostelcomplaintmanagement;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class TechLogin extends AppCompatActivity {

    EditText id,pw;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tech_login);
        id=(EditText)findViewById(R.id.techID);
        pw=(EditText)findViewById(R.id.techPassword);
    }
    public void OnLogin(View view){
        String str_id=id.getText().toString();
        String password=pw.getText().toString();
        String type="loginTech";
        BackgroundWorker backgroundWorker=new BackgroundWorker(this);
        backgroundWorker.execute(type,str_id,password);
    }
}
