package com.example.room

import android.content.Context
import android.os.Bundle
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.lifecycleScope
import com.example.room.module.Owner
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddPuppyActivity: AppCompatActivity() {

    private lateinit var layout: AddPuppyLayout


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        layout = AddPuppyLayout(baseContext)
        setContentView(layout)

        val db = (applicationContext as RoomApplication).database

        layout.button.setOnClickListener {

            layout.owner.text?.toString()?.let {
                lifecycleScope.launch(Dispatchers.IO) {
                    db.ownerDao().insert(Owner(it))
                    Log.i(RoomTag, "insert owner $it")
                }
            }

        }
    }

}

class AddPuppyLayout(context: Context): ConstraintLayout(context) {

    val inputHeight = (44 * resources.displayMetrics.density).toInt()

    val name = EditText(context).apply {
        id = View.generateViewId()
        hint = "小狗的名字"
    }

    val age = EditText(context).apply {
        id = View.generateViewId()
        hint = "年龄"
    }

    val owner = EditText(context).apply {
        id = View.generateViewId()
        hint = "主人"
    }

    val button = Button(context).apply {
        text = "提交"
    }

    init {

        setPadding(2 * inputHeight, 0, 2 * inputHeight, 0)

        addView(name, LayoutParams(0, inputHeight).apply {
            leftToLeft = LayoutParams.PARENT_ID
            rightToRight = LayoutParams.PARENT_ID
            topToTop = LayoutParams.PARENT_ID
            topMargin = 3 * inputHeight
        })

        addView(age, LayoutParams(0, inputHeight).apply {
            leftToLeft = LayoutParams.PARENT_ID
            rightToRight = LayoutParams.PARENT_ID
            topToBottom = name.id
            topMargin = inputHeight
        })

        addView(owner, LayoutParams(0, inputHeight).apply {
            leftToLeft = LayoutParams.PARENT_ID
            rightToRight = LayoutParams.PARENT_ID
            topToBottom = age.id
            topMargin = inputHeight
        })

        addView(button, LayoutParams(0, inputHeight).apply {
            leftToLeft = LayoutParams.PARENT_ID
            rightToRight = LayoutParams.PARENT_ID
            topToBottom = owner.id
            topMargin = inputHeight
        })
    }

}