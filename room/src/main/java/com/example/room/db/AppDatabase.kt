package com.example.room.db

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.room.module.Owner
import com.example.room.module.OwnerDao
import com.example.room.module.Puppy
import com.example.room.module.PuppyDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(
    entities = [
        Puppy::class,
        Owner::class
   ],
    version = 1
)
abstract class AppDatabase: RoomDatabase() {

    abstract fun ownerDao(): OwnerDao

    abstract fun puppyDao(): PuppyDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context, scope: CoroutineScope): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "android_summary_room"
                ).addCallback(object : Callback() {
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        super.onCreate(db)
                        scope.launch {
                            INSTANCE?.let {
                                it.ownerDao().insert(Owner("zander"))
                            }
                            Log.i("zander", "create successfully")
                        }
                    }
                })
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }

}