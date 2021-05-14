package com.cc.viewpager101.model

import androidx.room.TypeConverter
import com.google.android.gms.maps.model.LatLng
import com.google.gson.Gson

class Converters {

    @TypeConverter
    fun jsonStringToCase(string: String?): Case  = Gson().fromJson(string,Case::class.java)

    @TypeConverter
    fun itemEntityToString(obj: Case?): String =Gson().toJson(obj)


    @TypeConverter
    fun jsonStringToLatLng(string: String?): LatLng = Gson().fromJson(string,LatLng::class.java)

    @TypeConverter
    fun itemEntityToString(obj: LatLng?): String =Gson().toJson(obj)
}