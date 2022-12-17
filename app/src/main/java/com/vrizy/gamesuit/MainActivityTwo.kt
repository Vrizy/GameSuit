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
import com.vrizy.gamesuit.databinding.ActivityMainTwoBinding
import com.vrizy.gamesuit.databinding.DialogLayoutBinding

class MainActivityTwo : AppCompatActivity() {

    private var namePlayer: String = "-"
    private var setPlayerOne = ""
    private var setPlayerTwo = ""
    lateinit var binding: ActivityMainTwoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainTwoBinding.inflate(layoutInflater)
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
                        this@MainActivityTwo,
                        "Selamat Bermain $namePlayer",
                        Toast.LENGTH_LONG
                    ).show()
                }.show()
        }
    }

    private fun setUpAction() {
        binding.apply {

            /*-------------SETTINGAN PILIHAN PLAYER 1 dan PLAYER 2------------*/

            stonePlayerOne.setOnClickListener {
                setSelection(STONE_PLAYER_ONE)
                setPlayerOne = STONE
                if (setPlayerTwo.isNotEmpty()) {
                    setUpRules()
                }
            }

            scissorPlayerOne.setOnClickListener {
                setSelection(SCISSOR_PLAYER_ONE)
                setPlayerOne = SCISSOR
                if (setPlayerTwo.isNotEmpty()) {
                    setUpRules()
                }
            }

            paperPlayerOne.setOnClickListener {
                setSelection(PAPER_PLAYER_ONE)
                setPlayerOne = PAPER
                if (setPlayerTwo.isNotEmpty()) {
                    setUpRules()
                }
            }

            stonePlayerTwo.setOnClickListener {
                setSelection(STONE_PLAYER_TWO)
                setPlayerTwo = STONE
                if (setPlayerOne.isNotEmpty()) {
                    setUpRules()
                }
            }

            scissorPlayerTwo.setOnClickListener {
                setSelection(SCISSOR_PLAYER_TWO)
                setPlayerTwo = SCISSOR
                if (setPlayerOne.isNotEmpty()) {
                    setUpRules()
                }
            }

            paperPlayerTwo.setOnClickListener {
                setSelection(PAPER_PLAYER_TWO)
                setPlayerTwo = PAPER
                if (setPlayerOne.isNotEmpty()) {
                    setUpRules()
                }
            }

            /*-------------SETTINGAN FUNGSI TOMBOL RESET------------*/

            reset.setOnClickListener {
                setPlayerOne = ""
                setPlayerTwo = ""
                scissorPlayerOne.setBackgroundResource(0)
                stonePlayerOne.setBackgroundResource(0)
                paperPlayerOne.setBackgroundResource(0)
                scissorPlayerTwo.setBackgroundResource(0)
                stonePlayerTwo.setBackgroundResource(0)
                paperPlayerTwo.setBackgroundResource(0)
                tvResult.setBackgroundResource(0)
                tvResult.text = getString(R.string.vs)
                tvResult.setTextColor(Color.RED)
                tvResult.textSize = 65F
                Toast.makeText(this@MainActivityTwo, "Permainan telah di reset", Toast.LENGTH_SHORT)
                    .show()
            }

            /*-------------SETTINGAN FUNGSI TOMBOL CLOSE------------*/

            btnClose.setOnClickListener {
                val intent = Intent(this@MainActivityTwo, MenuScreen::class.java)
                intent.putExtra(MenuScreen.KEY_NAME, namePlayer)
                startActivity(intent)
            }
        }
    }

    /*-------------SETTINGAN PERTURAN PERMAINAN------------*/
    private fun setUpRules() {
        binding.apply {
            tvResult.setTextColor(Color.WHITE)
            tvResult.textSize = 35F

            if (setPlayerOne == setPlayerTwo) {
                resultSetUp(DRAW)
                tvResult.text = getString(R.string.draw)
                dialogSetUp(DRAW)
                Toast.makeText(this@MainActivityTwo, "Permainan Seri", Toast.LENGTH_SHORT)
                    .show()

            } else if (setPlayerOne == STONE && setPlayerTwo == PAPER) {
                resultSetUp(PAPER)
                tvResult.text = getString(R.string.player_two_win)
                dialogSetUp(PLAYER_TWO_WIN)
                Toast.makeText(this@MainActivityTwo, "Pemain 2 Memilih Kertas", Toast.LENGTH_SHORT)
                    .show()

            } else if (setPlayerOne == STONE && setPlayerTwo == SCISSOR) {
                resultSetUp(STONE)
                tvResult.text = getString(R.string.player_one_win)
                dialogSetUp(PLAYER_ONE_WIN)
                Toast.makeText(this@MainActivityTwo, "Pemain 2 Memilih Gunting", Toast.LENGTH_SHORT)
                    .show()

            } else if (setPlayerOne == SCISSOR && setPlayerTwo == STONE) {
                resultSetUp(STONE)
                tvResult.text = getString(R.string.player_two_win)
                dialogSetUp(PLAYER_TWO_WIN)
                Toast.makeText(this@MainActivityTwo, "Pemain 2 Memilih Batu", Toast.LENGTH_SHORT)
                    .show()

            } else if (setPlayerOne == SCISSOR && setPlayerTwo == PAPER) {
                resultSetUp(STONE)
                tvResult.text = getString(R.string.player_one_win)
                dialogSetUp(PLAYER_ONE_WIN)
                Toast.makeText(this@MainActivityTwo, "Pemain 2 Memilih Kertas", Toast.LENGTH_SHORT)
                    .show()

            } else if (setPlayerOne == PAPER && setPlayerTwo == STONE) {
                resultSetUp(STONE)
                tvResult.text = getString(R.string.player_one_win)
                dialogSetUp(PLAYER_ONE_WIN)
                Toast.makeText(this@MainActivityTwo, "Pemain 2 Memilih Batu", Toast.LENGTH_SHORT)
                    .show()

            } else if (setPlayerOne == PAPER && setPlayerTwo == SCISSOR) {
                resultSetUp(STONE)
                tvResult.text = getString(R.string.player_two_win)
                dialogSetUp(PLAYER_TWO_WIN)
                Toast.makeText(this@MainActivityTwo, "Pemain 2 Memilih Gunting", Toast.LENGTH_SHORT)
                    .show()
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
                val intent = Intent(this@MainActivityTwo, MenuScreen::class.java)
                intent.putExtra(MenuScreen.KEY_NAME, namePlayer)
                startActivity(intent)
            }
            when (nameData) {
                PLAYER_ONE_WIN -> {
                    tvMessagesDialog.text = "$namePlayer Menang!"
                    alertDialog.show()
                }
                PLAYER_TWO_WIN -> {
                    tvMessagesDialog.text = "Player 2 Menang!"
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
                STONE_PLAYER_ONE -> {
                    stonePlayerOne.setBackgroundResource(R.drawable.bg_selector)
                    scissorPlayerOne.setBackgroundResource(0)
                    paperPlayerOne.setBackgroundResource(0)
                }
                PAPER_PLAYER_ONE -> {
                    paperPlayerOne.setBackgroundResource(R.drawable.bg_selector)
                    scissorPlayerOne.setBackgroundResource(0)
                    stonePlayerOne.setBackgroundResource(0)
                }
                SCISSOR_PLAYER_ONE -> {
                    scissorPlayerOne.setBackgroundResource(R.drawable.bg_selector)
                    stonePlayerOne.setBackgroundResource(0)
                    paperPlayerOne.setBackgroundResource(0)
                }
                STONE_PLAYER_TWO -> {
                    stonePlayerTwo.setBackgroundResource(R.drawable.bg_selector)
                    scissorPlayerTwo.setBackgroundResource(0)
                    paperPlayerTwo.setBackgroundResource(0)
                }
                PAPER_PLAYER_TWO -> {
                    paperPlayerTwo.setBackgroundResource(R.drawable.bg_selector)
                    scissorPlayerTwo.setBackgroundResource(0)
                    stonePlayerTwo.setBackgroundResource(0)
                }
                SCISSOR_PLAYER_TWO -> {
                    scissorPlayerTwo.setBackgroundResource(R.drawable.bg_selector)
                    stonePlayerTwo.setBackgroundResource(0)
                    paperPlayerTwo.setBackgroundResource(0)
                }
            }
        }
    }

    private companion object {
        const val STONE_PLAYER_ONE = "STONE PLAYER ONE"
        const val STONE_PLAYER_TWO = "STONE PLAYER TWO"
        const val PAPER_PLAYER_ONE = "PAPER PLAYER ONE"
        const val PAPER_PLAYER_TWO = "PAPER PLAYER TWO"
        const val SCISSOR_PLAYER_ONE = "SCISSOR PLAYER ONE"
        const val SCISSOR_PLAYER_TWO = "SCISSOR PLAYER TWO"
        const val SCISSOR = "SCISSOR"
        const val STONE = "STONE"
        const val PAPER = "PAPER"
        const val DRAW = "DRAW"
        const val KEY_NAME = "KEYWORD NAME"
        const val PLAYER_ONE_WIN = "PLAYER ONE WIN"
        const val PLAYER_TWO_WIN = "PLAYER TWO WIN"
    }
}