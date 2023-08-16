package com.ivanj.maamasdailycookie.model

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface CartDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCookie(cartModel: CartModel)

    @Query("SELECT * FROM cartModel")
    fun getAll(): List<CartModel>

    @Query("DELETE FROM cartModel WHERE cid = :cid")
    fun deleteCookie(cid: Int)

    @Update
    fun updateCookie(cartModel: CartModel)
}