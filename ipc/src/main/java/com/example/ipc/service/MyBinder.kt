package com.example.ipc.service

import android.os.Binder
import android.os.Parcel
import android.util.Log
import com.example.ipc.IMyAidlInterface
import com.example.ipc.getCurrentProcessName

class MyBinder(val service: MyService): Binder() {

}

class MyServiceBinder(): Binder() {

    val service = object : IMyService {
        override fun say(word: String) {
            Log.d(LogTag, "IPC, $word in process: ${getCurrentProcessName()}")
        }
    }

    override fun onTransact(code: Int, data: Parcel, reply: Parcel?, flags: Int): Boolean {

        val world = data.readString()

        world?.let {
            service.say(it)
        }

        Log.d(LogTag, "IPC, ain process: ${getCurrentProcessName()}, $world, $code")

        reply?.writeString("reply wordddd")

        return true
    }

}

