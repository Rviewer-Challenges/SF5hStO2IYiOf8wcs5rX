package com.esaudev.hackathonmoure.domain.extension

import android.app.Activity
import android.content.Context
import android.net.Uri
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.browser.customtabs.CustomTabsIntent
import com.esaudev.hackathonmoure.R

/**
 * Returns Dps from Pixels
 * @param px
 */
fun Context.pxToDp(px: Int): Int {
    return (px / resources.displayMetrics.density).toInt()
}

/**
 * Returns Pixels from Dps
 * @param dp
 */
fun Context.dpToPx(dp: Int): Int {
    return (dp * resources.displayMetrics.density).toInt()
}

fun Context.hideKeyboard(view: View) {
    val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
}

fun Context.openChromeTab(url: String) {
    val customTab = CustomTabsIntent.Builder()
        .build()
    customTab.launchUrl(this, Uri.parse(url))
}