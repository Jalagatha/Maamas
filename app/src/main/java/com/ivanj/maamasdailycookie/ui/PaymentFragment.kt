package com.ivanj.maamasdailycookie.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.ivanj.maamasdailycookie.R
import com.ivanj.maamasdailycookie.databinding.FragmentPaymentBinding
import com.ivanj.maamasdailycookie.model.CartModel
import com.ivanj.maamasdailycookie.model.DBBuilder
import com.ivanj.maamasdailycookie.model.LocationModel
import com.ivanj.maamasdailycookie.model.PaymentModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class PaymentFragment : Fragment() {
    private lateinit var _binding: FragmentPaymentBinding
    private val binding get() = _binding

    lateinit var p:List<PaymentModel>
    lateinit var l: List<LocationModel>
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPaymentBinding.inflate(layoutInflater)
        val v = _binding.root

        return v
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.tvBackPay.setOnClickListener {
            findNavController().navigate(R.id.cartFragment)
        }

        binding.cardView6.setOnClickListener {
//            if (l.isEmpty()) {
//                findNavController().navigate(R.id.deliveryAddressFragment)
//            } else {
//                Toast.makeText(requireContext(), "Location Available", Toast.LENGTH_LONG).show()
//            }
            findNavController().navigate(R.id.deliveryAddressFragment)
        }

        binding.changePay.setOnClickListener {
//            if (p.isEmpty()) {
//                findNavController().navigate(R.id.paymentDetailsFragment)
//            } else {
//                Toast.makeText(requireContext(), "Payment Method Available", Toast.LENGTH_LONG).show()
//            }
            findNavController().navigate(R.id.paymentDetailsFragment)
        }
        binding.payLayout.setOnClickListener {
//            if (p.isEmpty()) {
//                findNavController().navigate(R.id.paymentDetailsFragment)
//            } else {
//                Toast.makeText(requireContext(), "Payment Method Available", Toast.LENGTH_LONG).show()
//            }
            findNavController().navigate(R.id.paymentDetailsFragment)
        }

        lifecycleScope.launch(Dispatchers.IO) {
            val db = DBBuilder(requireContext()).db
            l = db.locationDao().getAll()

            p = db.paymentDao().getAll()

            val arrayList = ArrayList<CartModel>()
            val list = db.cartDao().getAll()

            var sum =0
            list.forEach {
                 arrayList.add(it)
                 sum += it.total
            }

            withContext(Dispatchers.Main) {
                if (l.isNotEmpty()) {
                    binding.locationText.text = "${l[0].name + "\n" + l[0].city + "\n" + l[0].address }"
                    binding.summary.text = "${l[0].note}"
                }
                if (p.isNotEmpty()) {
                    binding.imageView2.setImageResource(R.drawable.baseline_account_balance_24)
                    binding.payText.text = "Bank Transfer"
                }

                binding.order.text = "UGX. $sum"
            }
        }

        binding.btnCheckOut44.setOnClickListener {
            if (l.isNotEmpty() && p.isNotEmpty()) {
                findNavController().navigate(R.id.statusFragment)
            }
            else {
                Toast.makeText(requireContext(), "Missing Information", Toast.LENGTH_LONG).show()
            }
        }

        binding.safeboda.setOnClickListener {
            binding.safeboda.setCardBackgroundColor(resources.getColor(R.color.bg_color))
            binding.personal.setCardBackgroundColor(resources.getColor(R.color.white))
        }

        binding.personal.setOnClickListener {
            binding.personal.setCardBackgroundColor(resources.getColor(R.color.bg_color))
            binding.safeboda.setCardBackgroundColor(resources.getColor(R.color.white))
        }

    }
}