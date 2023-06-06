package com.example.test_your_brain_personnel

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.content.Intent

class WelcomeScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome_screen)

        findViewById<Button>(R.id.nextButton).setOnClickListener {
            // Start the Start_screen activity
            val intent = Intent(this, StartScreen::class.java)
            startActivity(intent)
        }
    }
}