package com.esaudev.hackathonmoure.domain.model

import androidx.annotation.DrawableRes

data class SingleContentTopic(
    val title: String,
    val description: String,
    @DrawableRes val iconRes: Int,
)