package com.example.l3mig2024;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

        //Login et Mot de passe de connexion.
        String Login ="christophe";
        String password ="BZFTELUX234";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
   

        Button bt1 = (Button)findViewById(R.id.valider);
        bt1.setOnClickListener(this);
        Button bt2 = (Button)findViewById(R.id.supprimer);
        bt2.setOnClickListener(this);

    }

    public void onStop(){
        super.onStop();
        Log.i("MYAPP", "Application stoppé");
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.valider){
            //Log.v("MYAPP", "clic sur le bouton ANNULER");
            EditText textLog = (EditText)findViewById(R.id.logintext);
            EditText TextPass = (EditText)findViewById(R.id.pass);


              String enteredLogin = textLog.getText().toString();
            String enteredPassword = TextPass.getText().toString();

            if (enteredLogin.equals(Login) && enteredPassword.equals(password)) {
                Intent inte =new Intent(this, MainActivity2.class);
                inte.putExtra("Nom", textLog.getText().toString());
                inte.putExtra("prenom",  TextPass.getText().toString());
                startActivity(inte);
            }else {
                //Affichage du message si le mot de passe et login sont incorrecte
                Toast.makeText(this, "Login ou mot de passe incorrect", Toast.LENGTH_SHORT).show();
            }

        }
        if(v.getId() == R.id.supprimer){
            EditText login = (EditText)findViewById(R.id.pass);
            Intent intt = new Intent(this, Liste.class);
            intt.putExtra("Pass", login.getText().toString());
            startActivity(intt);
        }

    


    }
}