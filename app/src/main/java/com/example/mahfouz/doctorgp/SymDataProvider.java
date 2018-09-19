package com.example.mahfouz.doctorgp;

import android.content.Context;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by MAHFOUZ on 10/4/2018.
 */

public class SymDataProvider{

    static ArrayList<String> ser_sym ;

    static public void setSer_sym(String e)
    {
        ser_sym.add(e);
    }

    static public ArrayList<String> selected ()
    {
        return ser_sym;
    }


    public static HashMap<String, List<String>> getInfo(final Context context)
    {


        final HashMap<String, List<String>> major_sym = new HashMap<String, List<String>>();


        ArrayList <String> major_list=new ArrayList <String>();
        major_list.add("الرأس");
        major_list.add("الرقبه");
        major_list.add("العين");
        major_list.add("أنف");
        major_list.add("الأذن");
        major_list.add("الفم");
        major_list.add("الصدر");
        major_list.add("البطن");
        major_list.add("اليدين");
        major_list.add("القدمين");
        major_list.add("الظهر");
        major_list.add("الحوض");
        major_list.add("الجلد");
        for (int i=0;i<major_list.size();i++)
        {   ArrayList <String> x=new ArrayList <String>();
            major_sym.put(major_list.get(i),x);
        }



        String URL="http://doctorguide.000webhostapp.com/API/getAllSymptoms";




        JsonArrayRequest x=new JsonArrayRequest(URL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject object = new JSONObject();
                for (int i = 0; i < response.length(); i++)
                    try {
                        object = response.getJSONObject(i);


                        if (object.getString("MainSymptoms").equals("الرأس")) {
                            major_sym.get("الرأس").add((String) object.getString("Symptom"));
                        } else if (object.getString("MainSymptoms").equals("الرقبه")) {
                            major_sym.get("الرقبه").add((String) object.getString("Symptom"));
                        } else if (object.getString("MainSymptoms").equals("العين")) {
                            major_sym.get("العين").add((String) object.getString("Symptom"));
                        }
                        else if (object.getString("MainSymptoms").equals("أنف")) {
                            major_sym.get("أنف").add((String) object.getString("Symptom"));
                        }
                        else if (object.getString("MainSymptoms").equals("الأذن")) {
                            major_sym.get("الأذن").add((String) object.getString("Symptom"));
                        }
                        else if (object.getString("MainSymptoms").equals("الفم")) {
                            major_sym.get("الفم").add((String) object.getString("Symptom"));
                        }

                        else if (object.getString("MainSymptoms").equals("الصدر")) {
                            major_sym.get("الصدر").add((String) object.getString("Symptom"));
                        }

                        else if (object.getString("MainSymptoms").equals("البطن")) {
                            major_sym.get("البطن").add((String) object.getString("Symptom"));
                        }

                        else if (object.getString("MainSymptoms").equals("اليدين")) {
                            major_sym.get("اليدين").add((String) object.getString("Symptom"));
                        }

                        else if (object.getString("MainSymptoms").equals("القدمين")) {
                            major_sym.get("القدمين").add((String) object.getString("Symptom"));
                        }

                        else if (object.getString("MainSymptoms").equals("الظهر")) {
                            major_sym.get("الظهر").add((String) object.getString("Symptom"));
                        }

                        else if (object.getString("MainSymptoms").equals("الحوض")) {
                            major_sym.get("الحوض").add((String) object.getString("Symptom"));
                        }

                        else if (object.getString("MainSymptoms").equals("الجلد")) {
                            major_sym.get("الجلد").add((String) object.getString("Symptom"));
                        }



                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }



    });
        Volley.newRequestQueue(context).add(x);
        return major_sym;
    }

}






