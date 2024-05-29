package com.example.botttom.ui.adapter2

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.botttom.ui.home.HomeFragment

class adapter2(fragmentActivity: HomeFragment, private val list: List<Fragment>): FragmentStateAdapter(fragmentActivity){
    override fun getItemCount(): Int {
        return list.size
    }

    override fun createFragment(position: Int): Fragment {
        return list[position]
    }
}