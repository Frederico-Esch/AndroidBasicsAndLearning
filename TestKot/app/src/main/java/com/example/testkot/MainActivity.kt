package com.example.testkot

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.core.text.isDigitsOnly

class MainActivity : AppCompatActivity() {
    var numero:Int = 0
    lateinit var textView:TextView
    lateinit var botao:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        textView = findViewById(R.id.texto)
        botao = findViewById(R.id.botao)
        botao.setOnClickListener {
            adicionar()
        }
    }

    fun adicionar(){
        this.numero += 1
        textView.setText(numero.toString())
    }

}