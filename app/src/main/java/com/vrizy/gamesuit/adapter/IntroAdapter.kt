package com.vrizy.gamesuit.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.vrizy.gamesuit.content.IntroductionOneFragment
import com.vrizy.gamesuit.content.IntroductionThreeFragment
import com.vrizy.gamesuit.content.IntroductionTwoFragment

class IntroAdapter(fragmentActivity: FragmentActivity) :
    FragmentStateAdapter(fragmentActivity) {


    private val fragmentsData = listOf(
        IntroductionOneFragment.newInstance(FIRST),
        IntroductionTwoFragment.newInstance(SECOND),
        IntroductionThreeFragment.newInstance(THIRD),
    )

    companion object {
        const val FIRST = 0
        const val SECOND = 1
        const val THIRD = 2
    }

    override fun getItemCount(): Int {
        return fragmentsData.size

    }

    override fun createFragment(position: Int): Fragment = fragmentsData[position]

    }