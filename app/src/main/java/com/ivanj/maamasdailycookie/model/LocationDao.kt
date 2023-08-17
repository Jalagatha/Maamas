package com.ivanj.maamasdailycookie.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface LocationDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertLocation(locationModel: LocationModel)

    @Query("SELECT * FROM locationModel")
    fun getAll(): List<LocationModel>

    @Query("DELETE FROM locationModel WHERE lid = :lid")
    fun deleteLocation(lid: Int)

    @Update
    fun updateLocation(locationModel: LocationModel)
}