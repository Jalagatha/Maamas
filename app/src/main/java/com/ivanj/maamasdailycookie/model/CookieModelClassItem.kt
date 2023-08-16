package com.ivanj.maamasdailycookie.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CookieModelClassItem(
    @PrimaryKey
    val id: String,
    val description: String,
    val image: String,
    val name: String,
    val price: String,
    val rating: String
)

@Entity
data class CartModel(
    @PrimaryKey(autoGenerate = true)
    val cid: Int? = null,
    @Embedded val cookie: CookieModelClassItem,
    var quantity: Int = 0,
    var total:Int = 0
)
//{
//    fun increment() : Int {
//        return quantity + 1
//    }
//
//    fun decrement() : Int {
//        return quantity - 1
//    }
//}

//@Entity
//data class User(
//    @PrimaryKey val uid: Int,
//    @ColumnInfo(name = "first_name") val firstName: String?,
//    @ColumnInfo(name = "last_name") val lastName: String?
//)