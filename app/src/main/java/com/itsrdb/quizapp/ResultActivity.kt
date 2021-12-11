package com.itsrdb.quizapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class ResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        val userName = intent.getStringExtra(Constants.USER_NAME)
        val tvName = findViewById<TextView>(R.id.user_name)
        tvName.text = userName

        val totalQuestions = intent.getIntExtra(Constants.TOTAL_QUESTIONS, 0)
        val totalCorrect = intent.getIntExtra(Constants.TOTAL_CORRECT, 0)

        val resultTv = findViewById<TextView>(R.id.score_text)
        resultTv.text = "Your score is $totalCorrect out of $totalQuestions"

        val btnFinish = findViewById<Button>(R.id.end_btn)
        btnFinish.setOnClickListener{
            finish()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}