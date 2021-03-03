package com.example.android_summary

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.activityresultapi.MainActivity
import com.example.coroutines.CoroutinesActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        findViewById<CustomView>(R.id.customView).smoothScrollTo(-400)
        findViewById<Button>(R.id.activity_result).setOnClickListener {
            startActivity(Intent(baseContext, MainActivity::class.java))
        }

        findViewById<Button>(R.id.scoped_storage).setOnClickListener {
            startActivity(Intent(baseContext, com.example.scopedstorage.MainActivity::class.java))
        }

        findViewById<Button>(R.id.kotlin_coroutine).setOnClickListener {
            startActivity(Intent(baseContext, CoroutinesActivity::class.java))
        }
    }

}