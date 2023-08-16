package com.ivanj.maamasdailycookie.model

import androidx.room.Dao
import androidx.room.Ignore
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface CookieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCookie(cookie: CookieModelClassItem)

    @Query("SELECT * FROM cookieModelClassItem")
    fun getAll(): List<CookieModelClassItem>
}