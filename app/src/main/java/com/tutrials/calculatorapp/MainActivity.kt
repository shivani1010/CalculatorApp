package com.tutrials.calculatorapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    var tv_result:TextView? = null
    var lastNumeric:Boolean = false
    var lastDot:Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tv_result = findViewById(R.id.tv_result)

    }

    fun onDigit(view: View)
    {
        tv_result?.append((view as Button).text)
        lastNumeric=true


    }

    fun onClear(view:View)
    {
        tv_result?.text=""
    }

    fun onDecimalPoint(view: View)
    {
        if(lastNumeric && !lastDot)
        {
            tv_result?.append(".")
            lastNumeric=false
            lastDot=true
        }
    }

    fun isOperatorAdded(value : String) :Boolean {
        return  if(value.startsWith("-")){false}else{
            value.contains("/")
                    || value.contains("*")
                    ||value.contains("+")
                    || value.contains("-")
        }
    }

    fun onOperator(view: View)
    {
        tv_result?.text?.let{
            if(lastNumeric && !isOperatorAdded(it.toString()))
            {
                tv_result?.append((view as Button).text)
                lastNumeric=false
                lastDot=false

            }
        }

    }

    fun onEqual(view:View)
    {
        if(lastNumeric)
        {
            var prefix=""
            var tv_value=tv_result?.text.toString()
            try{
                if(tv_value.startsWith("-"))
                {
                        prefix="-"
                        tv_value=tv_value.substring(1)
                }
                if(tv_value.contains("-")) {
                    val split_Value = tv_value.split("-")
                    var one = split_Value[0]
                    var two = split_Value[1]

                    if (prefix.isNotEmpty()) {
                        one = prefix + one
                    }
                    tv_result?.text = removeZeroAfterDot((one.toDouble() - two.toDouble()).toString())
                }else if(tv_value.contains("+")) {
                    val split_Value = tv_value.split("+")
                    var one = split_Value[0]
                    var two = split_Value[1]

                    if (prefix.isNotEmpty()) {
                        one = prefix + one
                    }
                    tv_result?.text = removeZeroAfterDot((one.toDouble() + two.toDouble()).toString())
                }else if(tv_value.contains("/")) {
                    val split_Value = tv_value.split("/")
                    var one = split_Value[0]
                    var two = split_Value[1]

                    if (prefix.isNotEmpty()) {
                        one = prefix + one
                    }
                    tv_result?.text =removeZeroAfterDot ((one.toDouble() / two.toDouble()).toString())
                }else if(tv_value.contains("*")) {
                    val split_Value = tv_value.split("*")
                    var one = split_Value[0]
                    var two = split_Value[1]

                    if (prefix.isNotEmpty()) {
                        one = prefix + one
                    }
                    tv_result?.text = removeZeroAfterDot((one.toDouble() * two.toDouble()).toString())
                }

            }catch (e :java.lang.ArithmeticException)
            {
                e.printStackTrace()
            }
        }
    }

    fun removeZeroAfterDot(result: String) : String
    {
        var value=result
        if(result.contains(".0"))
            value =result.substring(0,result.length-2)
        return  value

    }
}