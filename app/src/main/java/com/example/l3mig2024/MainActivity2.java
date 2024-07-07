package com.example.l3mig2024;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity2 extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener , AdapterView.OnItemLongClickListener {

    ArrayList<String> data1 = new ArrayList<>();
    ArrayAdapter<String> adapter;
    EditText inputText;
    String name_entolog;
    boolean test_connect;
    String test;

    private String et_forme_name;
    private  String sect_formul;
    private int  num_formu;
    private String prenom_formul;
    private String mail_formul;

    Map<String, formulaire> saveData = new  HashMap<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {



        test_connect= chargerListe();




        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main2);

         Button btR = (Button)findViewById(R.id.retour);
         btR.setOnClickListener(this);

         Button btr1=(Button)findViewById(R.id.id_nomEntreprise);
         btr1.setOnClickListener(this);




        String str = getIntent().getStringExtra("Nom");
        String str_prenom = getIntent().getStringExtra("prenom");
        TextView prenomUser=(TextView)findViewById(R.id.prenomUser);
        prenomUser.setText(str_prenom);

        TextView nomUser = (TextView)findViewById(R.id.Bonjour1);
        nomUser.setText(str);


        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, data1);

        // Création de la listeView
        ListView liste = findViewById(R.id.maliste);
        liste.setAdapter(adapter);

        liste.setOnItemClickListener(this);
        liste.setOnItemLongClickListener(this);

        inputText =findViewById(R.id.text_entreprise);



        SearchView searchView = findViewById(R.id.searchView1);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.retour){
            Intent in =new Intent(this, MainActivity.class);
            startActivity(in);
            sauvegarder_entreprise();

        }

        if(v.getId()== R.id.id_nomEntreprise){
           // EditText vl_newName = (EditText)findViewById(R.id.id_nomEntreprise);
            //data1.remove(0);
             test = inputText.getText().toString();

            if(!test.isEmpty()){
                
                data1.add(test);
                adapter.notifyDataSetChanged();
                inputText.setText("");
                sauvegarder_entreprise();
            }
        }

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Log.v("TEST", "Vous avez cliqué sur l’élément"+position+"de la liste");

        String select = data1.get(position);

        Oject_class idListe = new Oject_class(select);
        Intent intent = new  Intent(this, Liste.class);

        intent.putExtra("Obt", idListe);
        intent.putExtra("position", position);

        if(saveData.containsKey(select)){
            formulaire forme = saveData.get(select);
            intent.putExtra("forma", forme);
        }
        startActivity(intent);


    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        String select =data1.get(position);
        Alter_info(select, position);
        return false;
    }


    public boolean sauvegarder_entreprise(){

        boolean r = true;
       // data1.clear();
        try {
            FileOutputStream fout = openFileOutput("entreprises.csv", Context.MODE_PRIVATE);
            OutputStreamWriter out = new OutputStreamWriter(fout);
            for(String t : data1){
                out.write(t+"\n");
            }
            out.close();
        }
        catch (Exception e) {
            r = false;
        }
        return r;


    }


    //Supprimer un élément de la liste
    public void Alter_info( String item,  int position){
        AlertDialog.Builder bi = new AlertDialog.Builder(this);
        bi.setMessage("Voulez-vous supprimer cet élément : " + item + " ?");

        bi.setPositiveButton("oui", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                data1.remove(position);
                adapter.notifyDataSetChanged();
                sauvegarder_entreprise();
                Toast.makeText(getApplicationContext(), "Élément supprimé", Toast.LENGTH_SHORT).show();

            }
        });
        bi.setNegativeButton("Non", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog alert =bi.create();
        alert.show();


    }


    public boolean chargerListe(){
        boolean r = true;



        try {
            FileInputStream fin = openFileInput("entreprises.csv");
            InputStreamReader in = new InputStreamReader(fin);
            BufferedReader buffer = new BufferedReader(in);
            String ligne = buffer.readLine();
            while(ligne != null){
                data1.add(ligne);
                ligne = buffer.readLine();
            }
            in.close();
        }
        catch (Exception e) {
            r = false;
        }
        return r;
    }





}