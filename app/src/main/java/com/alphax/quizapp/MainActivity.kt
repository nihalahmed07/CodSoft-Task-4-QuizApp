package com.alphax.quizapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import java.io.InputStreamReader
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class MainActivity : AppCompatActivity() {
    private lateinit var quizListView: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        quizListView = findViewById(R.id.quizListView)

        // Load quizzes from the JSON file
        val quizzes: List<Quiz> = loadQuizzesFromJson("quiz_data.json")

        // Create an adapter to display quiz names
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, quizzes.map { it.title })
        quizListView.adapter = adapter

        // Set item click listener to start selected quiz
        quizListView.setOnItemClickListener { _, _, position, _ ->
            val selectedQuiz = quizzes[position]
            startQuiz(selectedQuiz)
        }
    }

    private fun startQuiz(quiz: Quiz) {
        val intent = Intent(this, QuizQuestionsActivity::class.java)
        intent.putExtra("quiz", quiz)
        startActivity(intent)
    }

    private fun loadQuizzesFromJson(fileName: String): List<Quiz> {
        val inputStream = assets.open(fileName)
        val reader = InputStreamReader(inputStream)
        val gson = Gson()

        val quizListType = object : TypeToken<List<Quiz>>() {}.type
        return gson.fromJson(reader, quizListType)
    }
}
