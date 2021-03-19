package com.example.room.module

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Owner(
    @PrimaryKey val name: String
)

@Entity
data class Puppy(
    @PrimaryKey val name: String,
    @ColumnInfo(defaultValue = "0") val age: Int
)