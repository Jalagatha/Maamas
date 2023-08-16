package com.ivanj.maamasdailycookie.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ivanj.maamasdailycookie.R
import com.ivanj.maamasdailycookie.model.CartModel
import com.ivanj.maamasdailycookie.model.CookieModelClassItem
import com.ivanj.maamasdailycookie.model.DBBuilder
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class CookieAdapter(val context: Context, val list: ArrayList<CookieModelClassItem>) :
    RecyclerView.Adapter<CookieAdapter.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val itemName: TextView = view.findViewById(R.id.tv_item_name)
        val categoryPic: ImageView = view.findViewById(R.id.iv_image)
        val rating: ImageView = view.findViewById(R.id.iv_favorites)
        val price: TextView = view.findViewById(R.id.tv_price)
        val addItem: ImageView = view.findViewById(R.id.iv_add)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val cookie =
            LayoutInflater.from(parent.context).inflate(R.layout.cookie_item_layout, parent, false)


        return ViewHolder(cookie)
    }

    override fun getItemCount(): Int {
       return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val db = DBBuilder(context)
        holder.itemName.text = list[position].name
//        holder.rating.setImageResource()
        holder.price.text = list[position].price


        Glide.with(context)
            .load(list[position].image)
            .into(holder.categoryPic)
        holder.addItem.setOnClickListener {

            val scope = CoroutineScope(Dispatchers.IO)
            scope.launch {
                val model = CartModel(null, list[position], 1)
                db.db.cartDao().insertCookie(model)
            }

        }
    }


}