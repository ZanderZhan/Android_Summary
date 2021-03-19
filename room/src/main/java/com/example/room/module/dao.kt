package com.example.room.module

import androidx.room.Dao
import androidx.room.Query

@Dao
interface PuppyDao {
}

@Dao
interface OwnerDao {

    @Query("select * from owner")
    fun all(): List<Owner>

}