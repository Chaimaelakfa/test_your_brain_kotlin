package com.example.test_your_brain_personnel

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import java.util.Random

class GameScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_screen)


        randomOperation()

        val equal = findViewById<Button>(R.id.equal)
        equal.setOnClickListener {
            // Start the Start_screen activity
            val intent = Intent(this@GameScreen, FinalScreen::class.java)
            startActivity(intent)
        }
// Inside your activity or fragment

    }
    var i=0
    fun condition(){
        if(i==4){ val intent = Intent(this@GameScreen, FinalScreen::class.java)
            startActivity(intent)}
    }
    fun randomOperation(){
        val randNum1: TextView = findViewById(R.id.randNum1)
        val randNum2: TextView = findViewById(R.id.randNum2)
        val operation: TextView = findViewById(R.id.Operation)

        val random = Random()
        val num1 = random.nextInt(20) + 1 // Generates a random number between 1 and 20
        val num2 = random.nextInt(20) + 1 // Generates a random number between 1 and 20

        val operationCode = random.nextInt(3) // Generates a random number between 0 and 2
        var operationSymbol = ""

        when (operationCode) {
            0 -> operationSymbol = "+"
            1 -> operationSymbol = "-"
            2 -> operationSymbol = "*"
        }

        randNum1.text = num1.toString()
        randNum2.text = num2.toString()
        operation.text = operationSymbol

        condition()
    }
}