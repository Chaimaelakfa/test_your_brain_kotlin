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
        setupNumberButtonListeners()

// Inside your activity or fragment

    }

    private fun setupNumberButtonListeners() {
        val numberButtons = listOf<Button>(
            findViewById(R.id.num0),
            findViewById(R.id.num1),
            findViewById(R.id.num2),
            findViewById(R.id.num3),
            findViewById(R.id.num4),
            findViewById(R.id.num5),
            findViewById(R.id.num6),
            findViewById(R.id.num7),
            findViewById(R.id.num8),
            findViewById(R.id.num9)
        )
        for (button in numberButtons) {
            button.setOnClickListener {
                val enteredNumber = button.text.toString()
                appendNumberToAnswer(enteredNumber)
            }
        }
    }

    private fun appendNumberToAnswer(number: String) {
        val answerTextView = findViewById<TextView>(R.id.Answer)
        val currentAnswer = answerTextView.text.toString()
        val newAnswer = "$currentAnswer$number"
        answerTextView.text = newAnswer
    }

    var i=0
    fun condition(){
        if(i==4){ val intent = Intent(this@GameScreen, FinalScreen::class.java)
            startActivity(intent)}
    }
    //rqndom operations
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