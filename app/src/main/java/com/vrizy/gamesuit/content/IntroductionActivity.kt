package com.vrizy.gamesuit.content

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.vrizy.gamesuit.adapter.IntroAdapter
import com.vrizy.gamesuit.databinding.ActivityIntroductionBinding

class IntroductionActivity : AppCompatActivity() {
    lateinit var binding: ActivityIntroductionBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIntroductionBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setViewPager()
    }

    private fun setViewPager() {
        binding.apply {
            vpSlider.apply {
                adapter = IntroAdapter(this@IntroductionActivity)
                currentItem = 0
            }
            TabLayoutMediator(tabIndicator, vpSlider) { tab, position ->
            }.attach()

            vpSlider.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {})
        }
    }
}
