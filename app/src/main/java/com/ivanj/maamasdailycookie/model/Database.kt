package com.ivanj.maamasdailycookie.model

import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@androidx.room.Database(entities = [CookieModelClassItem::class, CartModel::class, LocationModel::class, PaymentModel::class], version = 1)
abstract class Database : RoomDatabase() {
    abstract fun cookieDao(): CookieDao

    abstract fun cartDao(): CartDao

    abstract fun locationDao(): LocationDao

    abstract fun paymentDao(): PaymentDao
}

