package com.example.proyectofinalmoviles.shoppingCart

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.proyectofinalmoviles.R

class ShoppingCartAdapter (private val dataSet: ShoppingCartProduct): RecyclerView.Adapter<ShoppingCartView>() {
    lateinit var myContexto: Context
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShoppingCartView {
        myContexto=parent.context
        val view= LayoutInflater.from(parent.context).inflate(R.layout.row_shopping_cart, parent, false)
        return ShoppingCartView(view)
    }

    override fun getItemCount()=dataSet.message!!.size


    override fun onBindViewHolder(holder: ShoppingCartView, position: Int) {
        holder.txtSCName.text=dataSet.message!![position].toString()
    }
}