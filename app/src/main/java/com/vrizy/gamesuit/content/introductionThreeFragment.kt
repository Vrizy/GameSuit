package com.vrizy.gamesuit.content

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import com.vrizy.gamesuit.MainActivity
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
                if ((text?.length ?: 0) > 0) {
                    btnNextFragment.visibility = View.VISIBLE
                } else {
                    btnNextFragment.visibility = View.GONE
                }
            }
            btnNextFragment.setOnClickListener { viewClick ->
                val intent = Intent(requireActivity(), MainActivity::class.java)
                intent.putExtra(KEY_NAME, etName.text.toString())
                startActivity(intent)
            }
        }
    }

    companion object {
        fun newInstance(page: Int) = IntroductionThreeFragment()
        const val KEY_NAME = "KEYWORD NAME"
    }
}