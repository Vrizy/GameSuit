package com.vrizy.gamesuit

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.vrizy.gamesuit.content.IntroductionActivity
import com.vrizy.gamesuit.databinding.ActivityMainBinding
import com.vrizy.gamesuit.databinding.DialogLayoutBinding

class MainActivity : AppCompatActivity() {

    private var namePlayer: String = "-"
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        namePlayer = intent.getStringExtra(KEY_NAME).toString()
        setUpAction()
        setUpView()
    }

    private fun setUpView() {
        binding.apply {
            tvPlayerOne.text = namePlayer
            Snackbar.make(binding.root, "Selamat Datang $namePlayer", Snackbar.LENGTH_INDEFINITE)
                .setAction("Tutup") {
                    Toast.makeText(
                        this@MainActivity,
                        "Selamat Bermain $namePlayer",
                        Toast.LENGTH_LONG
                    ).show()
                }.show()
        }
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
                    dialogSetUp(DRAW)
                    Toast.makeText(this@MainActivity, "CPU memilih Batu", Toast.LENGTH_SHORT).show()

                } else if (computerMove == 2) {
                    setComputerSelection(PAPER)
                    resultSetUp(PAPER)
                    tvResult.text = getString(R.string.player_two_win)
                    tvResult.setTextColor(Color.WHITE)
                    tvResult.textSize = 35F
                    dialogSetUp(CPU_WIN)
                    Toast.makeText(this@MainActivity, "CPU memilih Kertas", Toast.LENGTH_SHORT)
                        .show()

                } else {
                    setComputerSelection(SCISSOR)
                    resultSetUp(STONE)
                    tvResult.text = getString(R.string.player_one_win)
                    tvResult.setTextColor(Color.WHITE)
                    tvResult.textSize = 35F
                    dialogSetUp(PLAYER_WIN)
                    Toast.makeText(this@MainActivity, "CPU memilih Gunting", Toast.LENGTH_SHORT)
                        .show()
                }
            }

            paperPlayer.setOnClickListener {
                setPlayerSelection(PAPER)
                val computerMove = (1..3).random()

                if (computerMove == 1) {
                    setComputerSelection(STONE)
                    resultSetUp(PAPER)
                    tvResult.text = getString(R.string.player_one_win)
                    tvResult.setTextColor(Color.WHITE)
                    tvResult.textSize = 35F
                    dialogSetUp(PLAYER_WIN)
                    Toast.makeText(this@MainActivity, "CPU memilih Batu", Toast.LENGTH_SHORT).show()


                } else if (computerMove == 2) {
                    setComputerSelection(PAPER)
                    resultSetUp(DRAW)
                    tvResult.text = getString(R.string.draw)
                    tvResult.setTextColor(Color.WHITE)
                    tvResult.textSize = 35F
                    dialogSetUp(DRAW)
                    Toast.makeText(this@MainActivity, "CPU memilih Kertas", Toast.LENGTH_SHORT)
                        .show()

                } else {
                    setComputerSelection(SCISSOR)
                    resultSetUp(SCISSOR)
                    tvResult.text = getString(R.string.player_two_win)
                    tvResult.setTextColor(Color.WHITE)
                    tvResult.textSize = 35F
                    dialogSetUp(CPU_WIN)
                    Toast.makeText(this@MainActivity, "CPU memilih Gunting", Toast.LENGTH_SHORT)
                        .show()
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
                    dialogSetUp(CPU_WIN)
                    Toast.makeText(this@MainActivity, "CPU memilih Batu", Toast.LENGTH_SHORT).show()

                } else if (computerMove == 2) {
                    setComputerSelection(PAPER)
                    resultSetUp(SCISSOR)
                    tvResult.text = getString(R.string.player_one_win)
                    tvResult.setTextColor(Color.WHITE)
                    tvResult.textSize = 35F
                    dialogSetUp(PLAYER_WIN)
                    Toast.makeText(this@MainActivity, "CPU memilih Kertas", Toast.LENGTH_SHORT)
                        .show()

                } else {
                    setComputerSelection(SCISSOR)
                    resultSetUp(DRAW)
                    tvResult.text = getString(R.string.draw)
                    tvResult.setTextColor(Color.WHITE)
                    tvResult.textSize = 35F
                    dialogSetUp(DRAW)
                    Toast.makeText(this@MainActivity, "CPU memilih Gunting", Toast.LENGTH_SHORT)
                        .show()
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
                Toast.makeText(this@MainActivity, "Permainan telah di reset", Toast.LENGTH_SHORT)
                    .show()
            }
            btnClose.setOnClickListener {
                val intent = Intent(this@MainActivity, MenuScreen::class.java)
                intent.putExtra(MenuScreen.KEY_NAME, namePlayer)
                startActivity(intent)
            }
        }
    }


    private fun dialogSetUp(nameData: String) {
        val layoutInflater = LayoutInflater.from(this)
        val bindingFragment: DialogLayoutBinding =
            DialogLayoutBinding.inflate(layoutInflater)
        val dialogView = bindingFragment.root
        val alert = AlertDialog.Builder(this)
        alert.apply {
            setView(dialogView)
            setCancelable(false)
        }
        val alertDialog = alert.create()
        alertDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        bindingFragment.apply {
            btnTryagain.setOnClickListener {
                alertDialog.dismiss()
            }
            btnBacktomenu.setOnClickListener {
                val intent = Intent(this@MainActivity, MenuScreen::class.java)
                intent.putExtra(MenuScreen.KEY_NAME, namePlayer)
                startActivity(intent)
            }
            when (nameData) {
                PLAYER_WIN -> {
                    tvMessagesDialog.text = "$namePlayer Menang!"
                    alertDialog.show()
                }
                CPU_WIN -> {
                    tvMessagesDialog.text = "CPU Menang!"
                    alertDialog.show()
                }
                DRAW -> {
                    tvMessagesDialog.text = "SERI!"
                    alertDialog.show()
                }
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
        const val KEY_NAME = "KEYWORD NAME"
        const val PLAYER_WIN = "PLAYER WIN"
        const val CPU_WIN = "CPU WIN"
    }
}