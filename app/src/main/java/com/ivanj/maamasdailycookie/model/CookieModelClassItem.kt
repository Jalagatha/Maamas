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

@Entity
data class LocationModel(
    @PrimaryKey(autoGenerate = true)
    val lid: Int? = null,
    var name: String = "",
    var address:String = "",
    var city:String = "",
    var phone:String = "",
    var note:String = ""
)

@Entity
data class PaymentModel(
    @PrimaryKey(autoGenerate = true)
    val pid: Int? = null,
    var name: String = "",
    var number:String = "",
    var date:String = "",
    var ccv:String = "",
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