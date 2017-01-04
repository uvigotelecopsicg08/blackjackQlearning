package com.uvigo.uvigotelecopsicg08.blackjackqlearning;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.SyncFailedException;

public class setting_screen extends AppCompatActivity {
    Parameters p ;
    EditText rondas;
    CheckBox music,facil;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_screen);
        music =(CheckBox)findViewById(R.id.MusicBox);
        facil =(CheckBox) findViewById(R.id.FacilBox);
        rondas=(EditText) findViewById(R.id.editText);
        final String[] colores =
                new String[]{"Verde","Rojo","Negro","Amarillo","Blanco"};

        ArrayAdapter<String> adaptador =
                new ArrayAdapter<String>(this,
                        android.R.layout.simple_spinner_item, colores);
        Spinner spinner=(Spinner) findViewById(R.id.spinner2);
        spinner.setAdapter(adaptador);
        spinner.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    public void onItemSelected(AdapterView<?> parent,
                                               android.view.View v, int position, long id) {
                        p.setColor(""+parent.getItemAtPosition(position));
                        System.out.println(""+parent.getItemAtPosition(position));
                    }

                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
        try{
            Context context = getApplicationContext();
            ObjectInputStream ois = new ObjectInputStream(context.openFileInput("parameters.txt"));
             p =(Parameters) ois.readObject();
                music.setChecked(p.isMusic());
                facil.setChecked(p.isFacil());
            System.out.println("Ya hay parametros");


        }catch (IOException e){
            p =new Parameters();
            System.out.println("No hay parametros");

        } catch (ClassNotFoundException e) {
            p =new Parameters();
            System.out.println("No hay parametros");
        }
        String numRondas= "" + p.getNumRondas();
        rondas.setText(numRondas,TextView.BufferType.EDITABLE);
    }

    public void onClickApply(View view) {
        p.setNumRondas(Integer.parseInt(rondas.getText().toString()));

        Context context = getApplicationContext();
        try {
            ObjectOutputStream oos = new ObjectOutputStream(context.openFileOutput("parameters.txt", Context.MODE_PRIVATE)); //Select where you wish to save the file...
            oos.writeObject(p); // write the class as an 'object'
            oos.flush(); // flush the stream to insure all of the information was written to 'save_object.bin'
            oos.close();// close the stream
            Toast.makeText(getApplicationContext(), "Parametros guardados", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(setting_screen.this, home_screen.class);
            startActivity(intent);

        }

        catch (IOException e){
            e.printStackTrace();
        }

    }
    public void onClickResetSettings(View view) {
        p = new Parameters();
        rondas.setText("10",TextView.BufferType.EDITABLE);
        music.setChecked(false);
        facil.setChecked(false);
        Toast.makeText(getApplicationContext(), "Parámetros reseteados", Toast.LENGTH_LONG).show();


    }
    public void onClickResetScores(View view) {
        DataBaseManager dbM =new DataBaseManager(this);
        dbM.delete();
        dbM.close();
        Toast.makeText(getApplicationContext(), "Puntuaciones reseteadas", Toast.LENGTH_LONG).show();

    }
    public void onCheckboxMusic(View view) {
        p.setMusic(music.isChecked());
    }
    public void onCheckboxFacil(View view) {
        p.setFacil(facil.isChecked());
    }

}
