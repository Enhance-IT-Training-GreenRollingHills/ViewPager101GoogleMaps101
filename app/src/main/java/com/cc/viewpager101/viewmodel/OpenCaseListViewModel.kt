package com.cc.viewpager101.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.cc.viewpager101.model.Case
import com.cc.viewpager101.model.GoogleMapDatabase
import kotlinx.coroutines.launch

class OpenCaseListViewModel(application: Application) : AndroidViewModel(application) {

    private var openCases : LiveData<List<Case>>
    private var database : GoogleMapDatabase = GoogleMapDatabase.getInstance(getApplication())

    init {
        openCases = database.caseDao().getCases(false)

    }

    fun getOpenCases () : LiveData<List<Case>> {
        return openCases
    }


    suspend fun updateCase(data:Case) {
        database.caseDao().updateCase(data)
    }

}