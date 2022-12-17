package com.vrizy.gamesuit.content

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import com.vrizy.gamesuit.MenuScreen
import com.vrizy.gamesuit.adapter.IntroAdapter
import com.vrizy.gamesuit.adapter.IntroAdapter.Companion.FIRST
import com.vrizy.gamesuit.adapter.IntroAdapter.Companion.SECOND
import com.vrizy.gamesuit.adapter.IntroAdapter.Companion.THIRD
import com.vrizy.gamesuit.databinding.ActivityIntroductionBinding
import kotlinx.android.synthetic.main.activity_introduction.*

class IntroductionActivity : AppCompatActivity() {
    lateinit var binding: ActivityIntroductionBinding
    lateinit var tabAdapter: IntroAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIntroductionBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setViewPager()
        setUpAction()
    }

    private fun setUpAction() {
        binding.btnNextFragment.setOnClickListener { viewClick ->
            val fragment = tabAdapter.getFragment(binding.vpSlider.currentItem)
            if (fragment is IntroductionThreeFragment) {
                val intent = Intent(this, MenuScreen::class.java)
                intent.putExtra(
                    MenuScreen.KEY_NAME,
                    fragment.getNameData()
                )
                startActivity(intent)
            }
        }
    }

    /*-------------SETTINGAN VIEW PAGER------------*/
    private fun setViewPager() {
        binding.apply {
            vpSlider.apply {
                tabAdapter =
                    IntroAdapter(this@IntroductionActivity)
                adapter = tabAdapter
                currentItem = 0
            }
            TabLayoutMediator(tabIndicator, vpSlider) { tab, position ->
            }.attach()

            /*-------------SETTINGAN PENGATURAN HIDE NEXT FRAGMENT BUTTON PADA TIAP FRAGMENT------------*/
            vpSlider.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    when (position) {
                        FIRST -> {
                            binding.btnNextFragment.visibility = View.GONE
                        }
                        SECOND -> {
                            binding.btnNextFragment.visibility = View.GONE
                        }
                        THIRD -> {
                            val fragment =
                                tabAdapter.getFragment(binding.vpSlider.currentItem)
                            if (fragment is IntroductionThreeFragment) {
                                if (fragment.getNameData().isNotEmpty()) {
                                    binding.btnNextFragment.visibility = View.VISIBLE
                                } else {
                                    binding.btnNextFragment.visibility = View.GONE
                                }
                            }
                        }
                    }
                }
            })
        }
    }

    /*-------------SETTINGAN TOMBOL NEXT UNTUK FRAGMENT 3------------*/
    fun hideAndShowButton(isShow: Boolean) {
        binding.apply {
            if (isShow) {
                btnNextFragment.visibility = View.VISIBLE
            } else {
                btn_next_fragment.visibility = View.GONE
            }
        }
    }
}
