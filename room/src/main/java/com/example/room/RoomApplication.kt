package com.example.room

import android.app.Application
import com.example.room.db.AppDatabase

class RoomApplication: Application() {

    val database by lazy { AppDatabase.getDatabase(this) }

}