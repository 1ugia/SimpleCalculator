package com.practice.secondcalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private var textViewCalcScreen : TextView? = null //bringing TextView properties from xml file

    var lastNumeric :  Boolean = false //for onDecimalPoint function: to check that
    var lastDot : Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main) //initiating content of the view from xml

        textViewCalcScreen = findViewById(R.id.textViewCalcScreen)
        // initiating so properties are accessible
    }


    fun onDigit(view: View){
//        Toast.makeText(this, "Button Clicked", Toast.LENGTH_LONG).show()
        textViewCalcScreen?.append((view as Button).text)
        lastNumeric = true
        lastDot = false
    }

   fun onClear(view: View){
       textViewCalcScreen?.text = ""
   }

    fun onDecimalPoint(view: View){
        if(lastNumeric && !lastDot){
            textViewCalcScreen?.append(".")
            lastNumeric = false
            lastDot = true // "flag" or a check point to say where/when the changes are made
        }
    }
}