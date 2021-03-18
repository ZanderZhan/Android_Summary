package com.example.coroutines

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.*

class FirstActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // callback 方式处理异步操作
//        getNetWorkUserInfo( { info ->
//            writeInfoToDb(info, { succ ->
//                if (succ) {
//                    println("写入成功")
//                } else {
//                    println("写入失败")
//                }
//            })
//        })

        // 协程的处理
//        GlobalScope.launch {
//            val info = getNetWorkUserInfo2()
//            val succ = writeInfoToDb2(info)
//            if (succ) {
//                println("写入成功")
//            } else {
//                println("写入失败")
//            }
//        }

        // 基本用法
//        val job = GlobalScope.launch(Dispatchers.IO + CoroutineName("zander")) {
//            log("协程体...")
//            for (i in 0..2) {
//                log("$i")
//                delay(1000)
//            }
//        }
//        job.cancel()


        // 1. launch 创建
//        val job = GlobalScope.launch {
//            log("1")
//            log("2")
//            log("3")
//            try {
//                delay(1000)
//            } catch (e: Exception) {
//                log("exception: ${e.message}")
//            }
//            log("4")
//        }
//
//        log("5")    // 5 跟 1 的先后是不确定的。
//        job.cancel()    // cancel() 也可能在 1 之前，所以 1、2、3、4 可能不会打印出来
//        log("6")

        // 2. async 创建
//        GlobalScope.launch {
//            val deferred1 = async {
//                log("1")
//                log("2")
//                "deferred1 result"
//            }
//            val deferred2 = async {
//                log("3")
//                log("4")
//                "deferred2 result"
//            }
//
//            // await() 挂起，直到 deferred1 处理完并返回值
////            log("5, result: ${deferred1.await()}")
////            log("6")
//        }

        // 3. runBlocking
//        runBlocking {
//            log("1")
//            delay(100)
//            log("2")
//        }
//        log("3")


        // 启动模式

        // 1. DEFAULT
        // 使用默认调度器，在 JVM 上，它在后台会有一些线程处理异步任务。
        // 输出 1/2 或者 2/1，取决于 CPU 对于当前线程与后台线程的调度顺序。
//        GlobalScope.launch(start = CoroutineStart.DEFAULT) {
//            log("1")
//        }
//        log("2")

        // 2. LAZY
//        log("1")
//        val job = GlobalScope.launch(start = CoroutineStart.LAZY) {
//            log("2")
//            delay(1000L)
//            log("3")
//        }
//        job.start()   // 1 4 2 3 or 1 2 4 3
//        log("4")

        // 3. ATOMIC
//        log("1")
//        val job = GlobalScope.launch(start = CoroutineStart.ATOMIC) {
//            log("2")
//            log("3")
//            delay(1000L)
//            log("4")
//        }
//        job.cancel()  // 1 2 3 5 or 1 5 2 3
//        log("5")

        // 4. UNDISPATCHED
//        log("1")
//        val job = GlobalScope.launch(start = CoroutineStart.UNDISPATCHED) {
//            log("2")
//            log("3")
//            delay(1000L)
//            log("4")
//        }
//        job.cancel()  // 同一个线程，并且 2 肯定会优于 4 执行
//        log("5")

    }

    private fun getNetWorkUserInfo(succ: (info: String) -> Unit) {
        // 网络请求，解析结果...
        val info = "zander"
        succ.invoke(info)
    }

    private fun writeInfoToDb(info: String, cb: (succ: Boolean) -> Unit) {
        // 写 db 操作...
        cb.invoke(true)
    }

    suspend fun getNetWorkUserInfo2(): String {
        // 网络请求，解析结果...
        val info = "zander"
        return info
    }

    suspend fun writeInfoToDb2(info: String): Boolean {
        // 写 db 操作...，写入成功，则返回 true
        return true
    }
}