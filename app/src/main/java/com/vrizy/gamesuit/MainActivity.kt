package com.vrizy.gamesuit

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Color
import android.location.Location
import android.location.LocationManager
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.RequiresApi
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
//        setUpView()
        setUpAction()
        askPermission()
        askPermissionCamera()
        askPermissionStorage()
    }

    private fun askPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val permissionCheck = checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION)
            if (permissionCheck == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "izin lokasi diberikan", Toast.LENGTH_SHORT).show()
                getLongLat()
            } else {
                Toast.makeText(this, "izin lokasi ditolak", Toast.LENGTH_SHORT).show()
                requestPermission()
            }
        }
    }

    private fun askPermissionCamera() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val permissionCheck = checkSelfPermission(Manifest.permission.CAMERA)
            if (permissionCheck == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "izin camera diberikan", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "izin camera ditolak", Toast.LENGTH_SHORT).show()
                requestPermission()
            }
        }
    }

    private fun askPermissionStorage() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val permissionCheck = checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
            if (permissionCheck == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "izin storage diberikan", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "izin storage ditolak", Toast.LENGTH_SHORT).show()
                requestPermission()
            }
        }
    }

    private fun requestPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(
                arrayOf(
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.CAMERA,
                    Manifest.permission.READ_EXTERNAL_STORAGE,
                ), REQUEST_PERMISSION
            )
        }
    }

    @SuppressLint("MissingPermission")
    private fun getLongLat() {
        val locationManager =
            applicationContext.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        val location: Location? =
            locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER)
        if (location != null) {
            Toast.makeText(
                this, "cek lokasi lat = ${location.latitude} dan long = ${location.longitude}",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            PERMISSION_LOCATION_CODE -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "izin lokasi diberikan", Toast.LENGTH_SHORT).show()
                    getLongLat()
                }
            }
            PERMISSION_CAMERA_CODE -> {
                if (grantResults.isNotEmpty() && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "izin camera diberikan", Toast.LENGTH_SHORT).show()
                }
            }
            PERMISSION_STORAGE_CODE -> {
                if (grantResults.isNotEmpty() && grantResults[2] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "izin storage diberikan", Toast.LENGTH_SHORT).show()
                }
            }
            PERMISSION_DENIED_CODE -> {
                if (grantResults.contains(PackageManager.PERMISSION_DENIED)) {
                    requestPermission()
                }
            }
            else -> Toast.makeText(this, "Bukan request codde yang di kirim", Toast.LENGTH_SHORT)
                .show()
        }
    }


//    private fun setUpView() {
//        binding.apply {
//            Glide.with(this@MainActivity)
//                .load("https://i.ibb.co/zJHYGBP/binarlogo.jpg")
//                .circleCrop()
//                .into(stonePlayer)
//        }
//    }

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
        const val REQUEST_PERMISSION = 205
        const val PERMISSION_DENIED_CODE = 204
        const val PERMISSION_STORAGE_CODE = 203
        const val PERMISSION_CAMERA_CODE = 202
        const val PERMISSION_LOCATION_CODE = 201
        const val SCISSOR = "SCISSOR"
        const val STONE = "STONE"
        const val PAPER = "PAPER"
        const val DRAW = "DRAW"
    }


}