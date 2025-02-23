package com.example.test.presentation.utils

import android.animation.ValueAnimator
import android.view.View
import android.widget.TextView
import androidx.core.animation.doOnEnd

fun expandTextView(textView: TextView,doOnEnd:()->Unit) {


    textView.measure(
        View.MeasureSpec.makeMeasureSpec((textView.parent as View).width, View.MeasureSpec.AT_MOST),
        View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED)
    )
    val targetHeight = textView.measuredHeight

    textView.layoutParams.height = 0
    textView.visibility = View.VISIBLE

    val animator = ValueAnimator.ofInt(0, targetHeight).apply {
        duration = 300
        addUpdateListener { animation ->
            textView.layoutParams.height = animation.animatedValue as Int
            textView.requestLayout()
        }
        doOnEnd { doOnEnd() }
    }
    animator.start()
}

fun collapseTextView(textView: TextView,doOnEnd:()->Unit) {

    val initialHeight = textView.height

    val animator = ValueAnimator.ofInt(initialHeight, 0).apply {
        duration = 200
        addUpdateListener { animation ->
            textView.layoutParams.height = animation.animatedValue as Int
            textView.requestLayout()
        }
        doOnEnd {
            doOnEnd()
            textView.visibility = View.GONE
        }
    }
    animator.start()
}