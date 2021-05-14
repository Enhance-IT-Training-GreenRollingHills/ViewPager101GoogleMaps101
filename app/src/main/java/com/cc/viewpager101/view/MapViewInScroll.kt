package com.cc.viewpager101.view

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import com.google.android.gms.maps.MapView


class MapViewInScroll @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : MapView(context, attrs, defStyleAttr) {




    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        /**
         * Request all parents to relinquish the touch events
         */
        parent.requestDisallowInterceptTouchEvent(true)
        return super.dispatchTouchEvent(ev)
    }

    /*override fun onTouchEvent(event: MotionEvent?): Boolean {


        val action = event?.action
        action.let {
            when (action) {
                MotionEvent.ACTION_DOWN -> {
                    this.parent.requestDisallowInterceptTouchEvent(true)
                }
                MotionEvent.ACTION_UP -> {
                    this.parent.requestDisallowInterceptTouchEvent(false)
                }
            }
        }

        super.onTouchEvent(event)
        return true
    }*/

    /*override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        parent.requestDisallowInterceptTouchEvent(true)
        return super.dispatchTouchEvent(ev)
    }*/

}