package com.jehutyno.blablacarmvvm.injection

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.preference.PreferenceManager
import android.support.v7.app.AppCompatActivity
import com.jehutyno.blablacarmvvm.ui.trips.TripsViewModel

class ViewModelFactory(private val activity: AppCompatActivity): ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TripsViewModel::class.java)) {
            val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(activity)
            @Suppress("UNCHECKED_CAST")
            return TripsViewModel(sharedPreferences) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}