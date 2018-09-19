package com.example.mahfouz.doctorgp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    private Spinner spinner1;
    private Button btnSubmit;
    private TextView register;
    private Button login,contact,about;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        contact = (Button) findViewById(R.id.contactmain);
        about = (Button) findViewById(R.id.aboutmain);

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

        final SharedPreferences p_session = getSharedPreferences("p", 0);
        SharedPreferences.Editor editor = p_session.edit();



        //String email= p_session.getString("email","").toString();
        if(! p_session.getString("email","").equals(""))
        {        MainActivity.this.finish();

            Intent h = new Intent(getApplicationContext(),PatientProfActivity.class);
            startActivity(h);
        }

        final SharedPreferences d_session = getSharedPreferences("d", 0);
        SharedPreferences.Editor eeditor = d_session.edit();

        //email= p_session.getString("email","").toString();
        if(! d_session.getString("email","").equals(""))
        {   MainActivity.this.finish();
            Intent h = new Intent(getApplicationContext(),DocPersonalActivity.class);
            startActivity(h);
        }


        addListenerOnButton();
        addListenerOnSpinnerItemSelection();

        register = (TextView) findViewById(R.id.register);
        login = (Button) findViewById(R.id.login);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent r = new Intent(getApplicationContext(),RegistrationActivity.class);
                startActivity(r);
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent r = new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(r);
            }
        });




    }


    public void addListenerOnSpinnerItemSelection() {
        spinner1 = (Spinner) findViewById(R.id.spinner1);
        spinner1.setOnItemSelectedListener(new CustomOnItemSelectedListener());
    }

    public void addListenerOnButton() {

        spinner1 = (Spinner) findViewById(R.id.spinner1);
        btnSubmit = (Button) findViewById(R.id.btnSubmit);



        btnSubmit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if(spinner1.getSelectedItem().equals("بالتخصص")){
                Intent goTo = new Intent(getApplicationContext(), SpecialityActivity.class);
                startActivity(goTo);
                }
                else if(spinner1.getSelectedItem().equals("مناسب لآعراضك")){
                    Intent gooo = new Intent(getApplicationContext(),SymActivity.class);
                  startActivity(gooo);
                }
                else if(spinner1.getSelectedItem().equals("بالآسم")){
                    Intent gooo = new Intent(getApplicationContext(),DocNameActivity.class);
                    startActivity(gooo);
                }
                else if(spinner1.getSelectedItem().equals("بالعنوان")){
                    Intent gooo = new Intent(getApplicationContext(),LocationActivity.class);
                    startActivity(gooo);
                }
            }
}
    );}
}
