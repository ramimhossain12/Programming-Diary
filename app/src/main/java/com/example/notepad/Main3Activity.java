package com.example.notepad;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Main3Activity extends AppCompatActivity implements View.OnClickListener {

    private Button sendButton,clearButton;
    private EditText nameeditText,massegeEditText;

    private RatingBar ratingBar ;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);


        sendButton = findViewById(R.id.sendButtonID);
        clearButton = findViewById(R.id.clearButtonID);


        nameeditText = findViewById(R.id.nameEditTextID);
        massegeEditText = findViewById(R.id.massegeEditTextID);

        ratingBar=findViewById(R.id.rattingbarID);
        textView= findViewById(R.id.textViewIDI);

        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                textView.setText("Value :"+ratingBar.getProgress());
            }
        });

        sendButton.setOnClickListener(this);
        clearButton.setOnClickListener(this);
    }



    @Override
    public void onClick(View view) {

        try {
            String name = nameeditText.getText().toString();
            String massege = massegeEditText.getText().toString();
            if (view.getId()==R.id.sendButtonID){

                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/email");

                intent.putExtra(Intent.EXTRA_EMAIL,new String[]{"ramimhossain6242@gmail.com"});

                intent.putExtra(Intent.EXTRA_SUBJECT,"Feedback from App");
                intent.putExtra(Intent.EXTRA_TEXT,"Name :"+name +"\n Message :"+massege);
                startActivity(Intent.createChooser(intent,"Feedback with"));


            }else if(view.getId()==R.id.clearButtonID){
                nameeditText.setText("");
                massegeEditText.setText("");
            }

        }catch (Exception e){

            Toast.makeText(getApplicationContext(),"Exception :"+e,Toast.LENGTH_SHORT).show();

        }


    }
}
