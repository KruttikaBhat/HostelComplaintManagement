package com.example.android.hostelcomplaintmanagement;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URI;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

public class StudentHome extends AppCompatActivity {


    Button electricalButton,Refresh;
    ListView listView;
    ArrayAdapter<String> adapter;
    String IdHolder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_home);

        Spinner spinnercivil=(Spinner)findViewById(R.id.spinnerCivil);
        ArrayAdapter<CharSequence> civil= ArrayAdapter.createFromResource(this,R.array.civil, android.R.layout.simple_spinner_item);
        civil.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnercivil.setAdapter(civil);

        Spinner spinnerother=(Spinner)findViewById(R.id.spinnerOthers);
        ArrayAdapter<CharSequence> others= ArrayAdapter.createFromResource(this,R.array.others, android.R.layout.simple_spinner_item);
        others.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerother.setAdapter(others);

        IdHolder = getIntent().getStringExtra("Id");

        listView = (ListView) findViewById(R.id.studentlistview);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);
        listView.setAdapter(adapter);
        new SendPostRequest().execute();

        electricalButton = (Button) findViewById(R.id.ElectricalButton);
        electricalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), ElectricalComplaint.class);
                intent.putExtra("RollNo", IdHolder);
                view.getContext().startActivity(intent);
            }
        });

        Refresh = (Button) findViewById(R.id.StudentRefresh);
        Refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = getIntent();
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                finish();
                startActivity(intent);
            }
        });
    }

    public class SendPostRequest extends AsyncTask<String, Void, String>{
        @Override
        protected void onPreExecute() {

        }

        @Override
        protected String doInBackground(String... strings) {
            try {
                URL url= new URL("http://192.168.43.201/android/v1/studentComplaints.php");

                JSONObject postDataParams= new JSONObject();
                postDataParams.put("Student_Rollno",IdHolder);
                Log.e("params",postDataParams.toString());
                HttpURLConnection conn=(HttpURLConnection) url.openConnection();
                conn.setReadTimeout(15000);
                conn.setConnectTimeout(15000);
                conn.setRequestMethod("POST");
                conn.setDoInput(true);
                conn.setDoOutput(true);

                OutputStream os=conn.getOutputStream();
                    BufferedWriter writer=new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
                    writer.write(getPostDataString(postDataParams));
                    writer.flush();
                    writer.close();
                    os.close();

                    int responseCode=conn.getResponseCode();

                    if(responseCode== HttpsURLConnection.HTTP_OK){
                        BufferedReader in=new BufferedReader(new InputStreamReader(conn.getInputStream()));
                        StringBuffer sb=new StringBuffer("");
                        String line="";

                        while((line = in.readLine()) != null){
                            sb.append(line);
                            break;
                        }
                        in.close();
                        return sb.toString();
                    }
                    else{
                        return new String("false: "+responseCode);
                    }
            } catch (Exception e) {
                //e.printStackTrace();
                return new String("Exception: "+e.getMessage());
            }

        }

        @Override
        protected void onPostExecute(String result) {
            //Toast.makeText(getApplicationContext(),result, Toast.LENGTH_LONG).show();
            try {
                JSONObject jsonResult=new JSONObject(result);
                int success=jsonResult.getInt("success");
                if(success==1){
                    JSONArray complaints=jsonResult.getJSONArray("complaint");
                    //String header="C_id"+"  "+"Description"+" "+"Issued date"+" "+"Resolved date"+" "+"Start date"+" "+"staff name";
                    //adapter.add(header);
                    for (int i=0;i<complaints.length();i++){
                        JSONObject complaint=complaints.getJSONObject(i);
                        String id=complaint.getString("C_id");
                        String desc=complaint.getString("Description");
                        String idate=complaint.getString("Issued_date");
                        String Staff=complaint.getString("S_NAME");
                        String StaffNo=complaint.getString("S_PH_NO");
                        String status=complaint.getString("Status");
                        String line=id+" "+desc+" "+idate+" "+Staff+" "+StaffNo+" "+" "+status;
                        adapter.add(line);
                    }
                }
                else{
                    Toast.makeText(getApplicationContext(),"no electrical complaints",Toast.LENGTH_SHORT).show();
                }
            } catch (JSONException e) {
                Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_LONG).show();
            }

        }
    }

    public String getPostDataString(JSONObject params) throws Exception{
        StringBuilder result=new StringBuilder();
        boolean first=true;

        Iterator<String> itr=params.keys();
        while(itr.hasNext()){

            String key=itr.next();
            Object value=params.get(key);
            if(first)
                first=false;
            else
                result.append("&");
            result.append(URLEncoder.encode(key,"UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(value.toString(), "UTF-8"));

        }
       return result.toString();
    }

}



