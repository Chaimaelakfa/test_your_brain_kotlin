package com.example.test_your_brain_personnel

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import java.util.Random

class GameScreen : AppCompatActivity() {
    private var currentScore= 0
    private var i =1
    private var numberOp = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_screen)
        //random operations call
        randomOperation()
        //lisning to buttons
        tappedButton()

    }


    private fun tappedButton() {

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
                currentScore +=1
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
            saveScore(currentScore) // Save the score
        }

    }



    private fun nextOperation() {
        // Reset operation and userAnswer
        randomOperation()
        findViewById<TextView>(R.id.Answer).text = ""
        cond()
        i++

    }

    fun cond(){
        if(i==4){
            val intent = Intent(this@GameScreen, FinalScreen::class.java)
            intent.putExtra("currentScore", currentScore)
            startActivity(intent)}
    }
    private fun saveScore(score: Any) {
        //Création/Récupération du fichier XML pour shared pref
        val sharedPref = getSharedPreferences("fichierPreferencesScore", Context.MODE_PRIVATE)

        val bestScore = sharedPref.getInt("bestScore", 0)
        if (currentScore > bestScore) {
            //ecrire dans le fichier shared pref
            val editor = sharedPref.edit()
            editor.putInt("bestScore", currentScore)
            editor.apply()
        }
    }



    //minus button
    private fun insertMinus() {
        val userAnswer= findViewById<TextView>(R.id.Answer)
        val userAnswerText= userAnswer.text.toString()

        if (userAnswerText == "-") {
        }else {
            val newAnswer = "-$userAnswerText"
            userAnswer.text = newAnswer
        }
    }

    //del button function
    private fun deleteLastNumber() {
        val userAnswer= findViewById<TextView>(R.id.Answer)
        val currentAnswerText= userAnswer.text.toString()
        if (currentAnswerText.isNotEmpty()) {
            val newAnswer = currentAnswerText.substring(0, currentAnswerText.length - 1)
            userAnswer.text = newAnswer
        }
    }

    //the AC Button
    private fun clearAnswer() {
        val userAnswer = findViewById<TextView>(R.id.Answer)
        userAnswer.text = ""  // Set the text to an empty string
    }

    //add numbers next to eachother
    private fun appendNumberToAnswer(number: String) {
        val userAnswer = findViewById<TextView>(R.id.Answer)
        val currentAnswerText = userAnswer.text.toString()
        val newAnswer = "$currentAnswerText$number"
        userAnswer.text = newAnswer
    }



    //random operations
    private fun randomOperation(){
        val randNum1: TextView = findViewById(R.id.randNum1)
        val randNum2: TextView = findViewById(R.id.randNum2)
        val operation: TextView = findViewById(R.id.Operation)

        val num1 = Random().nextInt(20) + 1 // Generating the first number
        val num2 = Random().nextInt(20) + 1 // Generating the last number
        val operators= listOf<String>("+", "-", "*")
        val operatorIndex = Random().nextInt(operators.size)
        var operator = operators[operatorIndex]
        //generating the operation
        randNum1.text = num1.toString()
        randNum2.text = num2.toString()
        operation.text = operator

        numberOp++

        val numberOperation : TextView = findViewById(R.id.numOperation)
        numberOperation.text= "Operation Number ${numberOp}"
        cond()

    }
}