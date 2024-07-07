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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
     //   EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
      /*  ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });*/

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


            Intent inte =new Intent(this, MainActivity2.class);
            inte.putExtra("Nom", textLog.getText().toString());
            inte.putExtra("prenom",  TextPass.getText().toString());
            startActivity(inte);

        }
        if(v.getId() == R.id.supprimer){
            EditText login = (EditText)findViewById(R.id.pass);
            Intent intt = new Intent(this, Liste.class);
            intt.putExtra("Pass", login.getText().toString());
            startActivity(intt);
        }

    


    }
}