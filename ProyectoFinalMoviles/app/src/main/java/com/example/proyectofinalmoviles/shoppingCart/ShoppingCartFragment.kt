package com.example.proyectofinalmoviles.shoppingCart

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.proyectofinalmoviles.R
import com.example.proyectofinalmoviles.databinding.FragmentShoppingCartBinding
import com.example.proyectofinalmoviles.shoppingCart.viewModel.ShoppingCartViewModel

class ShoppingCartFragment : Fragment() {

    companion object {
        fun newInstance() = ShoppingCartFragment()
    }

    private val viewModel: ShoppingCartViewModel by viewModels()
    private lateinit var binding: FragmentShoppingCartBinding
    private lateinit var myAdapter: ShoppingCartAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // TODO: Use the ViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentShoppingCartBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            rvShoppingCart.layoutManager = LinearLayoutManager(requireContext())
            myAdapter = ShoppingCartAdapter(ShoppingCartProduct())
            rvShoppingCart.adapter = myAdapter

            viewModel.datos.observe(viewLifecycleOwner, Observer { shoppingCartData ->
                txtTotalCartQuantity.text=shoppingCartData.totalCartQuantity.toString()
                txtTotalCartPrice.text=shoppingCartData.totalCartPrice.toString()

                myAdapter = ShoppingCartAdapter(shoppingCartData)
                rvShoppingCart.adapter = myAdapter
            })
        }
        viewModel.returnAllCart()
    }
}