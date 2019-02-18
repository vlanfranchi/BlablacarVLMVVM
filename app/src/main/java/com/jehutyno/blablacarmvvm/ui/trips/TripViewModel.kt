package com.jehutyno.blablacarmvvm.ui.trips

import android.arch.lifecycle.MutableLiveData
import com.jehutyno.blablacarmvvm.base.BaseViewModel

class TripViewModel: BaseViewModel() {

    val departureTime = MutableLiveData<String>()
    val locations = MutableLiveData<String>()
    val price = MutableLiveData<String>()
    val userName = MutableLiveData<String>()
    val userPicture = MutableLiveData<String>()

    fun bind(trip: TripItem){
        departureTime.value = trip.departureTime
        locations.value = trip.locations.joinToString(" - ")
        price.value = trip.price
        userName.value = trip.userName
        userPicture.value = trip.userPictureUrl
    }


}