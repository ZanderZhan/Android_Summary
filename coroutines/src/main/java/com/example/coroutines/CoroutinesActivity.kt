package com.example.coroutines

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.widget.Button
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.broadcast
import java.lang.Exception
import kotlin.coroutines.Continuation
import kotlin.coroutines.ContinuationInterceptor
import kotlin.coroutines.CoroutineContext

class CoroutinesActivity : AppCompatActivity() {
    private val TAG = "coroutine_tag"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.coroutine_layout)

        findViewById<Button>(R.id.button1).setOnClickListener {
            startActivity(Intent(baseContext, FirstActivity::class.java))
        }

        findViewById<Button>(R.id.button2).setOnClickListener {
            startActivity(Intent(baseContext, SecondActivity::class.java))
        }


        // 1. 协程取消
//        runBlocking {
//            val job = launch(start = CoroutineStart.LAZY) {
//                delay(500)
//                Log.d(TAG, "launch running Coroutines")
//            }
//            Log.d(TAG, "waiting launch running")
//            job.join()
//            Log.d(TAG, "runBlocking running end")
//        }

        // 2. 超时处理
//        runBlocking {
//            withTimeout(1300L) {
//                repeat(1000) { i ->
//                    Log.d(TAG, "I'm sleeping $i ...")
//                    delay(500L)
//                }
//            }
//        }

        // 协程调度器
//        runBlocking {
//            launch {
//                Log.d(TAG, "Im working in thread ${Thread.currentThread().name}")
//            }
//            launch(Dispatchers.Unconfined) {
//                Log.d(TAG, "Unconfined before I'm working in thread ${Thread.currentThread().name}")
//                delay(500)
//                Log.d(TAG, "Unconfined after I'm working in thread ${Thread.currentThread().name}")
//            }
//            launch(Dispatchers.Default) {
//                Log.d(TAG, "Default I'm working in thread ${Thread.currentThread().name}")
//            }
//            launch(newSingleThreadContext("MyOWnThread")) {
//                Log.d(TAG, "newSingleThreadContext I'm working in thread ${Thread.currentThread().name}")
//            }
//            launch(Dispatchers.IO) {
//                Log.d(TAG, "IO I'm working in thread ${Thread.currentThread().name}")
//            }
//        }

//        GlobalScope.launch {
//            Log.d(TAG, "context 1: ${coroutineContext[Job]}")
//        }

//        Log.d(TAG, "test: ${Test}")
//        Log.d(TAG, "test2: test")
//
//        GlobalScope.launch(MyContinuationInterceptor()) {
//            log("1")
//            val job = async {
//                log("2")
//                delay(1000)
//                log("3")
//                "Hello"
//            }
//            log("4")
//            val result = job.await()
//            log("5. $result")
//        }

    }
}

fun log(content: String) {
    Log.d("coroutine_tag", "[${Thread.currentThread().name}]: $content")
}

interface Test {
    companion object Key: Test2 {
        override var name: String
            get() = TODO("Not yet implemented")
            set(value) {}
    }
}

interface Test2 {
    var name: String
}

class MyContinuationInterceptor: ContinuationInterceptor {
    override val key: CoroutineContext.Key<*> = ContinuationInterceptor

    override fun <T> interceptContinuation(continuation: Continuation<T>): Continuation<T> {
        return object : Continuation<T> {
            override val context: CoroutineContext = continuation.context

            override fun resumeWith(result: Result<T>) {
                log("<MyContinuation> $result")
                continuation.resumeWith(result)
            }
        }
    }
}
