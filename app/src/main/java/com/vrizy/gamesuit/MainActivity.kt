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


            /*-------------SETTINGAN LOGIC PERMAINAN------------*/
            stonePlayer.setOnClickListener {
                setSelection(STONE_PLAYER)
                val computerMove = (1..3)
                val randomElement = computerMove.asSequence().shuffled().find { true }

                when (randomElement) {
                    1 -> {
                        setSelection(STONE_CPU)
                        resultSetUp(DRAW)
                        tvResult.text = getString(R.string.draw)
                        tvResult.setTextColor(Color.WHITE)
                        tvResult.textSize = 35F
                        dialogSetUp(DRAW)
                        Toast.makeText(this@MainActivity, "CPU Memilih Batu", Toast.LENGTH_SHORT)
                            .show()
                    }
                    2 -> {
                        setSelection(PAPER_CPU)
                        resultSetUp(PAPER)
                        tvResult.text = getString(R.string.player_two_win)
                        tvResult.setTextColor(Color.WHITE)
                        tvResult.textSize = 35F
                        dialogSetUp(CPU_WIN)
                        Toast.makeText(this@MainActivity, "CPU Memilih Kertas", Toast.LENGTH_SHORT)
                            .show()
                    }
                    3 -> {
                        setSelection(SCISSOR_CPU)
                        resultSetUp(STONE)
                        tvResult.text = getString(R.string.player_one_win)
                        tvResult.setTextColor(Color.WHITE)
                        tvResult.textSize = 35F
                        dialogSetUp(PLAYER_WIN)
                        Toast.makeText(this@MainActivity, "CPU Memilih Gunting", Toast.LENGTH_SHORT)
                            .show()
                    }
                }
            }

            paperPlayer.setOnClickListener {
                setSelection(PAPER_PLAYER)
                val computerMove = (1..3)
                val randomElement = computerMove.asSequence().shuffled().find { true }
                when (randomElement) {
                    1 -> {
                        setSelection(STONE_CPU)
                        resultSetUp(PAPER)
                        tvResult.text = getString(R.string.player_one_win)
                        tvResult.setTextColor(Color.WHITE)
                        tvResult.textSize = 35F
                        dialogSetUp(PLAYER_WIN)
                        Toast.makeText(this@MainActivity, "CPU Memilih Batu", Toast.LENGTH_SHORT)
                            .show()
                    }
                    2 -> {
                        setSelection(PAPER_CPU)
                        resultSetUp(DRAW)
                        tvResult.text = getString(R.string.draw)
                        tvResult.setTextColor(Color.WHITE)
                        tvResult.textSize = 35F
                        dialogSetUp(DRAW)
                        Toast.makeText(this@MainActivity, "CPU Memilih Kertas", Toast.LENGTH_SHORT)
                            .show()
                    }
                    3 -> {
                        setSelection(SCISSOR_CPU)
                        resultSetUp(SCISSOR)
                        tvResult.text = getString(R.string.player_two_win)
                        tvResult.setTextColor(Color.WHITE)
                        tvResult.textSize = 35F
                        dialogSetUp(CPU_WIN)
                        Toast.makeText(this@MainActivity, "CPU Memilih Gunting", Toast.LENGTH_SHORT)
                            .show()
                    }
                }
            }

            scissorPlayer.setOnClickListener {
                setSelection(SCISSOR_PLAYER)
                val computerMove = (1..3)
                val randomElement = computerMove.asSequence().shuffled().find { true }
                when (randomElement) {
                    1 -> {
                        setSelection(STONE_CPU)
                        resultSetUp(STONE)
                        tvResult.text = getString(R.string.player_two_win)
                        tvResult.setTextColor(Color.WHITE)
                        tvResult.textSize = 35F
                        dialogSetUp(CPU_WIN)
                        Toast.makeText(this@MainActivity, "CPU Memilih Batu", Toast.LENGTH_SHORT)
                            .show()
                    }
                    2 -> {
                        setSelection(PAPER_CPU)
                        resultSetUp(SCISSOR)
                        tvResult.text = getString(R.string.player_one_win)
                        tvResult.setTextColor(Color.WHITE)
                        tvResult.textSize = 35F
                        dialogSetUp(PLAYER_WIN)
                        Toast.makeText(this@MainActivity, "CPU Memilih Kertas", Toast.LENGTH_SHORT)
                            .show()
                    }
                    3 -> {
                        setSelection(SCISSOR_CPU)
                        resultSetUp(DRAW)
                        tvResult.text = getString(R.string.draw)
                        tvResult.setTextColor(Color.WHITE)
                        tvResult.textSize = 35F
                        dialogSetUp(DRAW)
                        Toast.makeText(this@MainActivity, "CPU Memilih Gunting", Toast.LENGTH_SHORT)
                            .show()
                    }
                }
            }

            /*-------------SETTINGAN TOMBOL RESET------------*/
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

            /*-------------SETTINGAN TOMBOL CLOSE------------*/
            btnClose.setOnClickListener {
                val intent = Intent(this@MainActivity, MenuScreen::class.java)
                intent.putExtra(MenuScreen.KEY_NAME, namePlayer)
                startActivity(intent)
            }
        }
    }

    /*-------------SETTINGAN DIALOG RESULT------------*/
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

    /*-------------SETTINGAN BACKGROUND RESULT------------*/
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

    /*-------------SETTINGAN BACKGROUND SELECTOR------------*/
    private fun setSelection(typeSelection: String) {
        binding.apply {
            when (typeSelection) {
                STONE_PLAYER -> {
                    stonePlayer.setBackgroundResource(R.drawable.bg_selector)
                    scissorPlayer.setBackgroundResource(0)
                    paperPlayer.setBackgroundResource(0)
                }
                PAPER_PLAYER -> {
                    paperPlayer.setBackgroundResource(R.drawable.bg_selector)
                    scissorPlayer.setBackgroundResource(0)
                    stonePlayer.setBackgroundResource(0)
                }
                SCISSOR_PLAYER -> {
                    scissorPlayer.setBackgroundResource(R.drawable.bg_selector)
                    stonePlayer.setBackgroundResource(0)
                    paperPlayer.setBackgroundResource(0)
                }
                STONE_CPU -> {
                    stoneCpu.setBackgroundResource(R.drawable.bg_selector)
                    scissorCpu.setBackgroundResource(0)
                    paperCpu.setBackgroundResource(0)
                }
                PAPER_CPU -> {
                    paperCpu.setBackgroundResource(R.drawable.bg_selector)
                    scissorCpu.setBackgroundResource(0)
                    stoneCpu.setBackgroundResource(0)
                }
                SCISSOR_CPU -> {
                    scissorCpu.setBackgroundResource(R.drawable.bg_selector)
                    stoneCpu.setBackgroundResource(0)
                    paperCpu.setBackgroundResource(0)
                }
            }
        }
    }

    companion object {
        const val STONE_PLAYER = "STONE PLAYER"
        const val STONE_CPU = "STONE CPU"
        const val PAPER_PLAYER = "PAPER PLAYER"
        const val PAPER_CPU = "PAPER CPU"
        const val SCISSOR_CPU = "SCISSOR CPU"
        const val SCISSOR_PLAYER = "SCISSOR_PLAYER"
        const val SCISSOR = "SCISSOR"
        const val STONE = "STONE"
        const val PAPER = "PAPER"
        const val DRAW = "DRAW"
        const val KEY_NAME = "KEYWORD NAME"
        const val PLAYER_WIN = "PLAYER WIN"
        const val CPU_WIN = "CPU WIN"
    }
}