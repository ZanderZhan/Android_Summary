package com.example.android_summary

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.OverScroller
import android.widget.Scroller

class CustomView constructor(context: Context, attributes: AttributeSet?): View(context, attributes) {

    private val mScroll: OverScroller
    private var lastX = 0f
    private var lastY = 0f

    init {
        mScroll = OverScroller(context)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {

        val x = event.x
        val y = event.y

        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                lastX = x
                lastY = y
            }
            MotionEvent.ACTION_MOVE -> {
                val offsetX = x - lastX
                val offsetY = y - lastY

                // 1. 通过 layout 布局
//                Log.i("zander", "offset, $x - $offsetY, $y - $offsetY")
//                Log.i("zander", "cur position: $left, $top, $right, $bottom")
//                layout(left + offsetX.toInt(), top + offsetY.toInt(),
//                right + offsetX.toInt(), bottom + offsetY.toInt())

                // 2. offset
//                offsetLeftAndRight(offsetX.toInt())
//                offsetTopAndBottom(offsetY.toInt())

                // 3. layoutparams
                val lp = (layoutParams as ViewGroup.MarginLayoutParams)
                lp.leftMargin = left + offsetX.toInt()
                lp.topMargin = top + offsetY.toInt()
                layoutParams = lp

            }
        }

        return true
    }


    fun smoothScrollTo(destX: Int) {
        val sX = scrollX
        val delta = destX - sX
        Log.i("zander", "cur: $sX, delta: $delta")
        mScroll.startScroll(sX, 0, delta, 0, 2000)
        invalidate()
    }

    override fun computeScroll() {
        super.computeScroll()
        if (mScroll.computeScrollOffset()) {
            Log.i("zander", "scroll cur: ${mScroll.currX} ${mScroll.currY}")
            (parent as View).scrollTo(mScroll.currX, mScroll.currY)
            invalidate()
        }
    }
}