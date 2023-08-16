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

        val db = DBBuilder(requireContext()).db
        binding.apply {


            lifecycleScope.launch {

                withContext(Dispatchers.IO) {
                    val arrayList = ArrayList<CartModel>()
                    val l = db.cartDao().getAll()

                    l.forEach {
                        Log.d("data", "$it")
                        arrayList.add(it)
                    }



                    withContext(Dispatchers.Main) {
                        createRecycler(arrayList)
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

    private fun createRecycler(list: ArrayList<CartModel>) {
        val myAdapter = AddToCartAdapter(requireContext(), list)
        val recycler = binding.rvCart
        recycler.adapter = myAdapter
        recycler.layoutManager = LinearLayoutManager(requireContext())
    }
}