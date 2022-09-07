package com.esaudev.hackathonmoure.domain.usecase

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import com.esaudev.hackathonmoure.data.remote.Constants
import java.util.*
import javax.inject.Inject

class SaveSenderIdUseCase @Inject constructor(
    private val sharedPrefs: SharedPreferences
) {

    operator fun invoke() {
        sharedPrefs.edit().putString(Constants.ENCRYPTED_SHARED_PREFERENCES_NAME, UUID.randomUUID().toString()).apply()
    }

}