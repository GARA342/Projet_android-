package com.example.l3mig2024;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class Oject_class implements Parcelable {
    public String Arr;

    public Oject_class(String arr){
        this.Arr = arr;
    }
    protected Oject_class(Parcel in) {
        Arr = in.readString();
    }

    public static final Creator<Oject_class> CREATOR = new Creator<Oject_class>() {
        @Override
        public Oject_class createFromParcel(Parcel in) {
            return new Oject_class(in);
        }

        @Override
        public Oject_class[] newArray(int size) {
            return new Oject_class[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeString(Arr);

    }
}
