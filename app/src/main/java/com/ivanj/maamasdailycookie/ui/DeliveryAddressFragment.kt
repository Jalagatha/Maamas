package com.ivanj.maamasdailycookie.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.ivanj.maamasdailycookie.R
import com.ivanj.maamasdailycookie.databinding.FragmentDeliveryAddressBinding
import com.ivanj.maamasdailycookie.model.DBBuilder
import com.ivanj.maamasdailycookie.model.LocationModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class DeliveryAddressFragment : Fragment() {
    private lateinit var _binding: FragmentDeliveryAddressBinding
    private val binding get() = _binding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDeliveryAddressBinding.inflate(layoutInflater)
        val v = _binding.root

        return v
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.tvBackPay.setOnClickListener {
            findNavController().navigate(R.id.paymentFragment)
        }


        binding.saveAddress.setOnClickListener {
            val name = binding.fullName.text.toString()
            val address = binding.address.text.toString()
            val city = binding.city.text.toString()
            val phone = binding.phone.text.toString()
            val note = binding.note.text.toString()

            lifecycleScope.launch(Dispatchers.IO) {
                val db = DBBuilder(requireContext()).db
                val model = LocationModel(1, name, address, city, phone, note)
                db.locationDao().insertLocation(model)
            }

            findNavController().navigate(R.id.paymentFragment)
        }
    }
}