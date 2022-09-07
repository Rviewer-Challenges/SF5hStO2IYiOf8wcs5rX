package com.esaudev.hackathonmoure.domain.usecase

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import com.esaudev.hackathonmoure.data.remote.Constants
import javax.inject.Inject

class GetSenderIdUseCase @Inject constructor(
    private val sharedPrefs: SharedPreferences
) {

    operator fun invoke(): String {
        return sharedPrefs.getString(Constants.ENCRYPTED_SHARED_PREFERENCES_NAME, "") ?: ""
    }

}