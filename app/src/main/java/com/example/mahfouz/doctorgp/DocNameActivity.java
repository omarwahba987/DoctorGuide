package com.example.mahfouz.doctorgp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class DocNameActivity extends AppCompatActivity {

    EditText docName;
    Button search,contact,about;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doc_name);

        docName = (EditText) findViewById(R.id.dname);
        search = (Button) findViewById(R.id.yy);

        contact = (Button) findViewById(R.id.contactdname);
        about = (Button) findViewById(R.id.aboutdname);

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
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent go = new Intent(getApplicationContext(),SearchListDocActivity.class);
                go.putExtra("docname", docName.getText().toString().trim());
                go.putExtra("smethod","docname");
                startActivity(go);
            }
        });

    }
}
