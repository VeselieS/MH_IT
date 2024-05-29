package com.example.botttom.ui.home

import android.graphics.Color
import android.graphics.Paint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.botttom.databinding.FragmentHomeBinding
import com.example.botttom.ui.adapter2.adapter2
import com.example.botttom.ui.screens2.SecondFragment
import com.example.botttom.ui.screens2.FirstFragment
import com.example.botttom.ui.screens2.FourthFragment
import com.example.botttom.ui.screens2.ThirdFragment
import com.google.android.material.tabs.TabLayoutMediator

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    private val fragList = listOf(
        FirstFragment.newInstance(),
        SecondFragment.newInstance(),
        ThirdFragment.newInstance(),
        FourthFragment.newInstance()
    )
    private val titlesFrag = listOf(
        "Планер",
        "Тесты",
        "Статьи",
        "Тренажер"
    )

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val adapter = adapter2(this, fragList)
        binding.vp2.adapter = adapter
        TabLayoutMediator(binding.tabLayout, binding.vp2){
                tab, pos -> tab.text = titlesFrag[pos]
        }.attach()

        binding.mainFav.paintFlags = binding.mainFav.paintFlags or Paint.UNDERLINE_TEXT_FLAG
        binding.mainFav.setTextColor(Color.WHITE)

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}