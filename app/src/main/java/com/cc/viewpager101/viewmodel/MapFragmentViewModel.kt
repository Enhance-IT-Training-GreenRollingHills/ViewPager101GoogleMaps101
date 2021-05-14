package com.cc.viewpager101.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.cc.viewpager101.model.Case
import com.cc.viewpager101.model.GoogleMapDatabase
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions

class MapFragmentViewModel(application: Application) : AndroidViewModel(application) {

    private var allCases : LiveData<List<Case>>

    private var markers : MutableLiveData<List<MarkerOptions>> = MutableLiveData()

    private val database = GoogleMapDatabase.getInstance(application)

    init {
        allCases = database.caseDao().getAllCases()
    }

    fun updateMarkers (list:List<Case>) {
        val newMarkers = mutableListOf<MarkerOptions>()
        for (case in list) {
            val marker = MarkerOptions()
            marker.position(case.latLong)
            marker.title(case.caseTitle)
            val color = if (case.solved) BitmapDescriptorFactory.HUE_GREEN else BitmapDescriptorFactory.HUE_RED
            marker.icon(BitmapDescriptorFactory.defaultMarker(color))
            newMarkers.add(marker)
        }

        markers.value = newMarkers
    }

    fun getAllCases() : LiveData<List<Case>> {
        return allCases
    }

    fun getMarkers () : LiveData<List<MarkerOptions>> {
        return markers
    }



}