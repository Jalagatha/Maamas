package com.ivanj.maamasdailycookie.ui


import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.ivanj.maamasdailycookie.R
import com.ivanj.maamasdailycookie.databinding.BottomSheetBankLayoutBinding
import com.ivanj.maamasdailycookie.databinding.FragmentPaymentDetailsBinding
import com.ivanj.maamasdailycookie.model.DBBuilder
import com.ivanj.maamasdailycookie.model.PaymentModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PaymentDetailsFragment : Fragment() {
    private lateinit var _binding: FragmentPaymentDetailsBinding
    private val binding get() = _binding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPaymentDetailsBinding.inflate(layoutInflater)
        val v = _binding.root

        return v
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.tvBackToAddress.setOnClickListener {
            findNavController().navigate(R.id.paymentFragment)
        }

        binding.bankLayout.setOnClickListener {
            val dialog = BottomSheetDialog(requireContext())

            val v = BottomSheetBankLayoutBinding.inflate(layoutInflater)
            dialog.setContentView(v.root)

            val name = v.cHolderName.text.toString()
            val number = v.cHolderNumber.text.toString()
            val date = v.cDate.text.toString()
            val ccv = v.cCcv.text.toString()

            v.btnAddPay.setOnClickListener {
                lifecycleScope.launch(Dispatchers.IO) {
                    val model = PaymentModel(1, name, number, date, ccv)
                    DBBuilder(requireContext()).db.paymentDao().insertPayment(model)
                }
                dialog.dismiss()
                findNavController().navigate(R.id.paymentFragment)

            }
            dialog.setCancelable(false)
            dialog.show()

        }

    }
}