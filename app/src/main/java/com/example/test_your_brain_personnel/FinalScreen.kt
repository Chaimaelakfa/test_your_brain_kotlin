package com.example.test_your_brain_personnel

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class FinalScreen : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_final_screen)

        //current score
        val currentScore = intent.getIntExtra("currentScore", 0)
        findViewById<TextView>(R.id.currentScoreTextView).text = "Your current score is $currentScore/20"


        //best score using sharedpref
        val sharedPref = getSharedPreferences("fichierPreferencesScore", Context.MODE_PRIVATE)
        val bestScore = sharedPref.getInt("bestScore", currentScore)
        val bestScoreTextView = findViewById<TextView>(R.id.bestScoreText)
        bestScoreTextView.text = "Your best score is $bestScore/20"


        //level
        val levelText = findViewById<TextView>(R.id.levelText)
        val level: String = when {
            currentScore <= 5 -> "Beginner"
            currentScore <= 10 -> "Intermediate"
            currentScore <= 15 -> "Advanced"
            else -> "Expert"
        }
        levelText.text = "You are at the $level level"

        //button play Again
        val playAgainButton = findViewById<Button>(R.id.playAgainButton)
        playAgainButton.setOnClickListener {
            val str=""
            // Start the game again
            val userName = intent.getStringExtra("userName")
            val intent = Intent(this@FinalScreen, StartScreen::class.java)
            intent.putExtra("userNameFinal",userName)
            startActivity(intent)
        }
    }
}
