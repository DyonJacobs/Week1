package com.example.higherlower;

import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private Random random = new Random();
    private ImageView mImageDice;
    private ListView mThrowListView;
    private List<Throw> throwList;
    private ArrayAdapter throwListAdapter;

    private FloatingActionButton mHigher;
    private FloatingActionButton mLower;

    private TextView scoreView;
    private TextView highscoreView;

    private int prevNumber = 5;
    private int currNumber;
    private int currScore;
    private int highscore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /**
               Binding everything.
         */
        scoreView = findViewById(R.id.scoreText);
        highscoreView = findViewById(R.id.highscoreText);

        mImageDice = findViewById(R.id.diceImageView);
        mThrowListView = findViewById(R.id.throwListView);

        mHigher = findViewById(R.id.higherButton);
        mLower = findViewById(R.id.lowerButton);

        /**
               Make in adapter in MainActivity
         */
        throwList = new ArrayList<>();
        throwListAdapter = new ArrayAdapter(this, R.layout.throwlist, R.id.throwlist, throwList);
        mThrowListView.setAdapter(throwListAdapter);

        /**
         *      This is the "Onclick" that is used for the HigherButton.
         */

        mHigher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //chooses a random number from the method "roll"
                roll();
                //checks the score and in this check the true stands for higher.
                checkScore(v, true);
            }
        });

        /**
         *      This is the "Onclick" that is used for the LowerButton.
         */

        mLower.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //chooses a random number from the method "roll"
                roll();
                //checks the score and in this check the true stands for lower.
                checkScore(v, false);
            }
        });


    }

    /**
     Deze methode regelt een random getal tussen de 1 en 6
    Gegenereerde nummers worden in de lijst gezet en elk nummer genereerd een nieuwe image
     */

    private void roll() {
        Throw diceNumber = new Throw();
        diceNumber.setThrowNumber(random.nextInt(6) + 1);
        currNumber = diceNumber.getThrowNumber();

        throwList.add(diceNumber);
        throwListAdapter.notifyDataSetChanged();

        switch (diceNumber.getThrowNumber()) {
            case 1:
                mImageDice.setImageResource(R.drawable.d1);
                break;
            case 2:
                mImageDice.setImageResource(R.drawable.d2);
                break;
            case 3:
                mImageDice.setImageResource(R.drawable.d3);
                break;
            case 4:
                mImageDice.setImageResource(R.drawable.d4);
                break;
            case 5:
                mImageDice.setImageResource(R.drawable.d5);
                break;
            case 6:
                mImageDice.setImageResource(R.drawable.d6);
                break;
        }
    }

    /**
     Deze methode bekijkt de score en bepaald of de gebruiker gewonnen of verloren heeft.
     */
    private void checkScore(View v, boolean choice) {

        if ((choice && currNumber > prevNumber) || ((!choice) && currNumber < prevNumber)) {
            currScore++;

            if (currScore > highscore) {
                highscore = currScore;
                highscoreView.setText("Highscore: " + highscore);
            }

            Snackbar.make(v, "You Win!", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();

        } else if (currNumber == prevNumber) {
            Snackbar.make(v, "It's a Draw", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        } else {
            currScore = 0;
            Snackbar.make(v, "You Lose!", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        }

        prevNumber = currNumber;
        scoreView.setText(getString(R.string.score) + " " + currScore);
    }
}
