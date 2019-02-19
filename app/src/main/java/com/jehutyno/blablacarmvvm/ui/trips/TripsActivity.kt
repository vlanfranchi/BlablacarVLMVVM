package com.jehutyno.blablacarmvvm.ui.trips

import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.jehutyno.blablacarmvvm.R
import com.jehutyno.blablacarmvvm.databinding.TripsActivityBinding
import com.jehutyno.blablacarmvvm.injection.component.ViewModelFactory
import kotlinx.android.synthetic.main.trips_activity.*

class TripsActivity : AppCompatActivity() {

    companion object {
        private const val INTENT_DEPARTURE = "intent_departure"
        private const val INTENT_DESTINATION = "intent_destination"

        fun intent(context: Context, departure: String, destination: String): Intent {
            return Intent(context, TripsActivity::class.java).apply {
                putExtra(INTENT_DEPARTURE, departure)
                putExtra(INTENT_DESTINATION, destination)
            }
        }
    }

    private lateinit var binding: TripsActivityBinding
    private lateinit var viewModel: TripsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.trips_activity)
        binding.tripList.layoutManager = LinearLayoutManager(this)
        title = getString(R.string.results_title)
        val departure = intent.getStringExtra(INTENT_DEPARTURE)
        val destination = intent.getStringExtra(INTENT_DESTINATION)
        titleTv.text = getString(R.string.trips_title, departure, destination)

        viewModel = ViewModelProviders.of(this, ViewModelFactory(this)).get(TripsViewModel::class.java)
        viewModel.loadTrips(departure, destination)


        binding.viewModel = viewModel
    }
}