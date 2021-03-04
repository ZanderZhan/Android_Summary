package com.example.coroutines

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.coroutines.*

class FirstActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 1. launch 创建 https://pl.kotl.in/HFt360JL5
//        val job = GlobalScope.launch {
//            log("1")
//            log("2")
//            log("3")
//            try {
//                delay(100)
//            } catch (e: Exception) {
//                log("exception: $e")
//            }
//            log("4")
//        }
//
//        log("5")    // 5 跟 1 的先后也是不确定的。
//        job.cancel()    // 这个 cancel 的机制是怎样的？为什么有时候没有跑到 1、2、3，有时候却能跑到？
//        log("6")

        // 2. async 创建
//        GlobalScope.launch {
//            val deferred1 = async {
//                log("1}")
//                delay(2000L)
//                log("2}")
//                "deferred1 result"
//            }
//            val deferred2 = async {
//                log("3}")
//                delay(3000L)
//                log("4}")
//                "deferred2 result"
//            }
//
//            // 这里的 await() 与 join() 有何区别？
//            // join 会挂起协程，等待 deferred1 执行完完成。如果 deferred 还未 start，则 start。
//            // await 也会挂起协程，等待并获取 deferred1 的执行结果。
//            log("5, result: ${deferred1.join()}")
////            log("5, result: ${deferred2.await()}")
//            log("6")
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
//        GlobalScope.launch {
//            log("1")
//            val job = GlobalScope.launch(start = CoroutineStart.LAZY) {
//                log("2")
//                delay(1000L)
//                log("3")
//            }
////            job.start()   // 1 4 2 3
//            job.join()      // 1 2 3 4
//            log("4")
//        }

        // 3. ATOMIC
//        GlobalScope.launch {
//            log("1")
//            val job = GlobalScope.launch(start = CoroutineStart.ATOMIC) {
//                log("2")
//                log("2-1")
//                delay(1000L)
//                log("3")
//            }
//            job.cancel()  // 2 和 4 的顺序无法确定。
//            log("4")
//        }

        // 4. UNDISPATCHED
//        GlobalScope.launch {
//            log("1")
//            val job = GlobalScope.launch(start = CoroutineStart.UNDISPATCHED) {
//                log("2")
//                log("2-1")
//                delay(1000L)
//                log("3")
//            }
//            job.cancel()  // 同一个线程，并且 2 肯定会优于 4 执行
//            log("4")
//        }
//        GlobalScope.launch {
//            log("1")
//            val job = GlobalScope.launch(start = CoroutineStart.UNDISPATCHED) {
//                log("2")
//                delay(1000)
//                log("3")
//            }
//            log("4")
//            job.join()
//            log("5")
//        }

//        runBlocking {
//            launch {
//                delay(200L)
//                log("Task from runBlocking")
//            }
//
//            coroutineScope { // Creates a coroutine scope
//                launch {
//                    delay(500L)
//                    log("Task from nested launch")
//                }
//
//                delay(100L)
//                log("Task from coroutine scope") // This line will be printed before the nested launch
//            }
//
//            log("Coroutine scope is over") //
//        }
//
//        log("------ end ------")

    }
}