package com.vrizy.gamesuit.content

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import com.vrizy.gamesuit.databinding.FragmentIntroductionThreeBinding

class IntroductionThreeFragment : Fragment() {
    lateinit var binding: FragmentIntroductionThreeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentIntroductionThreeBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpAction()
    }


    private fun setUpAction() {
        binding.apply {
            etName.doOnTextChanged { text, start, before, count ->
                (requireActivity() as IntroductionActivity).hideAndShowButton(
                    (text?.length ?: 0) > 0
                )
            }
        }
    }

    fun getNameData(): String {
        return binding.etName.text.toString()
    }

    companion object {
        fun newInstance(page: Int) = IntroductionThreeFragment()

    }
}