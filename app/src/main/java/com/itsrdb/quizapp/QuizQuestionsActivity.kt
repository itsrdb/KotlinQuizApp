package com.itsrdb.quizapp

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.core.content.ContextCompat

class QuizQuestionsActivity : AppCompatActivity(), View.OnClickListener {

    private var curr_pos: Int = 1
    private var questionsList:ArrayList<Question>? = null
    private var selectedOption: Int = 0
    private var countCorrect = 0
    private var userName: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_questions)

        userName = intent.getStringExtra(Constants.USER_NAME)
        questionsList = Constants.getQuestions()

        setQuestion()

        val o1 = findViewById<TextView>(R.id.option_1)
        val o2 = findViewById<TextView>(R.id.option_2)
        val o3 = findViewById<TextView>(R.id.option_3)
        val o4 = findViewById<TextView>(R.id.option_4)
        val submitBtn = findViewById<TextView>(R.id.submit_btn)

        o1.setOnClickListener(this)
        o2.setOnClickListener(this)
        o3.setOnClickListener(this)
        o4.setOnClickListener(this)
        submitBtn.setOnClickListener(this)
    }

    private fun setQuestion(){
        questionsList = Constants.getQuestions()
        val question = questionsList!![curr_pos-1]

        defaultOptionsView()

        val progressBar = findViewById<ProgressBar>(R.id.progress_bar)
        val progressText = findViewById<TextView>(R.id.progress_text)
        progressBar.setProgress(curr_pos)
        progressText.setText("$curr_pos"+"/"+progressBar.max)

        val tv_question = findViewById<TextView>(R.id.tv_question)
        tv_question.text = question!!.question

        val flag_image = findViewById<ImageView>(R.id.flag_image)
        flag_image.setImageResource(question.image)

        val o1 = findViewById<TextView>(R.id.option_1).setText(question.Option1)
        val o2 = findViewById<TextView>(R.id.option_2).setText(question.Option2)
        val o3 = findViewById<TextView>(R.id.option_3).setText(question.Option3)
        val o4 = findViewById<TextView>(R.id.option_4).setText(question.Option4)
    }

    private fun defaultOptionsView(){
        val question = questionsList!![curr_pos-1]
        val o1 = findViewById<TextView>(R.id.option_1)
        val o2 = findViewById<TextView>(R.id.option_2)
        val o3 = findViewById<TextView>(R.id.option_3)
        val o4 = findViewById<TextView>(R.id.option_4)
        o1.text = question.Option1
        o2.text = question.Option2
        o3.text = question.Option3
        o4.text = question.Option4

        val options = ArrayList<TextView>()
        options.add(0, o1)
        options.add(1, o2)
        options.add(2, o3)
        options.add(3, o4)

        for(option in options){
            option.setTextColor(Color.parseColor("#7A8089"))
            option.typeface = Typeface.DEFAULT
            option.background = ContextCompat.getDrawable(
                this,
                R.drawable.default_option
            )
        }
    }

    override fun onClick(v: View?) {
        val o1 = findViewById<TextView>(R.id.option_1)
        val o2 = findViewById<TextView>(R.id.option_2)
        val o3 = findViewById<TextView>(R.id.option_3)
        val o4 = findViewById<TextView>(R.id.option_4)
        val submitBtn = findViewById<TextView>(R.id.submit_btn)
        when(v?.id){
            R.id.option_1->{
                selectedOptionView(o1,1)
            }
            R.id.option_2->{
                selectedOptionView(o2,2)
            }
            R.id.option_3->{
                selectedOptionView(o3,3)
            }
            R.id.option_4->{
                selectedOptionView(o4,4)
            }
            R.id.submit_btn->{
                if(selectedOption == 0){
                    curr_pos++

                    when{
                        curr_pos <= questionsList!!.size->{
                            setQuestion()
                            submitBtn.text = "SUBMIT"
                        }else->{
                            Toast.makeText(this, "You have completed the quiz.", Toast.LENGTH_SHORT).show()
                            val intent = Intent(this, ResultActivity::class.java)
                            intent.putExtra(Constants.USER_NAME, userName)
                            intent.putExtra(Constants.TOTAL_CORRECT, countCorrect)
                            intent.putExtra(Constants.TOTAL_QUESTIONS, questionsList!!.size)
                            startActivity(intent)
                        }
                    }
                }else{
                    val question = questionsList?.get(curr_pos-1)
                    if(question!!.CorrectAns!=selectedOption){
                        answerView(selectedOption, R.drawable.red_option)
                    }else{
                        countCorrect++
                    }
                    answerView(question!!.CorrectAns, R.drawable.green_option)

                    if(curr_pos == questionsList!!.size){
                        submitBtn.text = "Finish"
                    }else{
                        submitBtn.text = "Go to next Question"
                    }
                    selectedOption = 0
                }
            }
        }
    }

    private fun answerView(answer: Int, drawableView: Int){
        val o1 = findViewById<TextView>(R.id.option_1)
        val o2 = findViewById<TextView>(R.id.option_2)
        val o3 = findViewById<TextView>(R.id.option_3)
        val o4 = findViewById<TextView>(R.id.option_4)
        when(answer){
            1->{
                o1.background = ContextCompat.getDrawable(
                    this, drawableView
                )
            }
            2->{
                o2.background = ContextCompat.getDrawable(
                    this, drawableView
                )
            }
            3->{
                o3.background = ContextCompat.getDrawable(
                    this, drawableView
                )
            }
            4->{
                o4.background = ContextCompat.getDrawable(
                    this, drawableView
                )
            }
        }
    }

    private fun selectedOptionView(tv: TextView, selectedOptionNum: Int){
        defaultOptionsView()
        selectedOption = selectedOptionNum

        val o1 = findViewById<TextView>(R.id.option_1)
        val o2 = findViewById<TextView>(R.id.option_2)
        val o3 = findViewById<TextView>(R.id.option_3)
        val o4 = findViewById<TextView>(R.id.option_4)

        val options = ArrayList<TextView>()
        options.add(0, o1)
        options.add(1, o2)
        options.add(2, o3)
        options.add(3, o4)

        tv.setTextColor(Color.parseColor("#363A43"))
        tv.setTypeface(tv.typeface, Typeface.BOLD)
        tv.background = ContextCompat.getDrawable(
            this,
            R.drawable.selected_option
        )
    }
}