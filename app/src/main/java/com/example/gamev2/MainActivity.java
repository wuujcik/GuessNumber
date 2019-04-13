package com.example.gamev2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.util.Log;
import android.widget.TextView;

import com.example.gamev2.R;

import java.util.Random;


public class MainActivity extends AppCompatActivity {

    static final int CORRECT = 0;
    static final int LOW = 1;
    static final int HIGH = 2;
    static final int RESET = 3;

    EditText textGuess;
    Button   buttonGra;
    Button   buttonReset;

    static int correct;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textGuess    = findViewById(R.id.text_guess);
        buttonGra    = findViewById(R.id.button_gra);
        buttonReset  = findViewById(R.id.button_reset);

        Random generator = new Random();
        correct = generator.nextInt(100);

        buttonGra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int guess = Integer.parseInt(textGuess.getText().toString());
                //read what user provided and put it to checkGuess
                int result = checkGuess(guess);
                //checkGuess returns a result of the check which indicates then what updateUI should do
                updateUI(result);
            }
        });

        buttonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Random generator = new Random();
                correct = generator.nextInt(100);
                textGuess.getText().clear();

                TextView textFeedback;
                textFeedback = findViewById(R.id.text_feedback);
                TextView textNew;
                textNew = findViewById(R.id.text_new);

                textFeedback.setText("");
                textNew.setText("");
            }

        });


    }


    public static int checkGuess(int guess){
        if(guess == correct){
            return 0;
        } else if(guess < correct){
            return 1;
        } else {
            return 2;
        }
    }
    public void updateUI(int result){
        TextView textFeedback;
        textFeedback = findViewById(R.id.text_feedback);
        TextView textNew;
        textNew = findViewById(R.id.text_new);
        switch (result){
            case CORRECT: textFeedback.setText("correct!");
            textNew.setText("Click reset for a new game");

                break;
            case LOW: textFeedback.setText("too low");
                break;
            case HIGH: textFeedback.setText("too high");
                break;
            case RESET: textFeedback.setText("again?");
                break;
            default: textFeedback.setText("error");
                break;
        }
    }


}
