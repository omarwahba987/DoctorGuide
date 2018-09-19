package com.example.mahfouz.doctorgp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class AppointmentListActivity extends AppCompatActivity {


    Button about , contact;
    ListView listView ;

     ArrayList<String> app_data=new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment_list);
        String URL="http://doctorguide.000webhostapp.com/API/ViewAppointment?DoctorEmail=";
        final ArrayList<Appointment> data_object = new ArrayList<>();

        final ArrayList<String> app_data=new ArrayList<String>();



        contact = (Button) findViewById(R.id.contactappl);
        about = (Button) findViewById(R.id.aboutappl);

        contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent g = new Intent(getApplicationContext(),ContactActivity.class);
                startActivity(g);
            }
        });

        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent g = new Intent(getApplicationContext(),AboutActivity.class);
                startActivity(g);
            }
        });


        URL = URL + getIntent().getExtras().getString("d_email");
        JsonArrayRequest jsonArrayReq = new JsonArrayRequest(URL,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {


                        for (int i = 0; i < response.length(); i++) {

                            JSONObject object = new JSONObject();
                            Appointment appointment = new Appointment();
                            try {
                                object = response.getJSONObject(i);

                                //Toast.makeText(getApplicationContext(),object.getString("DoctorName"),Toast.LENGTH_LONG).show();
                                if (object.getString("PatientEmail").equals("Non"))
                                {

                                }
                                else
                                {
                                    appointment.p_email = object.getString("PatientEmail");
                                    appointment.p_username = object.getString("PatientUserName");
                                    appointment.s_time = object.getString("StartTime");
                                    app_data.add(appointment.s_time);
                                    data_object.add(appointment);
                                }





                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }

                        ArrayAdapter adapter = new ArrayAdapter<String>(getApplicationContext(),R.layout.appointmentcell,app_data);

                        listView = (ListView) findViewById(R.id.appLS);
                        listView.setAdapter(adapter);


                        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                Intent g = new Intent(getApplicationContext(),AppointmentDetailsActivity.class);
                                g.putExtra("p_email",data_object.get(position).p_email);
                                g.putExtra("p_uname",data_object.get(position).p_username);
                                g.putExtra("p_stime",data_object.get(position).s_time);
                                startActivity(g);
                            }
                        });



                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
      //          Toast.makeText(getApplicationContext(),error+"",Toast.LENGTH_LONG).show();

            }
        });


        // Adding String request to request queue
        Volley.newRequestQueue(this).add(jsonArrayReq);

        //Toast.makeText(AppointmentListActivity.this,app_data.toString().trim(), Toast.LENGTH_SHORT).show();











    }

    }

