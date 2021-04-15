package com.example.handler

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.util.Log
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import java.util.concurrent.ThreadPoolExecutor

fun log(info: String) {
    Log.i("HandlerActivity", "${Thread.currentThread().name} _ ${info}")
}

class HandlerActivity : AppCompatActivity() {

    override fun onResume() {
        super.onResume()

        val handler = Handler(Looper.getMainLooper()) {
            log(it.data.getString("msg") ?: "empty")
            true
        }

//        Executors.newCachedThreadPool {  }
//        ThreadPoolExecutor
        Thread {
            log("onResume")
            val msg = Message().apply {
                data = Bundle().apply {
                    putString("msg", "msg from handle")
                }
            }
            handler.sendMessage(msg)
        }.start()

        Thread()
    }
}