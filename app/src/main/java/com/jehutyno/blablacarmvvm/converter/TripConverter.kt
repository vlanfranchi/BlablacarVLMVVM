package com.jehutyno.blablacarmvvm.converter

import com.jehutyno.blablacarmvvm.model.Trip
import com.jehutyno.blablacarmvvm.ui.trips.TripItem

object TripConverter : Converter<Trip, TripItem> {

    override fun convert(input: Trip): TripItem {
       return TripItem(
           departureTime = input.departure_date,
           locations = input.locations_to_display,
           price = input.price.string_value,
           userName = input.user.display_name,
           userPictureUrl = input.user.picture
       )
    }
}