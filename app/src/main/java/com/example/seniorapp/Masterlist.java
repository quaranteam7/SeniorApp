package com.example.seniorapp;

public class Masterlist {

    private String Fullname ,Birthday, Gender, Municipality, Barangay, Zone ;

    public Masterlist(String fullname, String birthday, String gender, String municipality, String barangay, String zone) {
        Fullname = fullname;
        Birthday = birthday;
        Gender = gender;
        Municipality = municipality;
        Barangay = barangay;
        Zone = zone;
    }

    public String getFullname() {
        return Fullname;
    }

    public void setFullname(String fullname) {
        Fullname = fullname;
    }

    public String getBirthday() {
        return Birthday;
    }

    public void setBirthday(String birthday) {
        Birthday = birthday;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    public String getMunicipality() {
        return Municipality;
    }

    public void setMunicipality(String municipality) {
        Municipality = municipality;
    }

    public String getBarangay() {
        return Barangay;
    }

    public void setBarangay(String barangay) {
        Barangay = barangay;
    }

    public String getZone() {
        return Zone;
    }

    public void setZone(String zone) {
        Zone = zone;
    }

    public Masterlist() {
    }

    public String toString(){
        return this.Fullname + "." + Birthday;
    }
}
