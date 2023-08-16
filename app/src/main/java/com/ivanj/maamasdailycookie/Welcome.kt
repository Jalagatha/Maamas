package com.ivanj.maamasdailycookie

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class Welcome : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)
        val welcome: Button = findViewById(R.id.btnWelcome)

        welcome.setOnClickListener {
            startActivity(Intent(this, MainHomeActivity::class.java))
            finish()
        }

    }
}