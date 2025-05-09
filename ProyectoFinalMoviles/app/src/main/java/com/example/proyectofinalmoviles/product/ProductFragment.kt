package com.example.proyectofinalmoviles.product

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.proyectofinalmoviles.R
import com.example.proyectofinalmoviles.databinding.FragmentProductBinding
import com.example.proyectofinalmoviles.product.viewModel.ProductViewModel
import com.example.proyectofinalmoviles.shoppingCart.ShoppingCartAdapter
import com.example.proyectofinalmoviles.shoppingCart.ShoppingCartProduct
import com.google.android.material.snackbar.Snackbar

class ProductFragment : Fragment() {

    companion object {
        fun newInstance() = ProductFragment()
    }

    private lateinit var binding: FragmentProductBinding
    private val viewModel: ProductViewModel by viewModels()
    private lateinit var myAdapter: ProductAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        binding= FragmentProductBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding){
            val mLayout=LinearLayoutManager(requireContext())
            rvProducts.layoutManager = mLayout
            myAdapter = ProductAdapter(ResponseProduct())
            rvProducts.adapter = myAdapter
            viewModel.returnAllProducts()
            btnPSearch.setOnClickListener {
                if (txtPSearch.text.isBlank()){
                    if (spinnerPCategory.selectedItemPosition==0){
                        viewModel.returnAllProducts()
                    }else{
                        viewModel.returnAllProductsByCategory(spinnerPCategory.selectedItemPosition.toLong())
                    }
                }else{
                    if (spinnerPCategory.selectedItemPosition==0){
                        viewModel.returnALlProductsByName(txtPSearch.text.toString())
                    }else{
                        viewModel.returnAllProductsByAll(txtPSearch.text.toString(), spinnerPCategory.selectedItemPosition.toLong())
                    }
                }
            }
            viewModel.datos.observe(viewLifecycleOwner, Observer { productData ->
                myAdapter = ProductAdapter(productData)
                rvProducts.adapter = myAdapter
            })
            rvProducts.addOnScrollListener(object : RecyclerView.OnScrollListener(){
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    var finalScroll: Boolean= false
                    if (mLayout.findLastVisibleItemPosition()%5>=4){

                    }
                    if (finalScroll){
                        Snackbar.make(mainProducts, "Mostrar mas productos", Snackbar.LENGTH_LONG)
                            .setAction("Cargar mas productos", View.OnClickListener {
/*
                                viewModel.scrollProducts()
*/
                            })
                    }
                }
            })

        }

    }
}