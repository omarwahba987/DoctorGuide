package com.example.mahfouz.doctorgp;

import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
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
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class AppointmentDetailsActivity extends AppCompatActivity {

MediaPlayer audio_player;
    Button report,contact,about;
    TextView email,uname,stime;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment_details);


        contact = (Button) findViewById(R.id.contappd);
        about = (Button) findViewById(R.id.aboutappd);

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

        report = (Button) findViewById(R.id.report);
        email  = (TextView) findViewById(R.id.email11);
        uname  = (TextView) findViewById(R.id.uname);
        stime  = (TextView) findViewById(R.id.stime);

        final String p_email , p_uname , p_stime;
        p_email = getIntent().getExtras().getString("p_email");
        p_uname = getIntent().getExtras().getString("p_uname");
        p_stime = getIntent().getExtras().getString("p_stime");
        final String[] URL3 = {"http://doctorguide.000webhostapp.com/API/ReportAbcence?Email="};

        email.setText("ايميل المريض : "+p_email);
        uname.setText("ااسم المريض : "+p_uname);
        stime.setText("حجز الميعاد الساعه : "+p_stime);

        report.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(AppointmentDetailsActivity.this);

                builder.setTitle("تأكيد");
                builder.setMessage("هل تريد الابلاغ عن غياب هذا المريض");

                builder.setPositiveButton("نعم", new DialogInterface.OnClickListener() {


                    public void onClick(DialogInterface dialog, int which) {
                        //Toast.makeText(AppointmentDetailsActivity.this,"succed", Toast.LENGTH_SHORT).show();


                        URL3[0] = URL3[0] + p_email;

                        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL3[0], new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {

                              //  Toast.makeText(AppointmentDetailsActivity.this,response.toString(), Toast.LENGTH_SHORT).show();


                            }
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                //Toast.makeText(getApplicationContext(),error+"",Toast.LENGTH_SHORT).show();
                            }
                        });
                        Volley.newRequestQueue(getApplicationContext()).add(stringRequest);




                        dialog.dismiss();
                    }
                });

                builder.setNegativeButton("لا", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                       // Toast.makeText(AppointmentDetailsActivity.this,"failed", Toast.LENGTH_SHORT).show();
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
