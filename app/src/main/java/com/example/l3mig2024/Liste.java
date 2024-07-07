package com.example.l3mig2024;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class Liste extends AppCompatActivity implements View.OnClickListener{
    String entre;


     EditText Two_name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       EdgeToEdge.enable(this);
        setContentView(R.layout.activity_liste);

        Button vr_enregistre =(Button)findViewById(R.id.Enregistrer);
        vr_enregistre.setOnClickListener(this);

        Button annule =(Button)findViewById(R.id.Annule);
        annule.setOnClickListener(this);

        Two_name = findViewById(R.id.nom_entreprise);

        Intent intt = getIntent();
        Oject_class sl_name_et = intt.getParcelableExtra("Obt");

        if(sl_name_et != null){
            Two_name.setText(sl_name_et.Arr);        }

    }

    @Override
    public void onClick(View v) {
        if(v.getId()== R.id.Enregistrer ){
            EditText vr_nameEntreprise = (EditText)findViewById(R.id.nom_entreprise);
            EditText vr_secteur = (EditText)findViewById(R.id.name_secteur);
            EditText vr_num = (EditText)findViewById(R.id.numero);
            EditText vr_contact = (EditText)findViewById(R.id.nm_contact);
            EditText vr_mail_client = (EditText)findViewById(R.id.mail);


            String entreprise = vr_nameEntreprise.getText().toString();
            String secteur = vr_secteur.getText().toString();

            int numero = Integer.parseInt(vr_num.getText().toString());
            String contact = vr_contact.getText().toString();

            String mail = vr_mail_client.getText().toString();

            formulaire vl_class_js = new formulaire(entreprise,secteur,numero,contact, mail);

            Intent intt = new Intent(this, MainActivity2.class);
            intt.putExtra("updatedEntreprise", entreprise);
            intt.putExtra("position", getIntent().getIntExtra("position", -1));

            startActivity(intt);

            boolean r ;

            try {
                FileOutputStream fout = openFileOutput("entreprises.csv", Context.MODE_APPEND);

                OutputStreamWriter out = new OutputStreamWriter(fout);
                out.write(vl_class_js+"\n");

                out.close();
            }
            catch (Exception e) {
                r = false;
            }
            startActivity(intt);
        }

        if(v.getId() == R.id.Annule){
            Intent intt = new Intent(this, MainActivity2.class);
            startActivity(intt);

        }

    }
}