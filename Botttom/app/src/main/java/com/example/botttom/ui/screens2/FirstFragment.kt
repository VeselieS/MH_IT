package com.example.botttom.ui.screens2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.botttom.R
import com.example.botttom.databinding.FragmentFirstBinding
import com.example.botttom.ui.dashboard.DashboardViewModel


class FirstFragment : Fragment() {
    private lateinit var binding: FragmentFirstBinding
    private val model: DashboardViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_first_h, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
    companion object{
        @JvmStatic
        fun newInstance() = FirstFragment()
    }

}