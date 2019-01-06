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

public class EleComplaints extends AppCompatActivity {

    ListView listView;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ele_complaints);
        listView=(ListView)findViewById(R.id.ELElistview);
        adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1);
        listView.setAdapter(adapter);
        new EleComplaints.Connection().execute();

    }


    class Connection extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... params) {
            String result="";
            String host="http://192.168.43.201/android/v1/eleComplaints.php";

            try {
                HttpClient client=new DefaultHttpClient();
                HttpGet request=new HttpGet();
                request.setURI(new URI(host));
                HttpResponse response=client.execute(request);
                BufferedReader reader=new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
                StringBuffer stringBuffer=new StringBuffer("");
                String line="";
                while((line=reader.readLine())!=null){
                    stringBuffer.append(line);
                    break;
                }
                reader.close();
                result=stringBuffer.toString();
            } catch (Exception e) {
                return new String("There exception: "+e.getMessage());
            }
            return result;
        }

        @Override
        protected void onPostExecute(String result) {
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
                        String psdate=complaint.getString("ProbStart_date");
                        String room=complaint.getString("Room_no");
                        String tid=complaint.getString("TECHN_ID");
                        String status=complaint.getString("Status");
                        String line=id+" "+desc+" "+idate+" "+psdate+" "+room+" "+tid+" "+status;
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
}
