package com.barbieri.mini_tumblr_app.ViewPager

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import androidx.viewpager.widget.ViewPager


class CustomViewPager: ViewPager  {

    var disable: Boolean? = false

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    override
    fun onInterceptTouchEvent(event: MotionEvent): Boolean {
        return if (this.disable!!) false else super.onInterceptTouchEvent(event)
    }

    override
    fun onTouchEvent(event: MotionEvent): Boolean {
        return if (this.disable!!) false else super.onTouchEvent(event)
    }

    fun disableScroll(disable: Boolean?) {
        //When disable = true not work the scroll and when disble = false work the scroll
        this.disable = disable
    }


}