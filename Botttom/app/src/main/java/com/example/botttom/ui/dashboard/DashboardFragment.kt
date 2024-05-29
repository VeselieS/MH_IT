package com.example.botttom.ui.dashboard

import android.graphics.Color
import android.graphics.Paint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.botttom.databinding.FragmentDashboardBinding
import com.example.botttom.ui.adapter1.adapter1
import com.example.botttom.ui.screens1.SecondFragment
import com.example.botttom.ui.screens1.FirstFragment
import com.example.botttom.ui.screens1.FourthFragment
import com.example.botttom.ui.screens1.ThirdFragment
import com.google.android.material.tabs.TabLayoutMediator

class DashboardFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null

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
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val dashboardViewModel =
            ViewModelProvider(this).get(DashboardViewModel::class.java)

        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val adapter = adapter1(this, fragList)
        binding.vp2.adapter = adapter
        TabLayoutMediator(binding.tabLayout, binding.vp2){
                tab, pos -> tab.text = titlesFrag[pos]
        }.attach()

        binding.mainName.paintFlags = binding.mainName.paintFlags or Paint.UNDERLINE_TEXT_FLAG
        binding.mainName.setTextColor(Color.WHITE)

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}