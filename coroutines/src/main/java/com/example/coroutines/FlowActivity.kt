package com.example.coroutines

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class FlowActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 1. 基础用法
//        lifecycleScope.launch {
//            flow<Int> {
//                for (i in 0..2) {
//                    emit(i)
//                }
//            }.collect {
//                log("receive $it")
//            }
//        }

        // 2. 线程，如果 flow on io，则 emit 和 collect 的顺序不确定？
//        lifecycleScope.launch {
//            flow<Int> {
//                for (i in 0..2) {
//                    // why main?
//                    log("emit on dispatcher io $i")
//                    emit(i)
//                }
//            }
//            .flowOn(Dispatchers.IO)
//            .collect {
//                log("flow on io receive $it")
//            }
//        }
//
        // 3. 异常捕获
//        lifecycleScope.launch {
//            flow<Int> {
//                for (i in 0..2) {
//                    emit(i)
//                    if (i == 1) {
//                        throw Exception("value not match")
//                    }
//                }
//            }
//                    .catch {
//                        log("some wrong: ${it.message}")
//                    }
//                    .collect {
//                        log("receive $it")
//                    }
//        }
//        lifecycleScope.launch {
//            try {
//                flow<Int> {
//                    for (i in 0..2) {
//                        emit(i)
//                        if (i == 1) {
//                            throw Exception("value not match")
//                        }
//                    }
//                }
//                        .collect {
//                            log("receive $it")
//                        }
//            } catch (e: Throwable) {
//                log("some wrong: ${e.message}")
//            }
//
//        }

        // 4. 冷流，懒加载，如果没有 collect ，是不会执行数据 emit 的
//        lifecycleScope.launch {
//            flow<Int> {
//                log("start collect value and emit")
//                for (i in 0..2) {
//                    log("emit value: $i")
//                    emit(i)
//                }
//            }
//                    .collect {
//                        log("receive $it")
//                    }
//        }
    }

    private fun log(content: String) {
        Log.i(javaClass.simpleName, "thread: ${Thread.currentThread().name}, content: $content")
    }
}