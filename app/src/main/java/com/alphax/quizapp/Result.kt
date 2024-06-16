package com.alphax.quizapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView

class Result : AppCompatActivity() {
    companion object{
        lateinit var score:String
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)
        val resultText = findViewById<TextView>(R.id.results)
        resultText.text = score
    }

    fun back(v: View){
        val i= Intent(this,MainActivity::class.java)
        startActivity(i)
    }
}