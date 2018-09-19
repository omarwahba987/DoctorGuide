package com.example.mahfouz.doctorgp;

import android.content.Intent;
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

public class RegistrationActivity extends AppCompatActivity {
    private EditText user_name,email,pass,phone,gender,age;
private Button register,contact,about ;
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);


        final String[] URL = {"http://doctorguide.000webhostapp.com/API/Registration?Data="};
        register= (Button) findViewById(R.id.register);
        user_name= (EditText) findViewById(R.id.name);


        email= (EditText) findViewById(R.id.email);

        pass= (EditText) findViewById(R.id.password);
        phone= (EditText) findViewById(R.id.pnumber);
        gender= (EditText) findViewById(R.id.gender);
        age= (EditText) findViewById(R.id.age);
        final boolean[] flag = {true};
        final String[] data = {""};



        contact = (Button) findViewById(R.id.contactreg);
        about = (Button) findViewById(R.id.aboutreg);
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

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(user_name.getText().toString().equals("")||email.getText().toString().equals("")||pass.getText().toString().equals("")||phone.getText().toString().equals("")||gender.getText().toString().equals("")||age.getText().toString().equals(""))
                {
                    Toast.makeText(getApplicationContext(), "please fill all fieldes", Toast.LENGTH_SHORT).show();
                    flag[0] =false;

                }
                else if (!email.getText().toString().contains("@"))
                {
                    email.setText("");
                    Toast.makeText(getApplicationContext(), "من فضلك ادخل الايميل !", Toast.LENGTH_SHORT).show();
                    flag[0] =false;

                }
                if (flag[0]==false)
                {
                    Intent intent= new Intent(getApplicationContext(),RegistrationActivity.class);
                    startActivity(intent);
                }


                data[0] = user_name.getText().toString().trim() +","+email.getText().toString().trim()+","+pass.getText().toString().trim()+","+gender.getText().toString().trim()+","+age.getText().toString().trim()+","+phone.getText().toString().trim();
                if(data[0].contains(" "))
                {
                    data[0] = data[0].replaceAll(" ","_");
                }

                //Toast.makeText(RegistrationActivity.this, data[0], Toast.LENGTH_SHORT).show();

                URL[0] = URL[0] +data[0];
                StringRequest stringRequest = new StringRequest(Request.Method.GET,URL[0], new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                            if(response.equals("1"))
                            {
                                Toast.makeText(getApplicationContext(),"يمكنك تسجيل دخول الان ", Toast.LENGTH_LONG).show();
                                Intent h = new Intent(getApplicationContext(),MainActivity.class);
                                startActivity(h);
                            }
                            else{
                                Toast.makeText(getApplicationContext(),"هذا الايميل تكرر سابقا .. ادخل ايميلا جديدا ! ", Toast.LENGTH_LONG).show();
                                Intent h = new Intent(getApplicationContext(),RegistrationActivity.class);
                                startActivity(h);
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
