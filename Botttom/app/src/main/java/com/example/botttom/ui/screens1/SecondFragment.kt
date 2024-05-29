package com.example.botttom.ui.screens1

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.botttom.R
import com.example.botttom.databinding.FragmentSecondBinding
import com.example.botttom.ui.dashboard.DashboardViewModel


class SecondFragment : Fragment() {
    private lateinit var binding: FragmentSecondBinding
    private val model: DashboardViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_second, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
    companion object{
        @JvmStatic
        fun newInstance() = SecondFragment()
    }

}