package com.example.ipc

import android.app.ActivityManager
import android.content.Context
import android.os.Process
import java.io.FileInputStream
import java.io.IOException
import java.nio.charset.Charset

fun getProcessName(cxt: Context): String? {
    val pid = Process.myPid()
    val am = cxt.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
    val runningApps = am.runningAppProcesses ?: return null
    for (procInfo in runningApps) {
        if (procInfo.pid == pid) {
            return procInfo.processName
        }
    }
    return null
}

fun getCurrentProcessName(): String? {
    var `in`: FileInputStream? = null
    try {
        val fn = "/proc/self/cmdline"
        `in` = FileInputStream(fn)
        val buffer = ByteArray(256)
        var len = 0
        var b: Int
        while (`in`.read().also { b = it } > 0 && len < buffer.size) {
            buffer[len++] = b.toByte()
        }
        if (len > 0) {
            return String(buffer, 0, len, Charset.forName("UTF-8"))
        }
    } catch (e: Throwable) {
        e.printStackTrace()
    } finally {
        if (`in` != null) {
            try {
                `in`.close()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }
    return null
}