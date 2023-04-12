package com.example.mycalculator

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_result.*

class ResultActivity : AppCompatActivity() {
    private var resultToShare:String="";
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)
        val bundle = intent.extras
        val result = bundle?.getString("result")
        tvw_result_operation.text = result
        resultToShare = "${result.toString()} es el dato proporcionado por la app de EDWIN .A, MARCO .T, SERGIO .O ."
        btn_back_activity.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        btn_share.setOnClickListener{shareResult()}
    }

    private fun shareResult(){
        val intent = Intent().apply{
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT,resultToShare)
            type = "text/plain"
            putExtra(Intent.EXTRA_TITLE,"¿Quieres compartir el resultado de la operación?")
        }
        val shareIntent = Intent.createChooser(intent,null)
        startActivity(shareIntent)
    }
}