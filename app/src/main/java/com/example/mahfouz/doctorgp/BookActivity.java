package com.example.mahfouz.doctorgp;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
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

import java.util.ArrayList;


public class BookActivity extends AppCompatActivity {

Button p1 , p2 , p3 ,p4 , p5 ,p6 , contact , about;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);





        p1 = (Button) findViewById(R.id.p1);
        p2 = (Button) findViewById(R.id.p2);
        p3 = (Button) findViewById(R.id.p3);
        p4 = (Button) findViewById(R.id.p4);
        p5 = (Button) findViewById(R.id.p5);
        p6 = (Button) findViewById(R.id.p6);

        contact = (Button) findViewById(R.id.contactbook);
        about = (Button) findViewById(R.id.aboutbook);

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



        final String[] URL3 = {"http://doctorguide.000webhostapp.com/API/Book?Data="};


        final ArrayList<String> Reserved = new ArrayList<>();
        final String d_email = getIntent().getExtras().getString("d_email").toString();
        String URL  = "http://doctorguide.000webhostapp.com/API/GetStartDate?Email=";
        URL = URL + d_email;


        final JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(URL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                for (int i = 0; i < response.length(); i++) {

                    JSONObject object = new JSONObject();
                    try {
                        object = response.getJSONObject(i);
                        Reserved.add(object.getString("StartTime"));



            } catch (JSONException e) {
                        e.printStackTrace();
                    }


                }

                for(int i=0 ; i<Reserved.size();i++) {

                    if (Reserved.get(i).equals("7")) {
                        p1.setEnabled(false);
                    } else if (Reserved.get(i).equals("7.30")) {
                        p2.setEnabled(false);
                    } else if (Reserved.get(i).equals("8")) {
                        p3.setEnabled(false);
                    } else if (Reserved.get(i).equals("8.30")) {
                        p4.setEnabled(false);
                    } else if (Reserved.get(i).equals("9")) {
                        p5.setEnabled(false);
                    } else if (Reserved.get(i).equals("9.30")) {
                        p6.setEnabled(false);
                    }
                }


            }} , new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }
        );


        Volley.newRequestQueue(this).add(jsonArrayRequest);



        final SharedPreferences p_session = getSharedPreferences("p", 0);
        SharedPreferences.Editor editor = p_session.edit();



        p1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(BookActivity.this);

                builder.setTitle("تأكيد");
                builder.setMessage("هل تريد حجز الميعاد ؟");

                builder.setPositiveButton("نعم", new DialogInterface.OnClickListener() {


                    public void onClick(DialogInterface dialog, int which) {
                        // Do nothing but close the dialog
                        Toast.makeText(BookActivity.this,"لقد تم حجز الميعد", Toast.LENGTH_SHORT).show();

                        URL3[0]=URL3[0]+d_email + "," +p_session.getString("email","")+","+"7";

                        StringRequest stringRequest = new StringRequest(Request.Method.GET,URL3[0], new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {

                                //Toast.makeText(BookActivity.this,response.toString(), Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(),DocProfActivity.class);
                                intent.putExtra("docemail",d_email);
                                startActivity(intent);

                            }
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                               // Toast.makeText(getApplicationContext(),error+"",Toast.LENGTH_SHORT).show();
                            }
                        });
                        Volley.newRequestQueue(getApplicationContext()).add(stringRequest);




                        dialog.dismiss();
                    }
                });

                builder.setNegativeButton("لا", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                     //   Toast.makeText(BookActivity.this,"failed", Toast.LENGTH_SHORT).show();
                        // Do nothing
                        dialog.dismiss();
                    }
                });

                AlertDialog alert = builder.create();
                alert.show();

            }
        });



        p2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(BookActivity.this);

                builder.setTitle("تأكيد");
                builder.setMessage("هل تريد حجز الميعاد ؟");

                builder.setPositiveButton("نعم", new DialogInterface.OnClickListener() {


                    public void onClick(DialogInterface dialog, int which) {
                        // Do nothing but close the dialog
                        Toast.makeText(BookActivity.this,"لقد تم حجز الميعاد", Toast.LENGTH_SHORT).show();

                        URL3[0]=URL3[0]+d_email + "," +p_session.getString("email","")+","+"7_30";

                        StringRequest stringRequest = new StringRequest(Request.Method.GET,URL3[0], new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {

                             //   Toast.makeText(BookActivity.this,response.toString(), Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(),DocProfActivity.class);
                                intent.putExtra("docemail",d_email);
                                startActivity(intent);

                            }
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                               // Toast.makeText(getApplicationContext(),error+"",Toast.LENGTH_SHORT).show();
                            }
                        });
                        Volley.newRequestQueue(getApplicationContext()).add(stringRequest);




                        dialog.dismiss();
                    }
                });

                builder.setNegativeButton("لا", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                       // Toast.makeText(BookActivity.this,"failed", Toast.LENGTH_SHORT).show();
                        // Do nothing
                        dialog.dismiss();
                    }
                });

                AlertDialog alert = builder.create();
                alert.show();

            }
        });




        p3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(BookActivity.this);

                builder.setTitle("تأكيد");
                builder.setMessage("هل تريد حجز الميعاد ؟");

                builder.setPositiveButton("نعم", new DialogInterface.OnClickListener() {


                    public void onClick(DialogInterface dialog, int which) {
                        // Do nothing but close the dialog
                        Toast.makeText(BookActivity.this,"لقد تم حجز الميعاد", Toast.LENGTH_SHORT).show();

                        URL3[0]=URL3[0]+d_email + "," +p_session.getString("email","")+","+"8";

                        StringRequest stringRequest = new StringRequest(Request.Method.GET,URL3[0], new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {

                                //Toast.makeText(BookActivity.this,response.toString(), Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(),DocProfActivity.class);
                                intent.putExtra("docemail",d_email);
                                startActivity(intent);

                            }
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                               // Toast.makeText(getApplicationContext(),error+"",Toast.LENGTH_SHORT).show();
                            }
                        });
                        Volley.newRequestQueue(getApplicationContext()).add(stringRequest);




                        dialog.dismiss();
                    }
                });

                builder.setNegativeButton("لا", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                       // Toast.makeText(BookActivity.this,"failed", Toast.LENGTH_SHORT).show();
                        // Do nothing
                        dialog.dismiss();
                    }
                });

                AlertDialog alert = builder.create();
                alert.show();

            }
        });



        p4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(BookActivity.this);

                builder.setTitle("تأكيد");
                builder.setMessage("هل تريد حجز الميعاد ؟");

                builder.setPositiveButton("نعم", new DialogInterface.OnClickListener() {


                    public void onClick(DialogInterface dialog, int which) {
                        // Do nothing but close the dialog
                        Toast.makeText(BookActivity.this,"لقد تم حجز الميعاد", Toast.LENGTH_SHORT).show();

                        URL3[0]=URL3[0]+d_email + "," +p_session.getString("email","")+","+"8_30";

                        StringRequest stringRequest = new StringRequest(Request.Method.GET,URL3[0], new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {

                               // Toast.makeText(BookActivity.this,response.toString(), Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(),DocProfActivity.class);
                                intent.putExtra("docemail",d_email);
                                startActivity(intent);

                            }
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                              //  Toast.makeText(getApplicationContext(),error+"",Toast.LENGTH_SHORT).show();
                            }
                        });
                        Volley.newRequestQueue(getApplicationContext()).add(stringRequest);




                        dialog.dismiss();
                    }
                });

                builder.setNegativeButton("لا", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                       // Toast.makeText(BookActivity.this,"failed", Toast.LENGTH_SHORT).show();
                        // Do nothing
                        dialog.dismiss();
                    }
                });

                AlertDialog alert = builder.create();
                alert.show();

            }
        });





        p5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(BookActivity.this);

                builder.setTitle("تأكيد");
                builder.setMessage("هل تريد حجز الميعاد ؟");

                builder.setPositiveButton("نعم", new DialogInterface.OnClickListener() {


                    public void onClick(DialogInterface dialog, int which) {
                        // Do nothing but close the dialog
                        Toast.makeText(BookActivity.this,"لقد تم حجز الميعاد", Toast.LENGTH_SHORT).show();

                        URL3[0]=URL3[0]+d_email + "," +p_session.getString("email","")+","+"9";

                        StringRequest stringRequest = new StringRequest(Request.Method.GET,URL3[0], new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {

                               // Toast.makeText(BookActivity.this,response.toString(), Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(),DocProfActivity.class);
                                intent.putExtra("docemail",d_email);
                                startActivity(intent);

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
                       // Toast.makeText(BookActivity.this,"failed", Toast.LENGTH_SHORT).show();
                        // Do nothing
                        dialog.dismiss();
                    }
                });

                AlertDialog alert = builder.create();
                alert.show();

            }
        });





        p6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(BookActivity.this);

                builder.setTitle("تأكيد");
                builder.setMessage("هل تريد حجز الميعاد ؟");

                builder.setPositiveButton("نعم", new DialogInterface.OnClickListener() {


                    public void onClick(DialogInterface dialog, int which) {
                        // Do nothing but close the dialog
                        Toast.makeText(BookActivity.this,"لقد تم حجز الميعاد", Toast.LENGTH_SHORT).show();

                        URL3[0]=URL3[0]+d_email + "," +p_session.getString("email","")+","+"9_30";

                        StringRequest stringRequest = new StringRequest(Request.Method.GET,URL3[0], new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {

                               // Toast.makeText(BookActivity.this,response.toString(), Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(),DocProfActivity.class);
                                intent.putExtra("docemail",d_email);
                                startActivity(intent);

                            }
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                               // Toast.makeText(getApplicationContext(),error+"",Toast.LENGTH_SHORT).show();
                            }
                        });
                        Volley.newRequestQueue(getApplicationContext()).add(stringRequest);




                        dialog.dismiss();
                    }
                });

                builder.setNegativeButton("لا", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //Toast.makeText(BookActivity.this,"failed", Toast.LENGTH_SHORT).show();
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
