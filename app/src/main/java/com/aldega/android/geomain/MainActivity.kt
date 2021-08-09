package com.aldega.android.geomain

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders

class MainActivity : AppCompatActivity() {

    private lateinit var trueButton: Button
    private lateinit var falseButton: Button
    private lateinit var nextButton: Button
    private lateinit var questionTextView: TextView
    private val TAG = "MainActivity"


    private val quizViewModel: QuizViewModel by lazy {
        ViewModelProviders.of(this).get(QuizViewModel::class.java)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate(Bundle?) called")
        setContentView(R.layout.activity_main)

        trueButton = findViewById(R.id.true_button)
        falseButton = findViewById(R.id.false_button)
        nextButton = findViewById(R.id.next_button)
        questionTextView = findViewById(R.id.question_text_view)

        showQuestion()

        trueButton.setOnClickListener {
            setAnswerButtonsClickable(false)
            showAnswer(true) }
        falseButton.setOnClickListener {
            setAnswerButtonsClickable(false)
            showAnswer(false) }

        nextButton.setOnClickListener {
            quizViewModel.moveToNext()
            showQuestion()
            setAnswerButtonsClickable(true)
        }


    }

    fun setAnswerButtonsClickable(isClickable: Boolean) {

        falseButton.isClickable = isClickable
        trueButton.isClickable = isClickable
    }



    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart() called")
    }
    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume() called")
    }
    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause() called")
    }
    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop() called")
    }
    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy() called")
    }


    fun showAnswer(answer: Boolean) {
        if (answer == quizViewModel.currentQuestionAnswer) {
            Toast.makeText(this, R.string.correct_toast, Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, R.string.incorrect_toast, Toast.LENGTH_SHORT).show()
        }
    }

    fun showQuestion() {
        val questionTextResId = quizViewModel.currentQuestionText
        questionTextView.setText(questionTextResId)
    }
}
