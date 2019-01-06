package com.example.android.hostelcomplaintmanagement;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;

public class AdminHome extends AppCompatActivity {

    Button b1,b2,b3,b4,b5,b6;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home);
        b1=(Button)findViewById(R.id.ButtonELE);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Intent = new Intent(view.getContext(),EleComplaints.class);
                view.getContext().startActivity(Intent);}
        });
        b2=(Button)findViewById(R.id.ButtonNET);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Intent = new Intent(view.getContext(),NetComplaints.class);
                view.getContext().startActivity(Intent);}
        });
        b3=(Button)findViewById(R.id.ButtonPLU);
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Intent = new Intent(view.getContext(),PluComplaints.class);
                view.getContext().startActivity(Intent);}
        });
        b4=(Button)findViewById(R.id.ButtonCAR);
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Intent = new Intent(view.getContext(),CarComplaints.class);
                view.getContext().startActivity(Intent);}
        });
        b5=(Button)findViewById(R.id.ButtonAPP);
        b5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Intent = new Intent(view.getContext(),AppComplaints.class);
                view.getContext().startActivity(Intent);}
        });
        b6=(Button)findViewById(R.id.ButtonOTH);
        b6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Intent = new Intent(view.getContext(),OthComplaints.class);
                view.getContext().startActivity(Intent);}
        });
    }

}
