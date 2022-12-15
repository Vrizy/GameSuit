package com.vrizy.gamesuit

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.vrizy.gamesuit.databinding.ActivityMenuScreenBinding

class MenuScreen : AppCompatActivity() {
    lateinit var binding: ActivityMenuScreenBinding
    private var namePlayer: String = "-"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMenuScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        namePlayer = intent.getStringExtra(MainActivity.KEY_NAME).toString()
        setUpView()
        setUpAction()
    }

    @SuppressLint("SetTextI18n")
    private fun setUpView() {
        binding.apply {
            tvPlayerVsPlayer.text = "$namePlayer vs Pemain"
            tvPlayerVsCpu.text = "$namePlayer vs CPU"
        }
    }


    private fun setBackgroundSelector(typeSelection: String) {
        binding.apply {
            when (typeSelection) {
                PLAYER_VS_PLAYER -> {
                    ivPlayerVsPlayer.setBackgroundResource(R.drawable.bg_selector)
                    ivPlayerVsCpu.setBackgroundResource(0)
                }
                PLAYER_VS_CPU -> {
                    ivPlayerVsCpu.setBackgroundResource(R.drawable.bg_selector)
                    ivPlayerVsPlayer.setBackgroundResource(0)
                }
            }
        }
    }

    private fun setUpAction() {
        val intent = Intent(this, MainActivity::class.java)
//        val name = intent.getStringExtra(KEY_NAME).toString()
        binding.apply {
            ivPlayerVsPlayer.setOnClickListener {
                setBackgroundSelector(PLAYER_VS_PLAYER)
            }
            ivPlayerVsCpu.setOnClickListener {
                setBackgroundSelector(PLAYER_VS_CPU)
                intent.putExtra(KEY_NAME, namePlayer)
                startActivity(intent)
            }
        }
    }

    companion object {
        const val KEY_NAME = "KEYWORD NAME"
        const val PLAYER_VS_PLAYER = "PLAYER VS PLAYER"
        const val PLAYER_VS_CPU = "PLAYER VS CPU"
    }
}