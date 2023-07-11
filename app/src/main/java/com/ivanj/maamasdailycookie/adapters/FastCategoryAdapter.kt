package com.ivanj.maamasdailycookie.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ivanj.maamasdailycookie.R

class FastCategoryAdapter(val context: Context,val listCat:ArrayList<FastView>):RecyclerView.Adapter<FastCategoryAdapter.ViewHolder>() {
    class ViewHolder(view:View):RecyclerView.ViewHolder(view) {
        val categoryPic:ImageView=view.findViewById(R.id.picFastcategory)
        val categoryTitle:TextView=view.findViewById(R.id.categoryTitle)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
      val v=LayoutInflater.from(parent.context).inflate(R.layout.item_fast_delivery,parent,false)
   return ViewHolder(v)
    }

    override fun getItemCount(): Int {
       return listCat.size
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       holder.categoryTitle.text=listCat[position].categoryName
        holder.categoryPic.setImageResource(listCat[position].CategoryPic)
    }
}