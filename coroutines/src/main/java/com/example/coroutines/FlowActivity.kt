package com.example.coroutines

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking

class FlowActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        runBlocking {
            flow<Int> {
                for(i in 0..2) {
                    emit(i)
                }
            }.collect {
                log("receive $it")
            }
        }

        sequence<Int> {  }
    }

    private fun log(content: String) {
        Log.i(javaClass.simpleName, content)
    }
}