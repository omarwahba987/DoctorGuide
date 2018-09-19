package com.example.mahfouz.doctorgp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SymActivity extends AppCompatActivity {

    HashMap<String, List<String>> major_sym;
    List<String> major_sym_list;
    ExpandableListView Exp_list;
    SymAdapter adapter;
    Button search,reset,contact,about;
   static ArrayList<String> ser_sym ;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sym);


        search = (Button) findViewById(R.id.search_sym_btn);
        reset = (Button) findViewById(R.id.rsest_sym_btn);

        contact = (Button) findViewById(R.id.contactsym);
        about = (Button) findViewById(R.id.aboutsym);
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

        Exp_list = (ExpandableListView) findViewById(R.id.sympList);
        major_sym = SymDataProvider.getInfo(SymActivity.this);
        major_sym_list = new ArrayList<String>(major_sym.keySet());
        adapter = new SymAdapter(this, major_sym, major_sym_list);
        Exp_list.setAdapter(adapter);



        final String[] selected = {""};

        Exp_list.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {

                String y = major_sym.get(major_sym_list.get(groupPosition)).get(childPosition);
                Toast.makeText(SymActivity.this,  " تم اضافة " + y  , Toast.LENGTH_SHORT).show();
                if(y.contains(" "))
                {
                    y=y.replace(" ","_");
                }
                if(!selected[0].contains(y)) {


                    if(!selected[0].equals("")) {
                        selected[0] = selected[0] + "," + y;
                    }

                    else if (selected[0].equals(""))
                    {
                        selected[0] = selected[0] + y;
                    }
                    }
                return true;
            }
        });


        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent go = new Intent(getApplicationContext(),SearchListDocActivity.class);
                go.putExtra("symptoms", (String) selected[0]);
                go.putExtra("smethod","symptoms");
                startActivity(go);

            }
        });

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selected[0] = "";
                Toast.makeText(SymActivity.this, "قائمة الآعراض خالية الآن ! ", Toast.LENGTH_SHORT).show();
            }

        });

    }

}
