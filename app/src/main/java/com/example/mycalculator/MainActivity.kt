package com.example.mycalculator

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*



class MainActivity : AppCompatActivity() {
    private var numberOne:Double=0.0;
    private var numberTwo:Double=0.0;
    private var operationOnCourse:Int = 0;
    private var numberResult:String="";

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvw_result.text = "0"
        operationOnCourse = NO_OPERATION

        btn_one.setOnClickListener{ pressNumber("1") }
        btn_two.setOnClickListener{ pressNumber("2") }
        btn_three.setOnClickListener{ pressNumber("3") }
        btn_four.setOnClickListener{ pressNumber("4") }
        btn_five.setOnClickListener{ pressNumber("5") }
        btn_six.setOnClickListener{ pressNumber("6") }
        btn_seven.setOnClickListener{ pressNumber("7") }
        btn_eight.setOnClickListener{ pressNumber("8") }
        btn_nine.setOnClickListener{ pressNumber("9") }
        btn_zero.setOnClickListener{ pressNumber("0") }
        btn_dot.setOnClickListener{ pressNumber(".") }

        btn_plus.setOnClickListener{ pressOperation(PLUS)}
        btn_minus.setOnClickListener{ pressOperation(MINUS)}
        btn_mult.setOnClickListener{ pressOperation(MULTIPLY)}
        btn_divide.setOnClickListener{ pressOperation(DIVIDE)}

        btnClear.setOnClickListener{ resetAll() }

        btn_equal.setOnClickListener{ resolveOperation() }

        btn_show_result.setOnClickListener{ sentResultToNewActivity() }
    }

    private fun pressNumber(number:String){
        if(tvw_result.text == "0" && number != ".") {
            tvw_result.text = "$number"
        } else {
            tvw_result.text = "${tvw_result.text}$number"
        }

        if(operationOnCourse == NO_OPERATION){
            numberOne = tvw_result.text.toString().toDouble()
        }else{
            numberTwo = tvw_result.text.toString().toDouble()
        }
    }

    private fun pressOperation(operation:Int){
        this.operationOnCourse = operation;
        numberOne = tvw_result.text.toString().toDouble()
        tvw_result.text = "0"
    }

    companion object{
        const val PLUS = 1
        const val MINUS = 2
        const val MULTIPLY = 3
        const val DIVIDE = 4
        const val NO_OPERATION = 0
    }

    private fun resetAll(){
        numberOne = 0.0
        numberTwo = 0.0
        tvw_result.text = "0"
    }

    private fun resolveOperation(){
        var resultOperation = when(operationOnCourse){
            PLUS -> numberOne + numberTwo
            MINUS -> numberOne - numberTwo
            MULTIPLY -> numberOne * numberTwo
            DIVIDE -> numberOne / numberTwo
            else -> 0
        }
        numberOne = resultOperation as Double

        tvw_result.text = if("$resultOperation".endsWith(".0")) { "$resultOperation".replace(".0","") } else { "%.2f".format(resultOperation) }

        numberResult = if("$resultOperation".endsWith(".0")) { "$resultOperation".replace(".0","") } else { "%.2f".format(resultOperation) }
    }

    private fun sentResultToNewActivity(){
        val intent = Intent(this, ResultActivity::class.java)
        intent.putExtra("result",numberResult)
        startActivity(intent)
    }
}