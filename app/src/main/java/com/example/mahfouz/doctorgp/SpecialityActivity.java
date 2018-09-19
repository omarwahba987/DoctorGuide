package com.example.mahfouz.doctorgp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import static com.example.mahfouz.doctorgp.R.id.btnSubmit;
import static com.example.mahfouz.doctorgp.R.id.spinner1;
import static com.example.mahfouz.doctorgp.R.id.spinner2;

public class SpecialityActivity extends AppCompatActivity {

    private Spinner spinner2;
    private Button goo,contact,about;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_speciality);

        addListenerOnButton();
        addListenerOnSpinnerItemSelection();

        contact = (Button) findViewById(R.id.contactspec);
        about = (Button) findViewById(R.id.aboutspec);
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

    }

    public void addListenerOnSpinnerItemSelection() {
        spinner2 = (Spinner) findViewById(R.id.spinner2);
        spinner2.setOnItemSelectedListener(new CustomOnItemSelectedListener());
    }

    public void addListenerOnButton() {

        spinner2 = (Spinner) findViewById(R.id.spinner2);
        goo = (Button) findViewById(R.id.yy);

        goo.setOnClickListener(new View.OnClickListener() {

           @Override
           public void onClick(View v) {



               Intent gooo = new Intent(getApplicationContext(),SearchListDocActivity.class);
               gooo.putExtra("Speciallity", (String) spinner2.getSelectedItem());
               gooo.putExtra("smethod","speciallity");
               startActivity(gooo);
           }
       }
        );
    }

}
