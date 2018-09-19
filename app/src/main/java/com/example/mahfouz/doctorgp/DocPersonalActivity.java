package com.example.mahfouz.doctorgp;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class DocPersonalActivity extends AppCompatActivity {

    Button logout,showapp,dayoff,contact,about;
    TextView doctorname;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doc_personal);


        String URL ="http://doctorguide.000webhostapp.com/API/DayOff?Email=";
        logout = (Button) findViewById(R.id.logout);
        doctorname = (TextView) findViewById(R.id.doctorpersonalname);

        contact = (Button) findViewById(R.id.contactdpersonal);
        about = (Button) findViewById(R.id.aboutdpersonal);

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


        final SharedPreferences d_session = getSharedPreferences("d", 0);
        final SharedPreferences.Editor editor = d_session.edit();
       // d_email.setText(d_session.getString("email",""));
        String URL5 = "http://doctorguide.000webhostapp.com/API/GetDoctor?Email=";
        URL5 = URL5 + d_session.getString("email","");

        JsonArrayRequest jsonArrayReq = new JsonArrayRequest(URL5,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {

                        JSONObject object = new JSONObject();
                        try {
                            object = response.getJSONObject(0);
                            doctorname.setText("  "+object.getString("DoctorName"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
               // Toast.makeText(getApplicationContext(),error+"",Toast.LENGTH_LONG).show();

            }
        });


        // Adding String request to request queue
        Volley.newRequestQueue(this).add(jsonArrayReq);


        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DocPersonalActivity.this.finish();
                editor.clear();
                editor.commit();
                Intent h = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(h);
            }
        });


        showapp = (Button) findViewById(R.id.showapp);
        showapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),AppointmentListActivity.class);
                intent.putExtra("d_email",d_session.getString("email",""));
                startActivity(intent);
            }
        });


        URL = URL + d_session.getString("email","");

      dayoff = (Button) findViewById(R.id.dayoff);
        final String finalURL = URL;
        dayoff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {





                AlertDialog.Builder builder = new AlertDialog.Builder(DocPersonalActivity.this);

                builder.setTitle("تأكيد");
                builder.setMessage("هل تريد اغلاق مواعيد اليوم ؟");

                builder.setPositiveButton("نعم", new DialogInterface.OnClickListener() {


                    public void onClick(DialogInterface dialog, int which) {
                        //Toast.makeText(AppointmentDetailsActivity.this,"succed", Toast.LENGTH_SHORT).show();




                        StringRequest stringRequest = new StringRequest(Request.Method.GET, finalURL, new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {

                                Toast.makeText(DocPersonalActivity.this, "لقد تم قفل مواعيد اليوم ! ", Toast.LENGTH_SHORT).show();
                            }
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(DocPersonalActivity.this, "لا يمكنك غلق مواعيد اليوم لأنه يوجد فيه حجز   ", Toast.LENGTH_SHORT).show();
                            }
                        });
                        Volley.newRequestQueue(getApplicationContext()).add(stringRequest);



                        dialog.dismiss();
                    }
                });

                builder.setNegativeButton("لا", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    //    Toast.makeText(DocPersonalActivity.this,"failed", Toast.LENGTH_SHORT).show();
                        // Do nothing
                        dialog.dismiss();
                    }
                });

                AlertDialog alert = builder.create();
                alert.show();




            }
        });


    }
}
