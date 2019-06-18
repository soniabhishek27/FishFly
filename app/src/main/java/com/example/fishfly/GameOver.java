package com.example.fishfly;

import android.content.Intent;
import android.os.strictmode.IntentReceiverLeakedViolation;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class GameOver extends AppCompatActivity {

    private Button startagain;
   private String score;
   private TextView tscore;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);

       startagain=findViewById(R.id.b1);

       score= getIntent().getExtras().get("score").toString();

    tscore=findViewById(R.id.tscore);



    startagain.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent again = new Intent(GameOver.this, MainActivity.class);
            startActivity(again);

        }
    });

    tscore.setText(score);
    }

}
