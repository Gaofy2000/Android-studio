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
import com.google.android.material.snackbar.Snackbar

class ProductFragment : Fragment() {

    companion object {
        fun newInstance() = ProductFragment()
    }

    private lateinit var binding: FragmentProductBinding
    private val viewModel: ProductViewModel by viewModels()
    private lateinit var myAdapter: ProductAdapter
    private lateinit var layoutManager: LinearLayoutManager
    private var isLoading = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProductBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            layoutManager = LinearLayoutManager(requireContext())
            rvProducts.layoutManager = layoutManager

            myAdapter = ProductAdapter(ResponseProduct())
            rvProducts.adapter = myAdapter

            viewModel.returnAllProducts()

            btnPSearch.setOnClickListener {
                if (txtPSearch.text.isBlank()) {
                    if (spinnerPCategory.selectedItemPosition == 0) {
                        viewModel.returnAllProducts()
                    } else {
                        viewModel.returnAllProductsByCategory(spinnerPCategory.selectedItemPosition.toLong())
                    }
                } else {
                    if (spinnerPCategory.selectedItemPosition == 0) {
                        viewModel.returnALlProductsByName(txtPSearch.text.toString())
                    } else {
                        viewModel.returnAllProductsByAll(
                            txtPSearch.text.toString(),
                            spinnerPCategory.selectedItemPosition.toLong()
                        )
                    }
                }
            }

            viewModel.datos.observe(viewLifecycleOwner, Observer { productData ->
                myAdapter.updateData(productData)
                isLoading = false
            })

            rvProducts.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)

                    val totalItemCount = layoutManager.itemCount
                    val lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition()

                    if (!isLoading && viewModel.hasMorePages() && lastVisibleItemPosition == totalItemCount - 1) {
                        Snackbar.make(mainProducts, "Si desea recuperar más productos pulse: ", Snackbar.LENGTH_LONG)
                            .setAction("Cargar más productos", View.OnClickListener {
                            isLoading = true
                            viewModel.loadMoreProducts()
                        }).show()
                    }
                }
            })
        }
    }
}