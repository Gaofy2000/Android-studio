package com.example.recyclerviewanimals

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class MyAdapter(private val dataSet: List<String>) : RecyclerView.Adapter<MyView>() {
    var positionClicked: Int =RecyclerView.NO_POSITION
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyView {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row, parent, false)
        return MyView(view)
    }

    override fun getItemCount() = dataSet.size

    override fun onBindViewHolder(holder: MyView, position: Int) {
        holder.tvAnimales.text = dataSet[position]
        if (position==positionClicked){
            holder.tvAnimales.setTextColor(Color.WHITE)
            holder.tvAnimales.setBackgroundColor(Color.RED)
        }
        else{
            holder.tvAnimales.setTextColor(Color.BLACK)
            holder.tvAnimales.setBackgroundColor(Color.TRANSPARENT)
        }
        holder.tvAnimales.setOnClickListener {
            notifyItemChanged(positionClicked)
            positionClicked=position
            notifyItemChanged(positionClicked)
        }
        /*holder.tvAnimales.setOnClickListener{
            holder.tvAnimales.setTextColor(Color.WHITE)
            holder.tvAnimales.setBackgroundColor(Color.RED)
        }*/
        /*holder.tvAnimales.setOnLongClickListener {
            holder.tvAnimales.setTextColor(Color.BLACK)
            holder.tvAnimales.setBackgroundColor(Color.TRANSPARENT)
            true
        }*/
    }

}