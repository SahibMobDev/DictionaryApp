package com.example.dictionaryapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView

class DefinitionWordActivity : AppCompatActivity() {

    private val KEY = "WORD_DEFINITION"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_definition_word)

        val tvDefinition : TextView = findViewById(R.id.tv_definition)
        val arrowBack : ImageView = findViewById(R.id.arrow_back)

        tvDefinition.text = intent.getStringExtra(KEY)
        arrowBack.setOnClickListener { finish() }
    }
}