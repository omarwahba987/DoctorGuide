package com.example.mahfouz.doctorgp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

public class DocProfActivity extends AppCompatActivity {


    Button add,rate,getmap,book,contact,about;
    EditText rr;
    TextView name,spec,academic,rrate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doc_prof);

        final String[] URL = {"http://doctorguide.000webhostapp.com/API/AddFavorite?Data="};

        final String[] URL1 = {"http://doctorguide.000webhostapp.com/API/Rate?Data="};

        final String[] URL2 = {"http://doctorguide.000webhostapp.com/API/getDoctorLocation?Email="};

        final String[] URL3 = {"https://www.google.com/maps/dir/?api=1&destination="};

        final String[] URL4 = {"http://doctorguide.000webhostapp.com/API/GetAbcenceRate?Email="};



        name = (TextView) findViewById(R.id.doctorprofilename);
        spec = (TextView) findViewById(R.id.doctorprofilespec);
        academic = (TextView) findViewById(R.id.doctorprofileaca);
        rrate = (TextView) findViewById(R.id.doctorprofilerate);



        final String doc_email = getIntent().getExtras().getString("docemail");
        add = (Button) findViewById(R.id.add);
        book = (Button) findViewById(R.id.book);
        getmap = (Button) findViewById(R.id.getmap);
        rr = (EditText) findViewById(R.id.rr);
        rate = (Button) findViewById(R.id.rate);
        final SharedPreferences p_session = getSharedPreferences("p", 0);
        final SharedPreferences.Editor editor = p_session.edit();
        final String p_email = p_session.getString("email","");



        contact = (Button) findViewById(R.id.contactdprofail);
        about = (Button) findViewById(R.id.aboutdprofail);

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


        String URL5 = "http://doctorguide.000webhostapp.com/API/GetDoctor?Email=";
        URL5 = URL5 + doc_email;
        //Toast.makeText(this, doc_email, Toast.LENGTH_SHORT).show();
        JsonArrayRequest jsonArrayReq = new JsonArrayRequest(URL5,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {

                        JSONObject object = new JSONObject();
                        try {
                            object = response.getJSONObject(0);
                          //  Toast.makeText(DocProfActivity.this, response.toString(), Toast.LENGTH_SHORT).show();
                            name.setText(" دكتور :  " + object.getString("DoctorName"));
                            spec.setText(" متخصص فى :  " + object.getString("Specialist"));
                            rrate.setText(" تقييم الدكتور : " + object.getString("Rate"));
                            academic.setText(" معلومات عنه : " + object.getString("Academic_Information"));


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
               Toast.makeText(getApplicationContext(),error+"",Toast.LENGTH_LONG).show();

            }
        });


        // Adding String request to request queue
        Volley.newRequestQueue(this).add(jsonArrayReq);


        //Toast.makeText(this,doc_email + " " + p_email , Toast.LENGTH_SHORT).show();

        URL[0] = URL[0] + p_email + "," + doc_email;




        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(p_email.equals(""))
                {
                    Toast.makeText(DocProfActivity.this, " يجب تسجيل الدخول اولا . ", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    StringRequest stringRequest = new StringRequest(Request.Method.GET,URL[0], new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            //Toast.makeText(DocProfActivity.this,response.toString().trim(), Toast.LENGTH_SHORT).show();
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                         //   Toast.makeText(getApplicationContext(),"لقد حدث خطأ ما !",Toast.LENGTH_SHORT).show();
                        }
                    });
                    Volley.newRequestQueue(getApplicationContext()).add(stringRequest);
                }
            }
        });


        rate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(p_email.equals(""))
                {
                    Toast.makeText(DocProfActivity.this, " يجب تسجيل الدخول اولا . ", Toast.LENGTH_SHORT).show();
                }
                else {

                    if (Integer.valueOf(rr.getText().toString()) > 5 || Integer.valueOf(rr.getText().toString()) < 1) {
                        Toast.makeText(DocProfActivity.this, "من فضلك ادخل قيمة من 1 الى 5 ! ", Toast.LENGTH_SHORT).show();
                    } else {
                        URL1[0] = URL1[0] + doc_email + "," + rr.getText().toString().trim();
                        rate.setEnabled(false);
                        Toast.makeText(DocProfActivity.this, "لقد تم تسجييل تقييمك", Toast.LENGTH_SHORT).show();

                        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL1[0], new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                //Toast.makeText(DocProfActivity.this, response.toString().trim(), Toast.LENGTH_SHORT).show();
                            }
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                              //  Toast.makeText(getApplicationContext(),"لقد حدث خطأ ما !", Toast.LENGTH_SHORT).show();
                            }
                        });
                        Volley.newRequestQueue(getApplicationContext()).add(stringRequest);
                    }
                }
            }
        });



        URL2[0]=URL2[0]+doc_email;

        getmap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                //Toast.makeText(DocProfActivity.this,URL2[0], Toast.LENGTH_LONG).show();

                StringRequest stringRequest = new StringRequest(Request.Method.GET,URL2[0], new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        URL3[0]=URL3[0]+response.toString();
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(URL3[0]));
                        startActivity(intent);

                        //Toast.makeText(DocProfActivity.this,URL3[0], Toast.LENGTH_SHORT).show();
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                      //  Toast.makeText(getApplicationContext(),"لقد حدث خطأ ما !",Toast.LENGTH_SHORT).show();
                    }
                });
                Volley.newRequestQueue(getApplicationContext()).add(stringRequest);



            }
        });


        URL4[0] = URL4[0] + p_email;


        book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final int[] rate = new int[1];
                StringRequest stringRequest = new StringRequest(Request.Method.GET,URL4[0], new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                          rate[0] = Integer.valueOf(response);
                        if(p_email.equals(""))
                        {
                            Toast.makeText(DocProfActivity.this, " يجب تسجيل الدخول اولا . ", Toast.LENGTH_SHORT).show();
                        }

                        else {
                            if(rate[0]>3)
                            {

                                Toast.makeText(DocProfActivity.this, "لقد وصل معدل غيابك عن مواعيدك المحجوزه للحد الاقصى", Toast.LENGTH_SHORT).show();
                                Toast.makeText(DocProfActivity.this, " لرفع الحظر عن الحجز الرجاء التواصل معنا على الرقم 19777", Toast.LENGTH_SHORT).show();
                            }
                            else
                            {


                                Intent h = new Intent(getApplicationContext(),BookActivity.class);
                                h.putExtra("d_email",doc_email);
                                startActivity(h);}
                        }                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        if(p_email.equals(""))
                        {
                            Toast.makeText(DocProfActivity.this, " يجب تسجيل الدخول اولا . ", Toast.LENGTH_SHORT).show();
                        }
                        else{
                         //   Toast.makeText(getApplicationContext(),"لقد حدث خطأ ما ! ",Toast.LENGTH_SHORT).show();

                        }
                    }
                });
                Volley.newRequestQueue(getApplicationContext()).add(stringRequest);



            }
        });

    }

}
