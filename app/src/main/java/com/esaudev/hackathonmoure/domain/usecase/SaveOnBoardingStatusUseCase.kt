package com.esaudev.hackathonmoure.domain.usecase

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import com.esaudev.hackathonmoure.data.remote.Constants
import java.util.*
import javax.inject.Inject

class SaveOnBoardingStatusUseCase @Inject constructor(
    private val sharedPrefs: SharedPreferences
) {

    operator fun invoke(seen: Boolean = true) {
        sharedPrefs.edit().putBoolean(Constants.ON_BOARDING_SEEN, true).apply()
    }

}