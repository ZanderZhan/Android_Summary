package com.example.android_summary

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.activityresultapi.MainActivity
import com.example.coroutines.CoroutinesActivity
import com.example.hilt.HiltActivity
import com.example.ipc.IPCActivity
import com.example.navigation.NavigationActivity
import com.example.room.RoomActivity

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

        findViewById<Button>(R.id.jetpack_navigation).setOnClickListener {
            startActivity(Intent(baseContext, NavigationActivity::class.java))
        }

        findViewById<Button>(R.id.room).setOnClickListener {
            startActivity(Intent(baseContext, RoomActivity::class.java))
        }

        findViewById<Button>(R.id.hilt).setOnClickListener {
            startActivity(Intent(baseContext, HiltActivity::class.java))
        }

        findViewById<Button>(R.id.ipc).setOnClickListener {
            startActivity(Intent(baseContext, IPCActivity::class.java))
        }

    }

}