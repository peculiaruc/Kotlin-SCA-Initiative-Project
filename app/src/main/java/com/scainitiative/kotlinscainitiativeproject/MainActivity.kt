package com.scainitiative.kotlinscainitiativeproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var isOperator = true
    var numbers = ""
    var operators = "+"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val w = window;
        w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
    }

    fun numberClicked(view: View) {
        if (isOperator)
            displayEdittext.setText("")
        isOperator = false

        var buttonClicked  =  displayEdittext.text.toString()
        var buttonSelected  =  view as Button
        when(buttonSelected.id){

            cal_one.id -> {buttonClicked  += "1"}
            cal_two.id -> {buttonClicked  += "2"}
            cal_three.id -> {buttonClicked  += "3"}
            cal_four.id -> {buttonClicked  += "4"}
            cal_five.id -> {buttonClicked  += "5"}
            cal_six.id -> {buttonClicked  += "6"}
            cal_seven.id -> {buttonClicked  += "7"}
            cal_eight.id -> {buttonClicked  += "8"}
            cal_nine.id -> {buttonClicked  += "9"}
            cal_zero.id -> {buttonClicked  += "0"}
            cal_full_stop.id -> {buttonClicked  +=  "."}
            cal_add_minus.id -> {buttonClicked  + "-$buttonClicked"}

        }
        displayEdittext.setText(buttonClicked)

    }

    fun signOperation(view: View) {
        isOperator = true
        numbers = displayEdittext.text.toString()
        var buttonSelected = view as Button
        when (buttonSelected.id){
            cal_minus.id -> operators = "-"
            cal_divide.id -> operators = "/"
            cal_multiply.id -> operators = "*"
            cal_plus.id -> operators = "+"
        }


    }

    fun equalTo(view: View) {
        var anumber = displayEdittext.text.toString()
        var result = 0.0
        when (operators){
            "+" -> {result = numbers.toDouble() + anumber.toDouble()}
            "*" -> {result = numbers.toDouble() * anumber.toDouble()}
            "-" -> {result = numbers.toDouble() - anumber.toDouble()}
            "/" -> {result = numbers.toDouble() / anumber.toDouble()}
        }
        displayEdittext.setText(result.toString())

    }

    fun clear(view: View) {
        displayEdittext.setText("0")
        isOperator = true
    }

    fun percentage(view: View) {
        var num = displayEdittext.text.toString().toDouble()/100
        displayEdittext.setText(num.toString())
        isOperator = true

    }
}
