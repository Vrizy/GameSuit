package com.vrizy.gamesuit.content

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.vrizy.gamesuit.MainMenu
import com.vrizy.gamesuit.R
import kotlinx.android.synthetic.*

class IntroductionThreeFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_introduction_three, container, false)
        
    }

    companion object {
        fun newInstance(page: Int) = IntroductionThreeFragment()
    }
}