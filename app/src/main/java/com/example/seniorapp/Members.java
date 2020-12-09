package com.example.seniorapp;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class Members implements Parcelable {

    private String Fullname, Birthday, Gender, Municipality, Barangay, Zone;
    private String mImageUrl;

    public Members(String fullname, String birthday, String gender, String municipality, String barangay, String zone, String imageUrl) {
        Fullname = fullname;
        Birthday = birthday;
        Gender = gender;
        Municipality = municipality;
        Barangay = barangay;
        Zone = zone;
        mImageUrl = imageUrl;
    }

    public Members() {
    }

    protected Members(Parcel in) {
        Fullname = in.readString();
        Birthday = in.readString();
        Gender = in.readString();
        Municipality = in.readString();
        Barangay = in.readString();
        Zone = in.readString();
        mImageUrl = in.readString();
    }

    public static final Creator<Members> CREATOR = new Creator<Members>() {
        @Override
        public Members createFromParcel(Parcel in) {
            return new Members(in);
        }

        @Override
        public Members[] newArray(int size) {
            return new Members[size];
        }
    };

    public String getmImageUrl() {
        return mImageUrl;
    }

    public void setmImageUrl(String imageUrl) {
        mImageUrl = imageUrl;
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

    public Members(String s, String toString, String string, String s1, String toString1, String string1) {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(Fullname);
        dest.writeString(Birthday);
        dest.writeString(Gender);
        dest.writeString(Municipality);
        dest.writeString(Barangay);
        dest.writeString(Zone);
        dest.writeString(mImageUrl);
    }
}
