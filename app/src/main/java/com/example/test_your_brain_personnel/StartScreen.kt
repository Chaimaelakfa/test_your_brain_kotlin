package com.example.test_your_brain_personnel

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class StartScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start_screen)

        //entring nom
        val nom = findViewById<EditText>(R.id.nom)

        //button start
        val getStartedButton = findViewById<Button>(R.id.getStartedButton)
        getStartedButton.setOnClickListener {
            val TxtNom = nom.text.toString()
            // Start the Start_screen activity
            val intent = Intent(this@StartScreen, GameScreen::class.java)
            intent.putExtra("nom", TxtNom)
            startActivity(intent)
        }
    }
}