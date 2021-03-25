package com.example.room.module

import androidx.room.*

@Dao
interface PuppyDao {
}

@Dao
interface OwnerDao {

    @Query("select * from owner")
    fun all(): List<Owner>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(owner: Owner)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun update(owner: Owner)
}