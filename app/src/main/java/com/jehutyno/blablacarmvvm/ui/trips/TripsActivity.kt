package com.jehutyno.blablacarmvvm.ui.trips

import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.jehutyno.blablacarmvvm.R
import com.jehutyno.blablacarmvvm.databinding.TripsActivityBinding
import com.jehutyno.blablacarmvvm.injection.ViewModelFactory

class TripsActivity : AppCompatActivity() {

    private lateinit var binding: TripsActivityBinding
    private lateinit var viewModel: TripsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.trips_activity)
        binding.tripList.layoutManager = LinearLayoutManager(this)

        viewModel = ViewModelProviders.of(this, ViewModelFactory(this)).get(TripsViewModel::class.java)

        binding.viewModel = viewModel
    }
}