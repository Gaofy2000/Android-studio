package com.example.recycleviewcolores

import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MyView(itemView: View): RecyclerView.ViewHolder(itemView) {
    val tvColor: TextView = itemView.findViewById(R.id.tvColor)
    val tvColorHexa: TextView= itemView.findViewById(R.id.tvColorHexa)
    val fondo: LinearLayout= itemView.findViewById(R.id.fondo)
}