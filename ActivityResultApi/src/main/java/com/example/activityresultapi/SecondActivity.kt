package com.example.activityresultapi

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class SecondActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.layout_second)

        findViewById<TextView>(R.id.from).text = "from: ${intent.getStringExtra("param")}"

        findViewById<Button>(R.id.button).setOnClickListener {
            setResult(RESULT_OK, Intent().apply {
                putExtra("result", "${intent.getStringExtra("param")} finish")
            })
            finish()
        }
    }

}