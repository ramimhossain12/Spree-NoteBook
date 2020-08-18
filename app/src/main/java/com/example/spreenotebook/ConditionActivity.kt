package com.example.spreenotebook

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ConditionActivity : AppCompatActivity() {
    private var agree: TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_condition)
        agree = findViewById(R.id.agree2ID)
        agree?.setOnClickListener(View.OnClickListener {
            val `in` = Intent(this@ConditionActivity, NotesActivity::class.java)
            startActivity(`in`)
        })
    }
}