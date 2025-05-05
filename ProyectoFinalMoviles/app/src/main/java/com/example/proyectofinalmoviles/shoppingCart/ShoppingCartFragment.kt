package com.example.proyectofinalmoviles.shoppingCart

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.proyectofinalmoviles.databinding.FragmentShoppingCartBinding
import com.example.proyectofinalmoviles.shoppingCart.viewModel.ShoppingCartViewModel

class ShoppingCartFragment : Fragment() {

    private lateinit var binding: FragmentShoppingCartBinding
    private lateinit var myAdapter: ShoppingCartAdapter
    private val viewModel: ShoppingCartViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentShoppingCartBinding.inflate(inflater, container, false)
        myAdapter = ShoppingCartAdapter(ShoppingCartProduct("No success", null, 0, 0.0))
        viewModel.devuelveSCProductos("cart")
        viewModel.datos.observe(viewLifecycleOwner) {
            if (it.status == "success") {
                myAdapter = ShoppingCartAdapter(it)
                binding.rvShoppingCart.adapter = myAdapter

            }
        }
        return binding.root
    }


}