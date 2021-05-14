package com.cc.viewpager101.model

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface CaseDao {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllCases(vararg cases: Case)

    @Query("SELECT * FROM CaseTableName")
    fun getAllCases() : LiveData<List<Case>>

    @Query("SELECT * FROM CaseTableName WHERE solved = :solved")
    fun getCases(solved:Boolean) : LiveData<List<Case>>


    //I tried a parameter name of "case" and it didn't work.  I changed it to
    //"parameter" and it works.  why?
    @Update
    suspend fun updateCase(parameter:Case)
}