package com.example.proyectofinalmoviles.shoppingCart

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.proyectofinalmoviles.R

class ShoppingCartView (itemView: View): RecyclerView.ViewHolder(itemView) {
    val txtSCName= itemView.findViewById<View>(R.id.txtSCName) as TextView
}