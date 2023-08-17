package com.ivanj.maamasdailycookie.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.ivanj.maamasdailycookie.R
import com.ivanj.maamasdailycookie.databinding.FragmentStatusBinding
import com.ivanj.maamasdailycookie.model.LocationModel
import com.ivanj.maamasdailycookie.model.PaymentModel

class StatusFragment : Fragment() {
    private lateinit var _binding: FragmentStatusBinding
    private val binding get() = _binding

    lateinit var p:List<PaymentModel>
    lateinit var l: List<LocationModel>
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentStatusBinding.inflate(layoutInflater)
        val v = _binding.root

        return v
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.continueButton.setOnClickListener {
            findNavController().navigate(R.id.cartFragment)
        }

    }
}