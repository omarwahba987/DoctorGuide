package com.example.mahfouz.doctorgp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import static com.example.mahfouz.doctorgp.R.id.spinner2;

public class LocationActivity extends AppCompatActivity {

    private Spinner spinner3;
    private Button search,contact,about;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);

        contact = (Button) findViewById(R.id.contactlocation);
        about = (Button) findViewById(R.id.aboutlocation);

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

        addListenerOnButton();
        addListenerOnSpinnerItemSelection();
    }


    public void addListenerOnSpinnerItemSelection() {
        spinner3 = (Spinner) findViewById(R.id.spinner3);
        spinner3.setOnItemSelectedListener(new CustomOnItemSelectedListener());
    }

    public void addListenerOnButton() {

        spinner3 = (Spinner) findViewById(R.id.spinner3);
        search = (Button) findViewById(R.id.g);

        search.setOnClickListener(new View.OnClickListener() {


                                   @Override
                                   public void onClick(View v) {


                                       Intent t = new Intent(getApplicationContext(),SearchListDocActivity.class);
                                       t.putExtra("Location", (String) spinner3.getSelectedItem());
                                       t.putExtra("smethod","Location");
                                       startActivity(t);
                                   }
                               }
        );
    }
}
