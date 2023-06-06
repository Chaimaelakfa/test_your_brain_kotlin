package com.example.test_your_brain_personnel

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import java.util.Random

class GameScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_screen)
        //random operations call
        randomOperation()

        //lisning to buttons
        setupNumberButtonListeners()


    }

    @SuppressLint("MissingInflatedId")
    private fun setupNumberButtonListeners() {
        var score =0
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
        //the AC button Listener
        val ac = findViewById<Button>(R.id.AC)
        ac.setOnClickListener {
            clearAnswer()
        }
        //the del button Listener
        val del = findViewById<Button>(R.id.DEL)
        del.setOnClickListener {
            deleteLastNumber()
        }
        val moins = findViewById<Button>(R.id.moins)
        moins.setOnClickListener {
            insertMinus()
        }
        val equal = findViewById<Button>(R.id.equal)
        equal.setOnClickListener {
            // Get the entered answer from the Answer TextView
            val enteredAnswerTextView = findViewById<TextView>(R.id.Answer)
            val enteredAnswer = enteredAnswerTextView.text.toString()

            // Get the randomly generated numbers and operation
            val randNum1TextView = findViewById<TextView>(R.id.randNum1)
            val randNum2TextView = findViewById<TextView>(R.id.randNum2)
            val operationTextView = findViewById<TextView>(R.id.Operation)

            val randNum1 = randNum1TextView.text.toString().toInt()
            val randNum2 = randNum2TextView.text.toString().toInt()
            val operation = operationTextView.text.toString()

            // Check the result
            val userAnswerInt = enteredAnswer.toIntOrNull() ?: 0
            val correctAnswer = when (operation) {
                "+" -> randNum1 + randNum2
                "-" -> randNum1 - randNum2
                "*" -> randNum1 * randNum2
                else -> 0
            }

            if (userAnswerInt == correctAnswer) {
                score +=1
                // User is correct
                val alertDialogBuilder = AlertDialog.Builder(this)
                val dialogView = layoutInflater.inflate(R.layout.correct_answer, null)
                alertDialogBuilder.setView(dialogView)
                val alertDialog = alertDialogBuilder.create()
                val nextButton = dialogView.findViewById<ImageView>(R.id.nextButton)
                nextButton.setOnClickListener {
                    // Move to next operation immediately
                    nextOperation()
                    alertDialog.dismiss()
                }
                alertDialog.show()


            } else {
                // If the answer is incorrect, keep the current operation and reset the user's answer

                // Show a dialog with "Wrong answer" message
                val alertDialogBuilder = AlertDialog.Builder(this)
                val dialogView = layoutInflater.inflate(R.layout.wrong_answer, null)
                alertDialogBuilder.setView(dialogView)
                val alertDialog = alertDialogBuilder.create()
                val nextButton = dialogView.findViewById<ImageView>(R.id.nextButton)
                nextButton.setOnClickListener {
                    // Move to next operation immediately
                    nextOperation()
                    alertDialog.dismiss()
                }
                // Move to next operation immediately
                alertDialog.show()
            }
            saveScore(score) // Save the score
        }

        }


    var i=0
    private fun nextOperation() {
        // Reset operation and userAnswer
        randomOperation()
        val userAnswerTextView = findViewById<TextView>(R.id.Answer)
        userAnswerTextView.text = ""
        i++
        condition()
    }

    fun condition(){
        if(i==4){ val intent = Intent(this@GameScreen, FinalScreen::class.java)
            startActivity(intent)}
    }
    private fun saveScore(score: Any) {

    }



    //minus button
    private fun insertMinus() {
        val answerTextView = findViewById<TextView>(R.id.Answer)
        val currentAnswer = answerTextView.text.toString()

        if (currentAnswer == "-") {
        }else {
            val newAnswer = "-$currentAnswer"
            answerTextView.text = newAnswer
        }
    }

    //del button function
    private fun deleteLastNumber() {
        val answerTextView = findViewById<TextView>(R.id.Answer)
        val currentAnswer = answerTextView.text.toString()
        if (currentAnswer.isNotEmpty()) {
            val newAnswer = currentAnswer.substring(0, currentAnswer.length - 1)
            answerTextView.text = newAnswer
        }
    }

    //the AC Button
    private fun clearAnswer() {
        val answerTextView = findViewById<TextView>(R.id.Answer)
        answerTextView.text = ""  // Set the text to an empty string
    }

    private fun appendNumberToAnswer(number: String) {
        val answerTextView = findViewById<TextView>(R.id.Answer)
        val currentAnswer = answerTextView.text.toString()
        val newAnswer = "$currentAnswer$number"
        answerTextView.text = newAnswer
    }



    //random operations
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