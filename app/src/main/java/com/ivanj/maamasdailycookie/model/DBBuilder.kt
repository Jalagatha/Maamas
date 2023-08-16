package com.ivanj.maamasdailycookie.model

import android.content.Context
import androidx.room.Room

class DBBuilder(context: Context) {

    val db = Room.databaseBuilder(
        context,
        Database::class.java, "database-name"
    ).fallbackToDestructiveMigration().build()

}
