package com.example.botttom.ui.adapter1

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.botttom.ui.dashboard.DashboardFragment

class adapter1(fragmentActivity: DashboardFragment, private val list: List<Fragment>): FragmentStateAdapter(fragmentActivity){
    override fun getItemCount(): Int {
        return list.size
    }

    override fun createFragment(position: Int): Fragment {
        return list[position]
    }
}