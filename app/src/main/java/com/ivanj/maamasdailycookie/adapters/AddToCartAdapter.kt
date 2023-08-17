package com.ivanj.maamasdailycookie.adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ivanj.maamasdailycookie.R
import com.ivanj.maamasdailycookie.model.CartModel
import com.ivanj.maamasdailycookie.model.DBBuilder
import com.ivanj.maamasdailycookie.ui.CartFragment
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AddToCartAdapter(val context: Context, var list_items: ArrayList<CartModel>) :
    RecyclerView.Adapter<AddToCartAdapter.Cart>() {

    var sum = 0
    class Cart(item: View) : RecyclerView.ViewHolder(item) {
        val title: TextView = item.findViewById(R.id.item_Name)
        val amount: TextView = item.findViewById(R.id.cartPrice)
        var qty: TextView = item.findViewById(R.id.quantity)
        val image: ImageView = item.findViewById(R.id.cart_iv)
        val removeBtn: ImageView = item.findViewById(R.id.removeBtn)
        val addBtn: ImageView = item.findViewById(R.id.addButton)
        val clear: ImageView = item.findViewById(R.id.clearBtn)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Cart {
        val v =
            LayoutInflater.from(parent.context).inflate(R.layout.item_cart_layout, parent, false)
        return Cart(v)
    }

    override fun getItemCount(): Int {


        return list_items.size
    }

    override fun onBindViewHolder(holder: Cart, position: Int) {

        val db = DBBuilder(context)
        holder.amount.text = list_items[position].cookie.price
        holder.title.text = list_items[position].cookie.name
        holder.qty.text = list_items[position].quantity.toString()
        var quantity = list_items[position].quantity

        holder.addBtn.setOnClickListener {
            quantity += 1

            val scope = CoroutineScope(Dispatchers.IO)
            scope.launch {
                val total = quantity * list_items[position].cookie.price.toInt()
                val model =
                    CartModel(list_items[position].cid, list_items[position].cookie, quantity, total)
                db.db.cartDao().updateCookie(model)

                getSum(db)
            }

            holder.qty.text = quantity.toString()
        }
        holder.removeBtn.setOnClickListener {
            quantity -= 1
            if (quantity >= 1) {


                val scope = CoroutineScope(Dispatchers.IO)
                scope.launch {
                    val total = quantity * list_items[position].cookie.price.toInt()
                    val model =
                        CartModel(list_items[position].cid, list_items[position].cookie, quantity, total)
                    db.db.cartDao().updateCookie(model)

                    getSum(db)
                }
            } else {

                val scope = CoroutineScope(Dispatchers.IO)
                scope.launch {
                    //val model = CartModel(null, list_items[position].cookie, quantity)
                    val l = db.db.cartDao().getAll()
                    Log.d("data", "$l")
                    db.db.cartDao().deleteCookie(list_items[position].cid ?: 1)
                    val l2 = db.db.cartDao().getAll()
                    Log.d("data 2", "$l2")
                    getSum(db)
                }
                Log.d("Remain 1", "$list_items")
                list_items.remove(list_items[position])
                Log.d("Remain 2", "$list_items")
                notifyDataSetChanged()
//                notifyItemRangeRemoved(position, list_items.size)

            }

            holder.qty.text = quantity.toString()
        }

        holder.clear.setOnClickListener {
//            Log.d("Remain 1", "$list_items")
//            list_items.remove(list_items[position])
//            Log.d("Remain 2", "$list_items")
//            notifyItemChanged(position)
            val scope = CoroutineScope(Dispatchers.IO)
            scope.launch {
                //val model = CartModel(null, list_items[position].cookie, quantity)
                val l = db.db.cartDao().getAll()
                Log.d("data", "$l")
                db.db.cartDao().deleteCookie(list_items[position].cid ?: 1)
                val l2 = db.db.cartDao().getAll()
                Log.d("data 2", "$l2")
                list_items.remove(list_items[position])
                withContext(Dispatchers.Main) {
                    notifyDataSetChanged()
                    getSum(db)
                }

            }

            //notifyDataSetChanged()
        }


        Glide.with(context)
            .load(list_items[position].cookie.image)
            .into(holder.image)





    }

    suspend fun getSum(db: DBBuilder): Int {
        val scope = CoroutineScope(Dispatchers.IO)
        val x = scope.async {
            db.db.cartDao().getAll().forEach {
                //list_items.add(it)
                sum  += it.cookie.price.toInt()
            }
            sum
        }
        return x.await()
    }

    fun refresh(data: List<CartModel>) {
        val currentList: MutableList<CartModel> = mutableListOf()
        currentList.clear()
        currentList.addAll(data)
        notifyDataSetChanged()
    }


}