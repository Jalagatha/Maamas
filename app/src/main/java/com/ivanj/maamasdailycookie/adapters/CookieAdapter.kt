package com.ivanj.maamasdailycookie.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.ivanj.maamasdailycookie.R


class CookieAdapter(val context:Context,val list:ArrayList<CookieModel>):RecyclerView.Adapter<CookieAdapter.ViewHolder>(){
        class ViewHolder(view:View):RecyclerView.ViewHolder(view) {
        val categoryName: TextView =view.findViewById(R.id.categoryName)
        val categoryPic: ImageView =view.findViewById(R.id.categoryPic)
        val container: ConstraintLayout =view.findViewById(R.id.categoryContainer)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val cookie=LayoutInflater.from(parent.context).inflate(R.layout.view_holder_category,parent,false)
        return ViewHolder(cookie)
    }

    override fun getItemCount(): Int {
       return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       holder.categoryName.text=list[position].categoryName
        holder.categoryPic.setImageResource(list[position].CategoryPic)
    }


}