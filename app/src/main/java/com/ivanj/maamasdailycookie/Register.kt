package com.ivanj.maamasdailycookie

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.ivanj.maamasdailycookie.databinding.ActivityRegisterBinding

class Register : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLoginBtnBtn.setOnClickListener {


            startActivity(Intent(this@Register, Login::class.java))


        }

        binding.apply {
            binding.btnRegisterB.setOnClickListener {
                startActivity(Intent(this@Register, Login::class.java))
            }
        }

    }
}