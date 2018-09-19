package com.example.mahfouz.doctorgp;

/**
 * Created by MAHFOUZ on 27/3/2018.
 */

public class Symptom {

    private int Symptom_ID;
    private String Symptom_Name;
    private String Specialist;

    public Symptom(int symptom_ID, String symptom_Name, String specialist) {
        Symptom_ID = symptom_ID;
        Symptom_Name = symptom_Name;
        Specialist = specialist;
    }

    public int getSymptom_ID() {
        return Symptom_ID;
    }

    public void setSymptom_ID(int symptom_ID) {
        Symptom_ID = symptom_ID;
    }

    public String getSymptom_Name() {
        return Symptom_Name;
    }

    public void setSymptom_Name(String symptom_Name) {
        Symptom_Name = symptom_Name;
    }

    public String getSpecialist() {
        return Specialist;
    }

    public void setSpecialist(String specialist) {
        Specialist = specialist;
    }
}
