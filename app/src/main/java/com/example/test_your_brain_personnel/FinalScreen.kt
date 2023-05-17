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
    companion object {
        const val SHARED_PREFS_KEY = "score"
    }
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_final_screen)

        val score = intent.getIntExtra("score", 0)
        // Level
        val level: String = when {
            score <= 5 -> "Beginner"
            score <= 10 -> "Intermediate"
            score <= 15 -> "Advanced"
            else -> "Expert"
        }
        // Retrieve the old score from shared preferences
        val sharedPreferences: SharedPreferences = getSharedPreferences(FinalScreen.SHARED_PREFS_KEY, Context.MODE_PRIVATE)
        val oldScore = sharedPreferences.getInt(FinalScreen.SHARED_PREFS_KEY, 0)

        // Update UI elements
        val currentScoreTextView = findViewById<TextView>(R.id.currentScoreTextView)
        val bestScoreTextView = findViewById<TextView>(R.id.bestScoreText)
        val levelText = findViewById<TextView>(R.id.levelText)

        currentScoreTextView.text = "Your current score is $score/20"
        bestScoreTextView.text = "Your best score is $oldScore/20"
        levelText.text = "You are at the $level level"

        val playAgainButton = findViewById<Button>(R.id.playAgainButton)
        playAgainButton.setOnClickListener {
            // Start the game again
            val intent = Intent(this@FinalScreen, StartScreen::class.java)
            startActivity(intent)
        }
    }
}