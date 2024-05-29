package com.example.botttom.ui.screens1

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.botttom.*
import com.example.botttom.databinding.FragmentFirstBinding
import com.example.botttom.databinding.FragmentNotificationsBinding
import com.example.botttom.ui.dashboard.DashboardViewModel


class FirstFragment : Fragment() {
    private var _binding: FragmentFirstBinding? = null
    private val model: DashboardViewModel by activityViewModels()

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.iKalendar.setOnClickListener {
            val intent = Intent(context, calendar_menu::class.java)
            startActivity(intent)
        }
        binding.bEnterZadach.setOnClickListener {
            val intent = Intent(context, enter_zadachi::class.java)
            startActivity(intent)
        }
        binding.bEnterNote.setOnClickListener {
            val intent = Intent(context, enter_note::class.java)
            startActivity(intent)
        }

    }
    companion object{
        @JvmStatic
        fun newInstance() = FirstFragment()
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}