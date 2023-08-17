package com.ivanj.maamasdailycookie

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.ivanj.maamasdailycookie.databinding.ActivityLoginBinding

class Login : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            binding.login.setOnClickListener {
                startActivity(Intent(this@Login, Login::class.java))
                finish()
            }



            binding.LoginBtn.setOnClickListener {
                startActivity(Intent(this@Login, Welcome::class.java))
            }
        }


    }
}