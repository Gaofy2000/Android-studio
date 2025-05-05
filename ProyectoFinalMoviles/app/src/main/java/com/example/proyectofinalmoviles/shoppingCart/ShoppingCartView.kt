package com.example.proyectofinalmoviles.shoppingCart

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.proyectofinalmoviles.R

class ShoppingCartView (itemView: View): RecyclerView.ViewHolder(itemView) {
    val txtSCName= itemView.findViewById<View>(R.id.txtSCName) as TextView
    val txtSCQuantity= itemView.findViewById<View>(R.id.txtSCQuantity) as TextView
    val txtSCPrice= itemView.findViewById<View>(R.id.txtSCPrice) as TextView
    val txtSCTotalPrice= itemView.findViewById<View>(R.id.txtSCTotalPrice) as TextView
}