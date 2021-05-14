package com.cc.viewpager101.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.android.gms.maps.model.LatLng

@Entity(tableName = "CaseTableName")
data class Case (


    val caseTitle: String,
    @PrimaryKey
    val caseNumber: Int,
    val latLong: LatLng,
    var solved: Boolean
)