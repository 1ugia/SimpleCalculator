package com.practice.secondcalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import java.lang.ArithmeticException

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

    fun onOperator(view: View){
        textViewCalcScreen?.text?.let {
            if (lastNumeric && !isOperatorAdded(it.toString())){
                textViewCalcScreen?.append((view as Button).text)
                lastNumeric = false
                lastDot = false
            }
        }
    }

    fun onEqual(view: View){
        if(lastNumeric){
            var calcScreenValue = textViewCalcScreen?.text.toString()

            var prefix = ""
            try{
                if(calcScreenValue.startsWith("-")){
                    prefix = "-"
                    calcScreenValue = calcScreenValue.substring(1)
                }

                // for minus calculations
                if (calcScreenValue.contains("-")){

                    val calcScreenValue = calcScreenValue.split("-")

                    var splitValueOne = calcScreenValue[0]
                    var splitValueTwo = calcScreenValue[1]

                    if(prefix.isNotEmpty()){
                        splitValueOne = prefix + splitValueOne
                    }

                    textViewCalcScreen?.text = removeZeroAfterDot(
                        (splitValueOne.toDouble() - splitValueTwo.toDouble()).toString()
                    )
                }

                // for plus calculations

                else if (calcScreenValue.contains("+")){

                    val calcScreenValue = calcScreenValue.split("+")

                    var splitValueOne = calcScreenValue[0]
                    var splitValueTwo = calcScreenValue[1]

                    if(prefix.isNotEmpty()){
                        splitValueOne = prefix + splitValueOne
                    }

                    textViewCalcScreen?.text = removeZeroAfterDot(
                        (splitValueOne.toDouble() + splitValueTwo.toDouble()).toString()
                    )
                }

                // for division calculations
                else if (calcScreenValue.contains("/")){

                    val calcScreenValue = calcScreenValue.split("/")

                    var splitValueOne = calcScreenValue[0]
                    var splitValueTwo = calcScreenValue[1]

                    if(prefix.isNotEmpty()){
                        splitValueOne = prefix + splitValueOne
                    }

                    textViewCalcScreen?.text = removeZeroAfterDot(
                        (splitValueOne.toDouble() / splitValueTwo.toDouble()).toString()
                    )
                }

                // for multiplication calculations
                else if (calcScreenValue.contains("*")){

                    val calcScreenValue = calcScreenValue.split("*")

                    var splitValueOne = calcScreenValue[0]
                    var splitValueTwo = calcScreenValue[1]

                    if(prefix.isNotEmpty()){
                        splitValueOne = prefix + splitValueOne
                    }

                    textViewCalcScreen?.text = removeZeroAfterDot(
                        (splitValueOne.toDouble() * splitValueTwo.toDouble()).toString()
                    )
                }



            }catch (e: ArithmeticException){
                e.printStackTrace()
            }
        }
    }

    private fun removeZeroAfterDot(result : String) :String {
        var value = result
        if (result.contains("0"))
            value = result.substring(0, result.length -2 )
        /*
          value = result.substring
        re-setting value with new string

          (0, result.length -2 )
        starting at the index 0, take way 2 from the end of the length of the result
        */
        return value
    }

    private fun isOperatorAdded(value : String) : Boolean {
        return if(value.startsWith("-")){
            false
        }else{
            value.contains("/")
                    || value.contains("*")
                    || value.contains("+")
                    || value.contains("-")
        }
    }
}