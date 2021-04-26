package com.example.dictionaryapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView

private val KEY = "WORD_DEFINITION"


class DictionaryActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dictionary)
        var defination = findViewById<TextView>(R.id.definition_text_view)
        var img = findViewById<ImageView>(R.id.back_btn)
        defination.text = intent.getStringExtra(KEY)

        img.setOnClickListener { finish() }
    }
}