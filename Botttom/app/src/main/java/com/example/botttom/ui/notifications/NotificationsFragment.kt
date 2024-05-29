package com.example.botttom.ui.notifications

import android.R
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.botttom.buy_podpiska
import com.example.botttom.databinding.FragmentNotificationsBinding
import com.example.botttom.redakt_settings


class NotificationsFragment : Fragment() {

    private var _binding: FragmentNotificationsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val notificationsViewModel =
            ViewModelProvider(this).get(NotificationsViewModel::class.java)

        _binding = FragmentNotificationsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.imageButton.setOnClickListener {
            val intent = Intent(context, redakt_settings::class.java)
            startActivity(intent)
        }

        binding.imageButton2.setOnClickListener {
            val intent = Intent(context, buy_podpiska::class.java)
            startActivity(intent)
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}