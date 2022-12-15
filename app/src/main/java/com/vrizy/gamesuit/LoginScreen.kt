package com.vrizy.gamesuit

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.vrizy.gamesuit.content.IntroductionActivity
import com.vrizy.gamesuit.databinding.ActivityLoginScreenBinding
import kotlinx.android.synthetic.main.activity_login_screen.*

class LoginScreen : AppCompatActivity() {
    lateinit var binding: ActivityLoginScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        btn_play.setOnClickListener {
            val intent = Intent(this, IntroductionActivity::class.java)
            startActivity(intent)
        }
    }
}