package com.heriawanfx.restaurant.home

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doAfterTextChanged
import com.heriawanfx.restaurant.R
import com.heriawanfx.restaurant.core.data.Resource
import com.heriawanfx.restaurant.core.domain.model.Restaurant
import com.heriawanfx.restaurant.core.ui.RestaurantListAdapter
import com.heriawanfx.restaurant.databinding.ActivityHomeBinding
import org.koin.android.viewmodel.ext.android.viewModel

class HomeActivity : AppCompatActivity(), RestaurantListAdapter.Listener {

    private var _binding: ActivityHomeBinding? = null
    private val binding get() = _binding!!

    private val viewModel: HomeViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = ActivityHomeBinding.inflate(layoutInflater)

        setContentView(binding.root)

        initViews()
        initListeners()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun initViews(){
        val listAdapter = RestaurantListAdapter(this)
        binding.rvRestaurant.adapter = listAdapter

        viewModel.restaurants.observe(this, { resource ->
            if(resource != null){
                when(resource){
                    is Resource.Loading -> {
                        binding.progressBar.visibility = View.VISIBLE
                    }
                    is Resource.Success -> {
                        binding.progressBar.visibility = View.GONE
                        val data = resource.data
                        if(data.isNullOrEmpty()){
                            binding.viewEmpty.root.visibility = View.VISIBLE
                            if(viewModel.getQuery().value?.isNotEmpty() == true){
                                binding.viewEmpty.txtEmpty.text = getString(R.string.search_not_found)
                            }
                        } else {
                            binding.viewEmpty.root.visibility = View.GONE
                        }

                        listAdapter.submitList(resource.data)
                    }
                    is Resource.Error -> {
                        binding.progressBar.visibility = View.GONE
                        binding.rvRestaurant.visibility = View.GONE
                        binding.viewError.root.visibility = View.VISIBLE
                        binding.viewError.txtError.text = resource.message ?: getString(R.string.error_text)
                    }
                }
            }
        })

    }

    private fun initListeners(){
        binding.edtSearch.doAfterTextChanged { editable ->
            val value = editable?.toString() ?: ""
            viewModel.setQuery(value)
        }
    }

    override fun onItemClick(item: Restaurant) {
        //TODO("Not yet implemented")
    }
}