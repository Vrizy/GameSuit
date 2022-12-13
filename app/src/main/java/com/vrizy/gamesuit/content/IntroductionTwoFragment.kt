package com.vrizy.gamesuit.content

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.vrizy.gamesuit.R
import com.vrizy.gamesuit.adapter.IntroAdapter

class IntroductionTwoFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_introduction_two, container, false)
    }

    companion object {
        fun newInstance(page: Int) = IntroductionTwoFragment()
    }
}

/*PRODUK GAGAL :( */
//val view = inflater.inflate(R.layout.fragment_introduction_two, container, false)
//val tabLayout = view.findViewById<TabLayout>(R.id.tab_layout_two)
//val viewPager2 = view.findViewById<ViewPager2>(R.id.vp_two)
//
//tabLayout?.tabMode = TabLayout.MODE_FIXED
//
//viewPager2.adapter = IntroAdapter(activity as FragmentActivity)
//
//tabLayout?.let{
//    TabLayoutMediator(tabLayout, viewPager2) { tab, position ->
//        tab.text = "tab $position"
//    }.attach()
//}
//return view