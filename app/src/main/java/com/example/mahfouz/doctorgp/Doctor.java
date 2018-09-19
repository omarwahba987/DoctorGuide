package com.example.mahfouz.doctorgp;

/**
 * Created by MAHFOUZ on 13/2/2018.
 */

public class Doctor {

    private String DoctorId ;
    private String DoctorName;
    private String Email;
    private String Password;
    private String PhoneNumber;
    private String Gender;
    private String Age;
    private String Speciality;
    private String AcademicInfo;
    private String PhotoUrl;


    public String getRate() {
        return Rate;
    }

    public void setRate(String rate) {
        Rate = rate;
    }

    private String Rate;


    public Doctor() {
    }

    public Doctor(String doctorId, String doctorName, String email, String password, String phoneNumber, String gender, String age, String speciality, String academicInfo, String photoUrl , String Rate) {
        DoctorId = doctorId;
        DoctorName = doctorName;
        Email = email;
        Password = password;
        PhoneNumber = phoneNumber;
        Gender = gender;
        Age = age;
        Speciality = speciality;
        AcademicInfo = academicInfo;
        PhotoUrl = photoUrl;
        this.Rate = Rate;
    }

    public String getDoctorId() {
        return DoctorId;
    }

    public void setDoctorId(String doctorId) {
        DoctorId = doctorId;
    }

    public String getDoctorName() {
        return DoctorName;
    }

    public void setDoctorName(String doctorName) {
        DoctorName = doctorName;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        PhoneNumber = phoneNumber;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    public String getAge() {
        return Age;
    }

    public void setAge(String age) {
        Age = age;
    }

    public String getSpeciality() {
        return Speciality;
    }

    public void setSpeciality(String speciality) {
        Speciality = speciality;
    }

    public String getAcademicInfo() {
        return AcademicInfo;
    }

    public void setAcademicInfo(String academicInfo) {
        AcademicInfo = academicInfo;
    }

    public String getPhotoUrl() {
        return PhotoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        PhotoUrl = photoUrl;
    }
}
