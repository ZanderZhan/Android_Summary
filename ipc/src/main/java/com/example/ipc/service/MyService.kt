package com.example.ipc.service

import android.app.Service
import android.content.Intent
import android.os.*
import android.util.Log


val LogTag = "MyService"

class MyService: Service() {

    private var binder: MyServiceBinder? = null

    override fun onBind(intent: Intent?): IBinder? {
        Log.d(LogTag, "onBind")
        binder = MyServiceBinder()
        return binder
    }

    override fun onUnbind(intent: Intent?): Boolean {
        Log.d(LogTag, "onUnBind")
        return super.onUnbind(intent)
    }

    override fun onCreate() {
        super.onCreate()
        Log.d(LogTag, "onCreate")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d(LogTag, "onStartCommand")
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy() {
        Log.d(LogTag, "onDestroy")
        super.onDestroy()
    }
}

class MessageService: Service() {

    private val handler = object : Handler(Looper.getMainLooper()) {
        override fun handleMessage(msg: Message) {
            val bundle = msg.data;
            val id = bundle.getInt("id");
            Log.d(LogTag, "receive id from client, id: $id")
        }
    }

    override fun onBind(intent: Intent?): IBinder? {
        Log.d(LogTag, "onBind")
        return Messenger(handler).binder
    }

    override fun onUnbind(intent: Intent?): Boolean {
        Log.d(LogTag, "onUnBind")
        return super.onUnbind(intent)
    }

    override fun onCreate() {
        super.onCreate()
        Log.d(LogTag, "onCreate")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d(LogTag, "onStartCommand")
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy() {
        Log.d(LogTag, "onDestroy")
        super.onDestroy()
    }
}