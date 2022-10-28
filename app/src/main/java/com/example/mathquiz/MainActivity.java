package com.example.mathquiz;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.constraintlayout.widget.Constraints;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    //for the random a and b
    int a,b;
    TextView questiontextview;
    TextView finaltextview;
    TextView resulttextview;
    TextView timetextview;
    Button button1;
    Button button2;
    Button button3;
    Button button4;
    Button button5;
    Button mainbutton;
    ConstraintLayout constraint;
    MediaPlayer media;
    int locationofcorrectanswer;
    int countcorrectanswer;
    int counttotalnoofquestion;

    public void go(View view){
        mainbutton.setVisibility(View.INVISIBLE);
        constraint.setVisibility(View.VISIBLE);
    }

    ArrayList<Integer> numbers = new ArrayList<Integer>();
    public void question(){
        numbers.clear();
        Random rand = new Random();
        a=rand.nextInt(20);
        b=rand.nextInt(20);
        questiontextview.setText( a + " + " + b);


        locationofcorrectanswer= rand.nextInt(4);
        for(int i=0;i<4;i++){
            if(i== locationofcorrectanswer){
                numbers.add(a+b);
            }
            else{
                int wronganswer=rand.nextInt(40);
                while(wronganswer== (a+b)){
                    wronganswer= rand.nextInt(40);
                }
                numbers.add(wronganswer);
            }
        }
        button1.setText(Integer.toString(numbers.get(0)));
        button2.setText(Integer.toString(numbers.get(1)));
        button3.setText(Integer.toString(numbers.get(2)));
        button4.setText(Integer.toString(numbers.get(3)));
    }

    public void clicked(View view){
        counttotalnoofquestion++;

        Log.i("hey",view.getTag().toString());
        Log.i("location",Integer.toString(locationofcorrectanswer));
        // if locationofcorrectanswer is not changed into string we wont get correct in text view as integer cannot equals the string
        if(view.getTag().toString().equals(Integer.toString(locationofcorrectanswer))){
            finaltextview.setText("Correct");
            countcorrectanswer++;
        }
        else{
            finaltextview.setText("Incorrect");
        }
        resulttextview.setText(counttotalnoofquestion+ " / "+ countcorrectanswer);
        // to create again new question
        question();
    }

    public void playAgain(View view){
        timetextview.setText("30s");
        resulttextview.setText("0/0");
        question();
        CountDownTimer count= new CountDownTimer(30100,1000) {
            @Override
            public void onTick(long l) {
                timetextview.setText(String.valueOf(l/1000));
            }

            @Override
            public void onFinish() {
                finaltextview.setText("Time is Over");
                button5.setVisibility(View.VISIBLE);
                media= MediaPlayer.create(MainActivity.this,R.raw.timeup);
                media.start();

            }
        };
        count.start();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        timetextview = (TextView) findViewById(R.id.timetextView);
        resulttextview=(TextView) findViewById(R.id.resulttextView);
        questiontextview=(TextView) findViewById(R.id.questiontextView);
        finaltextview =(TextView)findViewById(R.id.finaltextview);
        button1= (Button) findViewById(R.id.button1);
        button2= (Button) findViewById(R.id.button2);
        button3= (Button) findViewById(R.id.button3);
        button4= (Button) findViewById(R.id.button4);
        button5=(Button)findViewById(R.id.button5);
        mainbutton= (Button)findViewById(R.id.mainbutton);
        constraint=(ConstraintLayout)findViewById(R.id.gamelayout);
        timetextview.setText("30s");
        resulttextview.setText("0/0");
        questiontextview.setText("23+7");
        mainbutton.setVisibility(View.VISIBLE);
        constraint.setVisibility(View.INVISIBLE);
        playAgain( findViewById(R.id.timetextView));

    }
}