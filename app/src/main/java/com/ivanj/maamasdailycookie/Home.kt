package com.ivanj.maamasdailycookie

import android.annotation.SuppressLint
import android.content.Intent
import android.icu.lang.UCharacter.VerticalOrientation
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ivanj.maamasdailycookie.adapters.CookieAdapter
import com.ivanj.maamasdailycookie.adapters.CookieModel
import com.ivanj.maamasdailycookie.adapters.FastCategoryAdapter
import com.ivanj.maamasdailycookie.adapters.FastView


class Home : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        val idCategory:ImageView=findViewById(R.id.idCategory)

        idCategory.setOnClickListener {

        }

        val idAccount:ImageView=findViewById(R.id.idContacts)

      idCategory.setOnClickListener {
            val intent=Intent(this,SignUp::class.java)
            startActivity(intent)
        }



        val list=ArrayList<CookieModel>()
        list.add(CookieModel(1,"Small Size",R.drawable.cookie))
        list.add(CookieModel(2,"Large Size",R.drawable.cookie2))
        list.add(CookieModel(3,"Medium Size",R.drawable.cookie))
        list.add(CookieModel(4,"Large Size",R.drawable.cookie2))
        list.add(CookieModel(5,"Medium Size",R.drawable.cookie))
        createRecyclerView(list)
        val listCat=ArrayList<FastView>()
        listCat.add(FastView(1,"Small Size",R.drawable.cookie))
        listCat.add(FastView(2,"Small Size",R.drawable.cookie2))
        listCat.add(FastView(3,"Small Size",R.drawable.cookie))
        listCat.add(FastView(4,"Small Size",R.drawable.cookie2))
        createRecyclerView2(listCat)
    }

    private fun createRecyclerView2(listCat: ArrayList<FastView>) {
        val adapter=FastCategoryAdapter(this,listCat)
        val recycler:RecyclerView=findViewById(R.id.view2)
        recycler.adapter=FastCategoryAdapter(this,listCat)
        recycler.layoutManager=LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
    }

    private fun createRecyclerView(list:ArrayList<CookieModel>) {
    val adapter=CookieAdapter(this,list)
        val recycler:RecyclerView=findViewById(R.id.view1)
       recycler.adapter=CookieAdapter(this,list)
        recycler.layoutManager=LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)

    }


}