package com.vrizy.gamesuit.content

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.vrizy.gamesuit.R
import com.vrizy.gamesuit.adapter.IntroAdapter
import com.vrizy.gamesuit.databinding.ActivityIntroductionBinding
import com.vrizy.gamesuit.databinding.FragmentIntroductionOneBinding

class IntroductionActivity : AppCompatActivity() {
    lateinit var binding : ActivityIntroductionBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIntroductionBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setViewPager()
//        setFragment()
    }

    private fun setViewPager(){
        binding.apply {
            vpSlider.apply {
                adapter = IntroAdapter( this@IntroductionActivity)
                currentItem = 0
            }
        }

    }    }

//    private fun setFragment() {
//        val fragment = IntroductionOneFragment()
//        supportFragmentManager.beginTransaction().apply {
//            replace(R.id.fr_content_one, IntroductionOneFragment())
//        } .commit()
//    }
//}