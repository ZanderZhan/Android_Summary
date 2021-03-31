package com.example.ipc

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.net.Uri
import android.os.*
import androidx.appcompat.app.AppCompatActivity
import android.util.Log
import com.example.ipc.service.*

class IPCActivity : AppCompatActivity() {

    val serviceConnection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
//            val myBinder = service as? MyServiceBinder
//            val myService = myBinder?.service
//
//            val data = Parcel.obtain()
//            val reply = Parcel.obtain()
//
//            data.writeString("a word from client")
//
//            try {
//                service?.transact(2, data, reply, 0)
//
//                val responseWord = reply.readString()
//
//                Log.d(LogTag, "IPC, client in process: ${getCurrentProcessName()}, $responseWord")
//            } catch (e: Throwable) {
//
//            }

            val messenger = Messenger(service)
            val message = Message.obtain()
            val bundle = Bundle().apply {
                putInt("id", 100)
            }
            message.data = bundle

            try {
                messenger.send(message)
            } catch (e: Throwable) {}

        }

        override fun onServiceDisconnected(name: ComponentName?) {

        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        bindService()

    }

    private fun bindService() {
        val intent = Intent(this, MessageService::class.java)
        bindService(intent, serviceConnection, BIND_AUTO_CREATE)
    }

    private fun startService() {
        val intent = Intent(this, MessageService::class.java)
        startService(intent)
    }
}