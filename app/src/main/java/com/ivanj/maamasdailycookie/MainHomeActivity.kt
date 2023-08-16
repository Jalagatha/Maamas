package com.ivanj.maamasdailycookie

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.google.android.material.bottomnavigation.BottomNavigationView

import com.ivanj.maamasdailycookie.retrofit.Api
import com.ivanj.maamasdailycookie.retrofit.RetrofitInstanceObject

class MainHomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main_home)

        val cookieApi = RetrofitInstanceObject.getInstance().create(Api::class.java)


        //val bottomNavigation: BottomNavigationView =findViewById(R.id.btm_nav)
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.hostFragment) as NavHostFragment
        val navController = navHostFragment.navController
        //val navController: NavController = Navigation.findNavController(this, R.id.hostFragment)
        // NavigationUI.setupWithNavController(bottomNavigation,navController)
        navController.navigate(R.id.itemsFragment)


    }
}