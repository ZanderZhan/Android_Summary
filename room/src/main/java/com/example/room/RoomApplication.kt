package com.example.room

import android.app.Application
import com.example.room.db.AppDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class RoomApplication: Application() {

    val applicationScope by lazy { CoroutineScope(SupervisorJob()) }

    val database by lazy { AppDatabase.getDatabase(this, applicationScope) }

}