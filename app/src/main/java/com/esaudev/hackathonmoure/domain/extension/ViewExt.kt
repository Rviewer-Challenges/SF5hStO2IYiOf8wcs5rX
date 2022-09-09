package com.esaudev.hackathonmoure.domain.extension

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.view.View

fun View.show() {
    this.visibility = View.VISIBLE
}

fun View.gone() {
    this.visibility = View.GONE
}

/** Set the View visibility to VISIBLE and eventually animate the View alpha till 100% */
fun View.showWithFade(animate: Boolean = true) {
    if (animate) {
        animate().alpha(1f).setDuration(300).setListener(object : AnimatorListenerAdapter() {
            override fun onAnimationStart(animation: Animator) {
                super.onAnimationStart(animation)
                visibility = View.VISIBLE
            }
        })
    } else {
        visibility = View.VISIBLE
    }
}

/** Set the View visibility to GONE and eventually animate view alpha till 0% */
fun View.goneWithFade(animate: Boolean = true) {
    hideFaded(View.GONE, animate)
}

private fun View.hideFaded(hidingStrategy: Int, animate: Boolean = true) {
    if (animate) {
        animate().alpha(0.0f).setDuration(300).setListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator) {
                super.onAnimationEnd(animation)
                visibility = View.GONE
            }
        })
    } else {
        visibility = View.GONE
    }
}