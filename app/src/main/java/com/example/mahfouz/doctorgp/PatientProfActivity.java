package com.example.mahfouz.doctorgp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class PatientProfActivity extends AppCompatActivity {


    private Spinner spinner2;
    private Button seerch;
    TextView name , abcence;
    Button logout,show,contact,about;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_prof);


        show = (Button) findViewById(R.id.showfd);
        logout = (Button) findViewById(R.id.logout);

        name = (TextView) findViewById(R.id.patientName);
        abcence = (TextView) findViewById(R.id.abcencee);

        contact = (Button) findViewById(R.id.contactpp);
        about = (Button) findViewById(R.id.aboutpp);
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


        String URL = "http://doctorguide.000webhostapp.com/API/GetPatient?Email=";

        final SharedPreferences p_session = getSharedPreferences("p", 0);
        final SharedPreferences.Editor editor = p_session.edit();

        URL = URL + p_session.getString("email", "");

        JsonArrayRequest jsonArrayReq = new JsonArrayRequest(URL,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {


                        JSONObject object = new JSONObject();
                        try {
                            object = response.getJSONObject(0);
                            name.setText(object.getString("name"));
                            abcence.setText("معدل غيابك => " + object.getString("ReprtAsbence"));

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
               // Toast.makeText(getApplicationContext(), error + "ffff", Toast.LENGTH_LONG).show();

            }
        });


        // Adding String request to request queue
        Volley.newRequestQueue(this).add(jsonArrayReq);

//        addListenerOnButton();
//        addListenerOnSpinnerItemSelection();

abcence.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Toast.makeText(PatientProfActivity.this, "لا يمكنك الحجز اذا تعدى معدل غيابك عن 3", Toast.LENGTH_SHORT).show();
    }
});
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PatientProfActivity.this.finish();
                editor.clear();
                editor.commit();
                Intent h = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(h);
            }
        });


        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent gooo = new Intent(getApplicationContext(), SearchListDocActivity.class);
                gooo.putExtra("pemail", p_session.getString("email", ""));
                gooo.putExtra("smethod", "fvlist");
                startActivity(gooo);


            }
        });




        addListenerOnButton();
        addListenerOnSpinnerItemSelection();
}


    public void addListenerOnSpinnerItemSelection() {
        spinner2 = (Spinner) findViewById(R.id.spinner2);
        spinner2.setOnItemSelectedListener(new CustomOnItemSelectedListener());
    }

    public void addListenerOnButton() {

        spinner2 = (Spinner) findViewById(R.id.spinner1);
        seerch = (Button) findViewById(R.id.seerch);



        seerch.setOnClickListener(new View.OnClickListener() {

                                         @Override
                                         public void onClick(View v) {

                                             if(spinner2.getSelectedItem().equals("بالتخصص")){
                                                 Intent goTo = new Intent(getApplicationContext(), SpecialityActivity.class);
                                                 startActivity(goTo);
                                             }
                                             else if(spinner2.getSelectedItem().equals("مناسب لآعراضك")){
                                                 Intent gooo = new Intent(getApplicationContext(),SymActivity.class);
                                                 startActivity(gooo);
                                             }
                                             else if(spinner2.getSelectedItem().equals("بالآسم")){
                                                 Intent gooo = new Intent(getApplicationContext(),DocNameActivity.class);
                                                 startActivity(gooo);
                                             }
                                             else if(spinner2.getSelectedItem().equals("بالعنوان")){
                                                 Intent gooo = new Intent(getApplicationContext(),LocationActivity.class);
                                                 startActivity(gooo);
                                             }
                                         }
                                     }
        );}
}
