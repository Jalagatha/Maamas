package com.ivanj.maamasdailycookie.ui


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.ivanj.maamasdailycookie.R
import com.ivanj.maamasdailycookie.adapters.AddToCartAdapter
import com.ivanj.maamasdailycookie.databinding.FragmentCartBinding
import com.ivanj.maamasdailycookie.model.CartModel
import com.ivanj.maamasdailycookie.model.DBBuilder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class CartFragment : Fragment() {


    private lateinit var _binding: FragmentCartBinding
    private val binding get() = _binding


    private var sum = 0
    private var amountTo = 0
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCartBinding.inflate(layoutInflater)
        val v = _binding.root

        return v

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.tvBackCart.setOnClickListener {
            findNavController().navigate(R.id.itemsFragment)
        }
        binding.btnCheckOut.setOnClickListener {
            if(sum > 0) {
                amountTo = (0.15 * sum).toInt()
                val bundle: Bundle = Bundle()
                //bundle.putString("amountTo", amountTo.toString())
                bundle.putString("amountTo", sum.toString())
                Log.d("sum", sum.toString())
                Log.d("amount", amountTo.toString())
                //findNavController().navigate(R.id.walletFragment, bundle)
                findNavController().navigate(R.id.paymentFragment, bundle)
            }

        }

        val db = DBBuilder(requireContext())
        binding.apply {


            lifecycleScope.launch {

                withContext(Dispatchers.IO) {
                    val arrayList = ArrayList<CartModel>()
                    val l = db.db.cartDao().getAll()

                    l.forEach {
                        Log.d("data", "$it")
                        arrayList.add(it)
                        sum += it.total

                    }

                    withContext(Dispatchers.Main) {
                        binding.tvTotal.text = "UGX. $sum"
                        val x = createRecycler(arrayList, db)
//                        binding.tvTotal.text = x.toString()
                    }


//                    val result = cookieApi.getCookies()
//
//                    for (i in result){
//                        db.cookieDao().insertCookie(i)
//                    }


                }
            }


        }

    }

    private suspend fun createRecycler(list: ArrayList<CartModel>, db: DBBuilder): Int  {
        val myAdapter = AddToCartAdapter(requireContext(), list)
        val recycler = binding.rvCart
        recycler.adapter = myAdapter
        recycler.layoutManager = LinearLayoutManager(requireContext())
        return myAdapter.getSum(db)
    }

    suspend fun getTotal(list: ArrayList<CartModel>) {
        val myAdapter = AddToCartAdapter(requireContext(), list)
    }
}