package com.example.room.list

import android.content.Context
import android.graphics.Color
import android.view.View
import androidx.appcompat.widget.AppCompatImageButton
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.room.R

class PuppyListLayout(context: Context): ConstraintLayout(context) {

    val actionSize = (44 * resources.displayMetrics.density).toInt()

    val actionView = AppCompatImageButton(context).apply {
        setImageResource(R.drawable.ic_baseline_add_24)
        setBackgroundColor(Color.BLUE)
    }

    val recyclerListView = RecyclerView(context).apply {
        id = View.generateViewId()
        layoutManager = LinearLayoutManager(context).apply {
            orientation = LinearLayoutManager.VERTICAL
        }
    }

    init {
        addView(recyclerListView, LayoutParams(0, 0).apply {
            leftToLeft = LayoutParams.PARENT_ID
            rightToRight = LayoutParams.PARENT_ID
            topToTop = LayoutParams.PARENT_ID
            bottomToBottom = LayoutParams.PARENT_ID
        })

        addView(actionView, LayoutParams(actionSize, actionSize).apply {
            rightToRight = LayoutParams.PARENT_ID
            bottomToBottom = LayoutParams.PARENT_ID
        })
    }

}