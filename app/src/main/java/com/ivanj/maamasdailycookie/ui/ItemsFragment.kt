package com.ivanj.maamasdailycookie.ui

import android.net.http.HttpResponseCache.install
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
//import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.client.HttpClient
import com.ivanj.maamasdailycookie.R
import com.ivanj.maamasdailycookie.adapters.CookieAdapter
import com.ivanj.maamasdailycookie.model.CookieModelClassItem
import com.ivanj.maamasdailycookie.databinding.FragmentItemsBinding
import com.ivanj.maamasdailycookie.model.DBBuilder
import com.ivanj.maamasdailycookie.model.Database
import com.ivanj.maamasdailycookie.retrofit.Api
import com.ivanj.maamasdailycookie.retrofit.RetrofitInstanceObject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ItemsFragment : Fragment() {

    private lateinit var _binding: FragmentItemsBinding

//
//    private val client = HttpClient(CIO) {
//        install(HttpTimeout) {
//            requestTimeoutMillis = 5000 // Set the timeout duration in milliseconds
//        }

    private val binding get() = _binding

    lateinit var db: Database

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        _binding = FragmentItemsBinding.inflate(layoutInflater)
        val v = _binding.root
        return v
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.addItemToCart.setOnClickListener {
            findNavController().navigate(R.id.cartFragment)

        }


        db = DBBuilder(requireContext()).db



        binding.apply {
            val cookieApi = RetrofitInstanceObject.getInstance().create(Api::class.java)
            // launching a new coroutine

            val list = ArrayList<CookieModelClassItem>()


            lifecycleScope.launch {


                withContext(Dispatchers.IO) {
//                    val arrayList = ArrayList<CookieModelClassItem>()
//                    val l = db.cookieDao().getAll()
//
//                    l.forEach {
//                        Log.d("data", "$it")
//                        arrayList.add(it)
//                    }
//                    withContext(Dispatchers.Main) {
//                        createRecycler(arrayList)
//                        createRec(arrayList)
//                    }

                    val result = cookieApi.getCookies()

                    withContext(Dispatchers.Main) {
                        createRecycler(result)
                        createRec(result)
                    }

//                    for (i in result){
//                        db.cookieDao().insertCookie(i)
//                    }

                }

//                if (result != null)
//                // Checking the results
//                    Log.d("IvanJ: ", result.body().toString())


            }

        }
    }

    private fun createRecycler(list: ArrayList<CookieModelClassItem>) {
        val myAdapter = CookieAdapter(requireContext(), list)
        val recycler = binding.recycler
        recycler.adapter = myAdapter
        recycler.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
    }

    private fun createRec(list: ArrayList<CookieModelClassItem>) {
        val myAdapter = CookieAdapter(requireContext(), list)
        val rec = binding.rvPopular
        rec.adapter = myAdapter
        rec.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
    }

}