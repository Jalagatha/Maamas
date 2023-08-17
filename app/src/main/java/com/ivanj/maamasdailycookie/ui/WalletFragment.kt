package com.ivanj.maamasdailycookie.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.fragment.findNavController
import com.ivanj.maamasdailycookie.R
import com.ivanj.maamasdailycookie.databinding.FragmentWalletBinding


class WalletFragment : Fragment() {

    private lateinit var _binding: FragmentWalletBinding

    private var amount = 0

    private val binding get() = _binding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentWalletBinding.inflate(layoutInflater)
        val v = _binding.root
        return v
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.tvBackPayT.setOnClickListener {
            findNavController().navigate(R.id.cartFragment)
        }

        binding.apply {

//            val b=Bundle()
            val x = requireArguments().getString("amountTo")

            Log.d(x, "value $x")
            binding.tvWallet.text = x


        }


    }


}