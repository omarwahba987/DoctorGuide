package com.example.mahfouz.doctorgp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class LoginActivity extends AppCompatActivity {


    final String[] URL = {"http://doctorguide.000webhostapp.com/API/Login?Data="};

    final String[] data = {""};


    EditText email1,password;
    Button login,contact,about;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email1 = (EditText) findViewById(R.id.name);
        password = (EditText) findViewById(R.id.pass);
        login = (Button) findViewById(R.id.login);


        contact = (Button) findViewById(R.id.contactlogin);
        about = (Button) findViewById(R.id.aboutlogin);

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

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (email1.getText().toString().equals("")|| password.getText().toString().equals(""))
                {
                    Toast.makeText(LoginActivity.this, "من فضلك ادخل جميع البيانات ! ", Toast.LENGTH_SHORT).show();
                }

                data[0] = email1.getText().toString().trim() +","+password.getText().toString().trim();

                URL[0] = URL[0] +data[0];

                StringRequest stringRequest = new StringRequest(Request.Method.GET,URL[0], new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if(response.equals("1"))
                        {   LoginActivity.this.finish();
                            //Toast.makeText(getApplicationContext(),response.toString(), Toast.LENGTH_SHORT).show();
                            SharedPreferences p_session = getSharedPreferences("p", 0);
                            SharedPreferences.Editor editor = p_session.edit();
                            editor.putString("email", email1.getText().toString());
                            editor.commit();
                            Intent h = new Intent(getApplicationContext(),PatientProfActivity.class);
                            startActivity(h);
                        }
                        else if (response.equals("11")){
                            LoginActivity.this.finish();
                            SharedPreferences d_session = getSharedPreferences("d", 0);
                            SharedPreferences.Editor eeditor = d_session.edit();
                            eeditor.putString("email", email1.getText().toString());
                            eeditor.commit();

                            Intent h = new Intent(getApplicationContext(),DocPersonalActivity.class);
                            startActivity(h);
                        }
                        else
                        {
                            Intent h = new Intent(getApplicationContext(),LoginActivity.class);
                            startActivity(h);
                            Toast.makeText(LoginActivity.this, "من فضلك ادخل الايميل او كلمة السر صحيحة ! ", Toast.LENGTH_SHORT).show();

                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                       // Toast.makeText(getApplicationContext(),error+"",Toast.LENGTH_LONG).show();
                    }
                });
                Volley.newRequestQueue(getApplicationContext()).add(stringRequest);

            }
        });






    }
}
