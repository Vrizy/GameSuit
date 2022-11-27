package com.vrizy.gamesuit

import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.marginRight
import com.vrizy.gamesuit.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        setUpAction()

    }

    private fun setUpAction() {
        binding.apply {
            stonePlayer.setOnClickListener {
                setPlayerSelection(STONE)
                val computerMove = (1..3).random()

                if (computerMove == 1) {
                    setComputerSelection(STONE)
                    resultSetUp(DRAW)
                    tvResult.text = getString(R.string.draw)
                    tvResult.setTextColor(Color.WHITE)
                    tvResult.textSize = 35F

                } else if (computerMove == 2) {
                    setComputerSelection(PAPER)
                    resultSetUp(PAPER)
                    tvResult.text = getString(R.string.player_two_win)
                    tvResult.setTextColor(Color.WHITE)
                    tvResult.textSize = 35F

                } else {
                    setComputerSelection(SCISSOR)
                    resultSetUp(SCISSOR)
                    tvResult.text = getString(R.string.player_one_win)
                    tvResult.setTextColor(Color.WHITE)
                    tvResult.textSize = 35F

                }
            }

            paperPlayer.setOnClickListener {
                setPlayerSelection(PAPER)
                val computerMove = (1..3).random()

                if (computerMove == 1) {
                    setComputerSelection(STONE)
                    resultSetUp(STONE)
                    tvResult.text = getString(R.string.player_one_win)
                    tvResult.setTextColor(Color.WHITE)
                    tvResult.textSize = 35F

                } else if (computerMove == 2) {
                    setComputerSelection(PAPER)
                    resultSetUp(DRAW)
                    tvResult.text = getString(R.string.draw)
                    tvResult.setTextColor(Color.WHITE)
                    tvResult.textSize = 35F

                } else {
                    setComputerSelection(SCISSOR)
                    resultSetUp(SCISSOR)
                    tvResult.text = getString(R.string.player_two_win)
                    tvResult.setTextColor(Color.WHITE)
                    tvResult.textSize = 35F
                }
            }

            scissorPlayer.setOnClickListener {
                setPlayerSelection(SCISSOR)
                val computerMove = (1..3).random()

                if (computerMove == 1) {
                    setComputerSelection(STONE)
                    resultSetUp(STONE)
                    tvResult.text = getString(R.string.player_two_win)
                    tvResult.setTextColor(Color.WHITE)
                    tvResult.textSize = 35F

                } else if (computerMove == 2) {
                    setComputerSelection(PAPER)
                    resultSetUp(PAPER)
                    tvResult.text = getString(R.string.player_one_win)
                    tvResult.setTextColor(Color.WHITE)
                    tvResult.textSize = 35F
                    tvResult.marginRight

                } else {
                    setComputerSelection(SCISSOR)
                    resultSetUp(DRAW)
                    tvResult.text = getString(R.string.draw)
                    tvResult.setTextColor(Color.WHITE)
                    tvResult.textSize = 35F
                }
            }
            reset.setOnClickListener {
                scissorPlayer.setBackgroundResource(0)
                stonePlayer.setBackgroundResource(0)
                paperPlayer.setBackgroundResource(0)
                scissorCpu.setBackgroundResource(0)
                stoneCpu.setBackgroundResource(0)
                paperCpu.setBackgroundResource(0)
                tvResult.setBackgroundResource(0)
                tvResult.text = getString(R.string.vs)
                tvResult.setTextColor(Color.RED)
                tvResult.textSize = 65F
            }
        }
    }


    private fun resultSetUp(resultGame: String) {
        binding.apply {
            when (resultGame) {
                STONE -> {
                    tvResult.setBackgroundResource(R.drawable.bg_selector)
                    tvResult.setBackgroundResource(R.color.light_green)
                }
                PAPER -> {
                    tvResult.setBackgroundResource(R.drawable.bg_selector)
                    tvResult.setBackgroundResource(R.color.light_green)
                }
                SCISSOR -> {
                    tvResult.setBackgroundResource(R.drawable.bg_selector)
                    tvResult.setBackgroundResource(R.color.light_green)
                }
                DRAW -> {
                    tvResult.setBackgroundResource(R.drawable.bg_selector)
                    tvResult.setBackgroundResource(R.color.light_blue)
                }
            }
        }
    }


    private fun setPlayerSelection(typeSelection: String) {
        binding.apply {
            when (typeSelection) {
                STONE -> {
                    stonePlayer.setBackgroundResource(R.drawable.bg_selector)
                    scissorPlayer.setBackgroundResource(0)
                    paperPlayer.setBackgroundResource(0)
                }
                PAPER -> {
                    paperPlayer.setBackgroundResource(R.drawable.bg_selector)
                    scissorPlayer.setBackgroundResource(0)
                    stonePlayer.setBackgroundResource(0)
                }
                SCISSOR -> {
                    scissorPlayer.setBackgroundResource(R.drawable.bg_selector)
                    stonePlayer.setBackgroundResource(0)
                    paperPlayer.setBackgroundResource(0)
                }
            }
        }
    }

    private fun setComputerSelection(typeSelection: String) {
        binding.apply {
            when (typeSelection) {
                STONE -> {
                    stoneCpu.setBackgroundResource(R.drawable.bg_selector)
                    scissorCpu.setBackgroundResource(0)
                    paperCpu.setBackgroundResource(0)
                }
                PAPER -> {
                    paperCpu.setBackgroundResource(R.drawable.bg_selector)
                    scissorCpu.setBackgroundResource(0)
                    stoneCpu.setBackgroundResource(0)
                }
                SCISSOR -> {
                    scissorCpu.setBackgroundResource(R.drawable.bg_selector)
                    stoneCpu.setBackgroundResource(0)
                    paperCpu.setBackgroundResource(0)
                }
            }
        }
    }


    companion object {
        const val SCISSOR = "SCISSOR"
        const val STONE = "STONE"
        const val PAPER = "PAPER"
        const val DRAW = "DRAW"
    }


}