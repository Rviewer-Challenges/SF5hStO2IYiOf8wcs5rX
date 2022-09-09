package com.esaudev.hackathonmoure.domain.usecase

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import com.esaudev.hackathonmoure.data.remote.Constants
import javax.inject.Inject

class GetOnBoardingStatusUseCase @Inject constructor(
    private val sharedPrefs: SharedPreferences
) {

    operator fun invoke(): Boolean {
        return sharedPrefs.getBoolean(Constants.ON_BOARDING_SEEN, false) ?: false
    }

}