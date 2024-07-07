package com.example.l3mig2024;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class formulaire implements Parcelable {
    String Entreprise;
    String Secteur;
    int Numero;
    String Name;
    String Mail;





    public  formulaire(String entre, String sec, int num, String prenom, String ml_mail){
        this.Entreprise = entre;
        this.Secteur = sec;
        this.Numero = num;
        this.Name = prenom;
        this.Mail = ml_mail;
    }

    protected formulaire(Parcel in) {
        Entreprise= in.readString();
        Secteur= in.readString();
        Numero = in.readInt();
        Name= in.readString();
        Mail=in.readString();
    }

    public static final Creator<formulaire> CREATOR = new Creator<formulaire>() {
        @Override
        public formulaire createFromParcel(Parcel in) {
            return new formulaire(in);
        }

        @Override
        public formulaire[] newArray(int size) {
            return new formulaire[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeString(Entreprise);
        dest.writeString(Secteur);
        dest.writeInt(Numero);
        dest.writeString(Name);
        dest.writeString(Mail);

    }

    public String toString(){
        return this.Entreprise+","+ this.Numero+","+ this.Name+","+this.Numero+","+this.Secteur+";";
    }


}
