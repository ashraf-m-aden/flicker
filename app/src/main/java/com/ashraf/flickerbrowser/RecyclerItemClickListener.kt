package com.ashraf.flickerbrowser

import android.content.Context
import android.util.Log
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import androidx.core.view.GestureDetectorCompat
import androidx.recyclerview.widget.RecyclerView

class RecyclerItemClickListener(context:Context, recyclerView:RecyclerView,private val listener: OnRecyclerClickListener):RecyclerView.SimpleOnItemTouchListener() {
    private val TAG = "RecyclerItemClickList"

    interface OnRecyclerClickListener{
        fun onItemClick(view: View?, position:Int)
        fun onItemLongClick(view:View, position: Int)
    }

    // add the gesture detector
    private val gestureDetector = GestureDetectorCompat(context, object : GestureDetector.SimpleOnGestureListener(){
        override fun onSingleTapUp(e: MotionEvent): Boolean {
            Log.d(TAG, "onSingleTapUp")
            val childView = recyclerView.findChildViewUnder(e.x,e.y)
            listener.onItemClick(childView, recyclerView.getChildAdapterPosition(childView!!))
            return true
        }

        override fun onLongPress(e: MotionEvent) {
            val childView = recyclerView.findChildViewUnder(e.x,e.y)
            listener.onItemClick(childView, recyclerView.getChildAdapterPosition(childView!!))
        }
    })

    override fun onInterceptTouchEvent(rv: RecyclerView, e: MotionEvent): Boolean {
        Log.d(TAG, "onIntercepter touch event start $e")
        val result = gestureDetector.onTouchEvent(e)
        Log.d(TAG, "onIntercepter touch returning: $result")

        return result
    }
}