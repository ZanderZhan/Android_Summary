package com.example.coroutines

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.coroutines.*

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // see what coroutineContext include
        runBlocking {
            log("parent: ${Job} __ ${coroutineContext[Job]}")
            launch {
                log("child: ${Job} __ ${coroutineContext[Job]}")
                log("launch: $coroutineContext")
            }
        }

        // companion object
        val testObject = TestObject()
        log("test: ${Test}, ${testObject}")

        // coroutineContext#plus
        val coroutineName = CoroutineName("Zander_Cor")
        val newCoroutineContext = coroutineName.plus(Dispatchers.IO)
        log("new coroutine ${newCoroutineContext}")
        val new2 = newCoroutineContext.plus(CoroutineName("Zander_Cor2"))
        log("new coroutine ${new2}")
    }

}

interface Test {
    companion object Key: Test
}

class TestObject: Test {

}