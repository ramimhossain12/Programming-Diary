package com.example.notepad;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.ZoomControls;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
   //    Button
    private EditText editText;
    private Button savebutton,exitButton;
    private android.app.AlertDialog.Builder alertDialogBuilder;



//on creat method
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


//find the button
        editText = findViewById(R.id.editTextID);
        savebutton = findViewById(R.id.saveButtonID);
        exitButton = findViewById(R.id.buttonID);
        exitButton.setOnClickListener(this);


//on clickListener
        savebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String text = editText.getText().toString();

                if(text!=null){


                    writeToFile(text);




                }else{
                    Toast.makeText(getApplicationContext(),"Please enter some data",Toast.LENGTH_SHORT).show();
                }
            }
        });

        readfFromFile();


    }



    //menu item find

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }


    //menu item selected
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();

        if (item.getItemId()==R.id.shareID){

            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("text/type");
            String subject = "Note_Book app";
            String body ="This app  is very useful .\n com.example.notepad";
            intent.putExtra(Intent.EXTRA_SUBJECT,subject);
            intent.putExtra(Intent.EXTRA_TEXT,body);
            startActivity(Intent.createChooser(intent,"share with"));


        }else if (item.getItemId()==R.id.feedbackID){
            Intent intent = new Intent(getApplicationContext(),Main3Activity.class);
            startActivity(intent);




        }else if (id == R.id.aboutId){
            Intent intent = new Intent(this,about.class);
            startActivity(intent);
            return true;
        }else if (id==R.id.dateId){
            Intent intent = new Intent(this,DatePicker.class);
            startActivity(intent);
        }


        return super.onOptionsItemSelected(item);
    }

    public  void writeToFile(String text){


        try {
            FileOutputStream fileOutputStream = openFileOutput("Note.text", Context.MODE_PRIVATE);
            try {
                fileOutputStream.write(text.getBytes());
                fileOutputStream.close();
                Toast.makeText(getApplicationContext(),"  Note saved ",Toast.LENGTH_SHORT).show();

            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


    }
    public  void readfFromFile(){

        try {
            FileInputStream fileInputStream = openFileInput("Note.text");

            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String line ;
            StringBuffer stringBuffer = new StringBuffer();
            while ((line = bufferedReader.readLine())!=null){

                stringBuffer.append(line+"\n");
            }

            editText.setText(stringBuffer.toString());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }


    //back button click
    @Override
    public void onBackPressed() {

        androidx.appcompat.app.AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are you sure you want Exit ?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {


                        MainActivity.super.onBackPressed();
                    }
                })

                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();


    }


    // alertdilog

    @Override
    public void onClick(View v) {

        alertDialogBuilder = new android.app.AlertDialog.Builder(MainActivity.this);
        alertDialogBuilder.setTitle(R.string.title_Text);

        alertDialogBuilder.setMessage(R.string.message_Text);

        alertDialogBuilder.setIcon(R.drawable.pic);

        alertDialogBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });

        alertDialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                dialog.cancel();

            }
        });


        alertDialogBuilder.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                Toast.makeText(MainActivity.this,"You have clicked on cancel button",Toast.LENGTH_SHORT).show();

            }
        });

        android.app.AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();

    }
}

