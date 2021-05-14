package com.cc.viewpager101.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [Case::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class GoogleMapDatabase : RoomDatabase() {

    abstract fun caseDao():CaseDao
    //abstract var caseDao:CaseDao

    companion object {
        //the instance
        @Volatile private var INSTANCE : GoogleMapDatabase? = null


        //get instance
        fun getInstance(context: Context) : GoogleMapDatabase {
            synchronized(this) {

                var instance = INSTANCE
                if (instance == null) {
                    instance = buildInstance(context)
                    INSTANCE = instance
                }
                return instance

            }
        }

        //build instance
        private fun buildInstance (context: Context) : GoogleMapDatabase {
            return  Room.databaseBuilder(context.applicationContext, GoogleMapDatabase::class.java, "dataBaseName")
                .fallbackToDestructiveMigration()
                .build()
        }



    }

}