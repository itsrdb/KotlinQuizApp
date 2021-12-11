package com.itsrdb.quizapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnStart = findViewById<Button>(R.id.button_start)
        val nameBox = findViewById<TextView>(R.id.name_box)
        btnStart.setOnClickListener{
            if(nameBox.text.toString().isEmpty()){
                Toast.makeText(this, "Enter a valid name", Toast.LENGTH_SHORT).show()
            }else{
                val intent = Intent(this, QuizQuestionsActivity::class.java)
                intent.putExtra(Constants.USER_NAME, nameBox.text.toString())
                startActivity(intent)
                finish()
            }
        }
    }
}