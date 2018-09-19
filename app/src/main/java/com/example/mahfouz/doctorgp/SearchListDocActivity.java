package com.example.mahfouz.doctorgp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
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



public class SearchListDocActivity extends AppCompatActivity {


    CustomeListAdapter adapter ;//= new CustomeListAdapter(getApplicationContext(),R.layout.celllayout,)
    ArrayList<Doctor> doctor = new ArrayList<Doctor>();
    Button contact,about;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_list_doc);



        contact = (Button) findViewById(R.id.contactlist);
        about = (Button) findViewById(R.id.aboutlist);
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

        final CustomeListAdapter adapter ;//= new CustomeListAdapter(getApplicationContext(),R.layout.celllayout,)
        final ArrayList<Doctor> doctor = new ArrayList<Doctor>();

        final ListView mylist = (ListView) findViewById(R.id.doctorlist);

        adapter = new CustomeListAdapter(getApplicationContext(),R.layout.doctor_cell,doctor);
        mylist.setAdapter(adapter);
        mylist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent g = new Intent(getApplicationContext(),DocProfActivity.class);
                g.putExtra("docname",doctor.get(position).getDoctorName());
                startActivity(g);
            }
        });

        //String URL = "http://10.0.2.2:8000/getDoctorsBySpecialist?specialist=";
        //String URL = "http://localhost:8081/getDoctorsBySpecialist?specialist=";



       //ArrayList<String> chooses = new ArrayList<>(getIntent().getExtras().getStringArrayList("Symptoms"));

        //Toast.makeText(getApplicationContext(),chooses.toString(),Toast.LENGTH_LONG).show();


        String URL=null;
         String x= null;

        String method = getIntent().getExtras().getString("smethod");

        if(method.equals("speciallity")) {
               x = getIntent().getExtras().getString("Speciallity").toString();

            if(x.contains(" "))
            {
                x = x.replaceAll(" ","_");
            }
             URL="http://doctorguide.000webhostapp.com/API/getDoctorsBySpecialist?specialist=";
        URL += x;

        }

        else if(method.equals("symptoms")) {

              x = getIntent().getExtras().getString("symptoms").toString();


           URL = "http://doctorguide.000webhostapp.com/API/GetAndroidSymptoms?symptoms=";
            URL+=x;
            //Toast.makeText(getApplicationContext(),URL,Toast.LENGTH_LONG).show();


        }

        else if(method.equals("docname")) {

              x = getIntent().getExtras().getString("docname").toString();
            if(x.contains(" "))
            {
                x = x.replaceAll(" ","_");
            }

            URL = "http://doctorguide.000webhostapp.com/API/getDoctorsByDoctorName?DoctorName=";
            URL+=x;
            //Toast.makeText(getApplicationContext(),x,Toast.LENGTH_LONG).show();

        }

        else if(method.equals("Location")) {

              x = "السيدة_زينب";

            URL = "http://doctorguide.000webhostapp.com/API/getDoctorsByLocation";
        }

        else if(method.equals("fvlist")) {

            x = getIntent().getExtras().getString("pemail").toString();

            URL = "http://doctorguide.000webhostapp.com/API/GetFavorite?PatientEmail=";
            URL+=x;
        }


            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setMessage("Loading...");
            progressDialog.show();


            JsonArrayRequest jsonArrayReq = new JsonArrayRequest(URL,
                    new Response.Listener<JSONArray>() {
                        @Override
                        public void onResponse(JSONArray response) {


                            for (int i = 0; i < response.length(); i++) {

                                JSONObject object = new JSONObject();
                                Doctor doctorData = new Doctor();
                                try {
                                    object = response.getJSONObject(i);

                                    //Toast.makeText(getApplicationContext(),object.getString("DoctorName"),Toast.LENGTH_LONG).show();

                                    doctorData.setDoctorId(object.getString("DoctorId"));
                                    doctorData.setDoctorName(object.getString("DoctorName"));
                                    doctorData.setEmail(object.getString("Email"));
                                    doctorData.setPassword(object.getString("Password"));
                                    doctorData.setPhoneNumber(object.getString("Rate"));
                                    doctorData.setGender(object.getString("Gender"));
                                    doctorData.setAge(object.getString("Age"));
                                    doctorData.setSpeciality(object.getString("Specialist"));
                                    doctorData.setAcademicInfo(object.getString("Academic_Information"));
                                    doctorData.setPhotoUrl(object.getString("photoURL"));
                                    doctorData.setRate(object.getString("Rate"));

                                    doctor.add(doctorData);

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                            }

                            adapter.notifyDataSetChanged();
                            //Toast.makeText(getApplicationContext(),response.toString(),Toast.LENGTH_LONG).show();

                            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(SearchListDocActivity.this);


                            progressDialog.hide();
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                   // Toast.makeText(getApplicationContext(),error+"",Toast.LENGTH_LONG).show();

                    progressDialog.hide();
                }
            });


            // Adding String request to request queue
            Volley.newRequestQueue(this).add(jsonArrayReq);

      mylist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
          @Override
          public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
              Intent g = new Intent(getApplicationContext(),DocProfActivity.class);
              g.putExtra("docemail",doctor.get(position).getEmail());
              startActivity(g);
          }
      });




        }


}
