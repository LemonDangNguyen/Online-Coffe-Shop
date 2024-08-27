package com.example.onlinecoffeeshop.Activity

import android.content.Intent
import android.os.Bundle
import com.example.onlinecoffeeshop.databinding.ActivityIntroBinding

class IntroActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        lateinit var binding: ActivityIntroBinding
        super.onCreate(savedInstanceState)
        binding = ActivityIntroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.startBtn.setOnClickListener{
            startActivity(Intent(this@IntroActivity, MainActivity::class.java))
        }

    }
}