package com.esaudev.hackathonmoure.domain.model

import androidx.annotation.DrawableRes

data class OnBoardingPage(
    val title: String,
    val subtitle: String,
    @DrawableRes val image: Int
)
