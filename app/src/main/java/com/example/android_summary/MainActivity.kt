package com.example.android_summary

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.activityresultapi.MainActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        findViewById<CustomView>(R.id.customView).smoothScrollTo(-400)
        findViewById<Button>(R.id.activity_result).setOnClickListener {
            startActivity(Intent(baseContext, MainActivity::class.java))
        }
    }

}