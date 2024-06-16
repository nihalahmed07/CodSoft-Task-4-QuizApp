package com.alphax.quizapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.TextView
import java.io.InputStreamReader

class QuizQuestionsActivity : AppCompatActivity() {
    private var currentQuestionIndex = 0
    private var userScore = 0
    private lateinit var quiz: Quiz

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_questions)

        quiz = intent.getParcelableExtra("quiz")!!

        val questionText = findViewById<TextView>(R.id.questionText)
        val optionButtons = listOf<Button>(
            findViewById(R.id.option1Button),
            findViewById(R.id.option2Button),
            findViewById(R.id.option3Button),
            findViewById(R.id.option4Button)
        )

        displayQuestion(quiz.questions[currentQuestionIndex])

        optionButtons.forEachIndexed { index, button ->
            button.setOnClickListener {
                checkAnswer(index, quiz.questions[currentQuestionIndex])
            }
        }
    }

    private fun displayQuestion(question: Question) {
        val questionText = findViewById<TextView>(R.id.questionText)
        questionText.text = question.text

        val optionButtons = listOf<Button>(
            findViewById(R.id.option1Button),
            findViewById(R.id.option2Button),
            findViewById(R.id.option3Button),
            findViewById(R.id.option4Button)
        )

        // Set text for option buttons
        for (i in 0 until optionButtons.size) {
            optionButtons[i].text = question.options[i]
        }
    }

    private fun checkAnswer(selectedOptionIndex: Int, question: Question) {
        if (selectedOptionIndex == question.correctAnswer) {
            userScore++
        }

        currentQuestionIndex++
        if (currentQuestionIndex < quiz.questions.size) {
            displayQuestion(quiz.questions[currentQuestionIndex])
        } else {
            showResult()
        }
    }

    private fun showResult() {
        val i=Intent(this,Result::class.java)
        val scoreText = "Your Score: $userScore / ${quiz.questions.size}"
        Result.score=scoreText
        startActivity(i)
    }


}
