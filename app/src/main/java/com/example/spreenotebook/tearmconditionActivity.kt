package com.example.spreenotebook

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class tearmconditionActivity : AppCompatActivity(), View.OnClickListener {
    private var textView1: TextView? = null
    private var textView2: TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tearmcondition)
        textView1 = findViewById(R.id.skipID)
        textView2 = findViewById(R.id.nextID)
        textView1?.setOnClickListener(this)
        textView2?.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.skipID -> {
                val i = Intent(this@tearmconditionActivity, NotesActivity::class.java)
                startActivity(i)
            }
            R.id.nextID -> {
                val `in` = Intent(this@tearmconditionActivity, ConditionActivity::class.java)
                startActivity(`in`)
            }
        }
    }
}