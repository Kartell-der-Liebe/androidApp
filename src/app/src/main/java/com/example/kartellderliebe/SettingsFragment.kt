package com.example.kartellderliebe

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat

class SettingsFragment : PreferenceFragmentCompat() {

    @SuppressLint("ResourceType")
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.layout.fragment_settings, rootKey)
    }
}