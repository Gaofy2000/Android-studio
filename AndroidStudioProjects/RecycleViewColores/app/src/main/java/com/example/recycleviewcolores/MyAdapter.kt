package com.example.recycleviewcolores

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.graphics.toColorInt
import androidx.recyclerview.widget.RecyclerView

class
MyAdapter(private val dataSet: List<Color>) : RecyclerView.Adapter<MyView>() {
    var positionClicked: Int =RecyclerView.NO_POSITION
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyView {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row, parent, false)
        return MyView(view)
    }

    override fun getItemCount() = dataSet.size

    override fun onBindViewHolder(holder: MyView, position: Int) {
        holder.tvColor.text = dataSet[position].nombre
        holder.tvColorHexa.text= dataSet[position].hexa
        holder.fondo.setBackgroundColor(dataSet[position].hexa.toColorInt())
        if (position==positionClicked){
            holder.tvColor.setTextColor(dataSet[position].hexa.toColorInt())
            holder.tvColorHexa.setTextColor(dataSet[position].hexa.toColorInt())
            holder.fondo.setBackgroundColor(android.graphics.Color.WHITE)
        }
        else{
            holder.tvColor.text = dataSet[position].nombre
            holder.tvColorHexa.text= dataSet[position].hexa
            holder.tvColor.setTextColor(android.graphics.Color.WHITE)
            holder.tvColorHexa.setTextColor(android.graphics.Color.WHITE)
            holder.fondo.setBackgroundColor(dataSet[position].hexa.toColorInt())
        }
        holder.fondo.setOnClickListener {
            notifyItemChanged(positionClicked)
            positionClicked=position
            notifyItemChanged(positionClicked)
        }
        holder.fondo.setOnLongClickListener {
            positionClicked=RecyclerView.NO_POSITION
            holder.tvColor.setTextColor(android.graphics.Color.WHITE)
            holder.tvColorHexa.setTextColor(android.graphics.Color.WHITE)
            holder.fondo.setBackgroundColor(dataSet[position].hexa.toColorInt())
            true
        }
    }

}