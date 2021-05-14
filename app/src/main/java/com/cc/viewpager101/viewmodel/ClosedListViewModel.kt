package com.cc.viewpager101.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.cc.viewpager101.model.Case
import com.cc.viewpager101.model.GoogleMapDatabase

class ClosedListViewModel(application: Application) : AndroidViewModel(application) {

    private var closedCases : LiveData<List<Case>>

    init {
        val database = GoogleMapDatabase.getInstance(application)
        closedCases = database.caseDao().getCases(true)
    }

    fun getAllClosedCases () :LiveData<List<Case>> {
        return closedCases
    }

}