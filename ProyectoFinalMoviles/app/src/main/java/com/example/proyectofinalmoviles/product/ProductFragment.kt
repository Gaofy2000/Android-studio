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
import com.example.proyectofinalmoviles.entities.Product
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
    private var firstLoad = true
    private val allProducts = mutableListOf<Product>()
    private var currentPage=0
    private var lastVisibleItemPosition = 0
    private var totalElemnts=0
    private var totalItemsCount= allProducts.size


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
            val mLayout=LinearLayoutManager(requireContext())
            layoutManager = LinearLayoutManager(requireContext())
            rvProducts.layoutManager = layoutManager

            myAdapter = ProductAdapter(ResponseProduct())
            rvProducts.adapter = myAdapter

            viewModel.returnAllProducts(currentPage)

            btnPSearch.setOnClickListener {
                currentPage=0
                if (txtPSearch.text.isBlank()) {
                    if (spinnerPCategory.selectedItemPosition == 0) {
                        viewModel.returnAllProducts(currentPage)
                    } else {
                        viewModel.returnAllProductsByCategory(spinnerPCategory.selectedItemPosition.toLong(), currentPage)
                    }
                } else {
                    if (spinnerPCategory.selectedItemPosition == 0) {
                        viewModel.returnALlProductsByName(txtPSearch.text.toString(), currentPage)
                    } else {
                        viewModel.returnAllProductsByAll(txtPSearch.text.toString(), spinnerPCategory.selectedItemPosition.toLong(), currentPage)
                    }
                }
            }

            viewModel.datos.observe(viewLifecycleOwner, Observer {response ->
                totalElemnts=response.totalElements
                if (firstLoad) {
                    firstLoad = false
                    return@Observer
                }

                if (response.pageable.pageNumber == 0) {
                    allProducts.clear()
                    allProducts.addAll(response.content)
                } else {
                    allProducts.addAll(response.content)
                    myAdapter.notifyItemRangeInserted(currentPage*5, response.content.size)
                }

                val accumulatedResponse = response.copy(
                    content = allProducts.toList(),
                    numberOfElements = allProducts.size,
                )

                lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition()

                myAdapter = ProductAdapter(accumulatedResponse)
                rvProducts.adapter = myAdapter
            })

            rvProducts.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    var finalScroll: Boolean= false
                    totalItemsCount=allProducts.size
                    val lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition()
                    if (lastVisibleItemPosition == totalItemsCount - 1&& totalItemsCount<totalElemnts){
                        finalScroll=true
                    }
                    if (finalScroll){
                        Snackbar.make(mainProducts, "Si desea recuperar más productos pulse: ", Snackbar.LENGTH_LONG)
                            .setAction("Cargar mas productos", View.OnClickListener {
                                currentPage++
                                if (txtPSearch.text.isBlank()) {
                                    if (spinnerPCategory.selectedItemPosition == 0) {
                                        viewModel.returnAllProducts(currentPage)
                                    } else {
                                        viewModel.returnAllProductsByCategory(spinnerPCategory.selectedItemPosition.toLong(), currentPage)
                                    }
                                } else {
                                    if (spinnerPCategory.selectedItemPosition == 0) {
                                        viewModel.returnALlProductsByName(txtPSearch.text.toString(), currentPage)
                                    } else {
                                        viewModel.returnAllProductsByAll(txtPSearch.text.toString(), spinnerPCategory.selectedItemPosition.toLong(), currentPage)
                                    }
                                }
                            }).show()
                    }
                }
            })
        }
    }
}