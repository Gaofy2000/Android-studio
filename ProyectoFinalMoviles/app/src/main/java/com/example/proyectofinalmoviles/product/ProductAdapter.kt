package com.example.proyectofinalmoviles.product

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.proyectofinalmoviles.ProductDetailActivity
import com.example.proyectofinalmoviles.R

class ProductAdapter(private val dataSet: ResponseProduct) : RecyclerView.Adapter<ProductView>() {
    private lateinit var myContexto: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductView {
        myContexto = parent.context
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.row_products, parent, false)
        return ProductView(view)
    }

    override fun getItemCount() = dataSet.content!!.size

    override fun onBindViewHolder(holder: ProductView, position: Int) {
        val product = dataSet.content!![position]
        val BASE_URL = "http://10.0.2.2:8000"
        val urlImage = if (product.image.isNullOrBlank()) {
            "$BASE_URL/imgs/dedo.jpg"
        } else {
            "$BASE_URL${product.image}"
        }
        Glide.with(myContexto).load("http://10.0.2.2:8000/imgs/Danza_Dragon.jpg").into(holder.productImage)
        holder.txtPName.text = product.name
        holder.txtPPrice.text = product.price.toString()
        holder.txtPDescription.text = product.description

        when (product.categoryId) {
            1 -> {
                holder.txtPCategory.text = "Carta"
            }

            2 -> {
                holder.txtPCategory.text = "Sobre"
            }

            3 -> {
                holder.txtPCategory.text = "Caja"
            }

            else -> {
                holder.txtPCategory.text = "Otro"
            }
        }

        holder.btnAddToCart.setOnClickListener {
            val intent = Intent(myContexto, ProductDetailActivity::class.java).apply {
                putExtra("product_id", product.id)
                putExtra("product_name", product.name)
                putExtra("product_description", product.description)
                putExtra("product_image", product.image)
                putExtra("product_price", product.price)
                putExtra("product_category", product.categoryId)
            }
            myContexto.startActivity(intent)
        }


    }

}