package com.ivanj.maamasdailycookie.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface PaymentDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPayment(paymentModel: PaymentModel)

    @Query("SELECT * FROM paymentModel")
    fun getAll(): List<PaymentModel>

    @Query("DELETE FROM paymentModel WHERE pid = :pid")
    fun deletePayment(pid: Int)

    @Update
    fun updatePayment(paymentModel: PaymentModel)
}