package com.ivanj.maamasdailycookie

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ivanj.maamasdailycookie.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.apply {

            val s = binding.splash
            s.alpha = 0f
            s.animate().setDuration(3000).alpha(1f).withEndAction {
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
                val intent = Intent(this@MainActivity, Register::class.java)
                startActivity(intent)
                finish()
            }

        }


    }
}