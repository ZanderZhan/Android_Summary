package com.example.ipc

import android.os.Parcel
import android.os.Parcelable


class Student(var name: String, var age: Int): Parcelable {
    constructor(parcel: Parcel): this(parcel.readString() ?: "", parcel.readInt())

    override fun describeContents(): Int = 0

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(name)
        dest.writeInt(age)
    }

    fun readFromParcel(source: Parcel) {
        name = source.readString()!!
        age = source.readInt()
    }

    companion object CREATOR : Parcelable.Creator<Student> {
        override fun createFromParcel(parcel: Parcel): Student {
            return Student(parcel)
        }

        override fun newArray(size: Int): Array<Student> {
            return Array(size) {
                Student("", 0)
            }
        }
    }

}