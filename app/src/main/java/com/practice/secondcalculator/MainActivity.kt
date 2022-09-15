package com.practice.secondcalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private var textViewCalcScreen : TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textViewCalcScreen = findViewById(R.id.textViewCalcScreen)
    }


    fun onDigit(view: View){
//        /Toast.makeText(this, "Button Clicked", Toast.LENGTH_LONG).show()
        textViewCalcScreen?.append((view as Button).text)
    }
}