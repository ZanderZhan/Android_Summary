package com.example.room

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.room.list.PuppyListLayout

class RoomActivity : AppCompatActivity() {
    private lateinit var puppyListLayout: PuppyListLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        puppyListLayout = PuppyListLayout(baseContext)

        puppyListLayout.actionView.setOnClickListener {
            startActivity(Intent(this, AddPuppyActivity::class.java))
        }

        setContentView(puppyListLayout)
    }

}