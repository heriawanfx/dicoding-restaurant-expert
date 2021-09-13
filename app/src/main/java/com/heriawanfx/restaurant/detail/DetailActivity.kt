package com.heriawanfx.restaurant.detail

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.heriawanfx.restaurant.R
import com.heriawanfx.restaurant.core.data.Resource
import com.heriawanfx.restaurant.core.domain.model.Restaurant
import com.heriawanfx.restaurant.databinding.ActivityDetailBinding
import org.koin.android.viewmodel.ext.android.viewModel

class DetailActivity : AppCompatActivity(){

    private var _binding: ActivityDetailBinding? = null
    private val binding get() = _binding!!

    private val viewModel: DetailViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = ActivityDetailBinding.inflate(layoutInflater)

        setContentView(binding.root)

        parseIntent()
        initViews()
        initListeners()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun parseIntent(){
        val id = intent.getStringExtra("EXTRA_ID")
        if(id != null){
            viewModel.setRestaurantId(id)
        }
    }

    private fun initViews(){

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        viewModel.restaurantDetail.observe(this, { resource ->
            if(resource != null){
                when(resource){
                    is Resource.Loading -> {
                        binding.progressBar.visibility = View.VISIBLE
                    }
                    is Resource.Success -> {
                        binding.progressBar.visibility = View.GONE
                        val data = resource.data
                        if(data == null){
                            binding.viewEmpty.root.visibility = View.VISIBLE
                        } else {
                            binding.viewEmpty.root.visibility = View.GONE
                            binding.groupContent.visibility = View.VISIBLE
                            generateContent(data)
                        }

                    }
                    is Resource.Error -> {
                        binding.progressBar.visibility = View.GONE
                        binding.viewError.root.visibility = View.VISIBLE
                        binding.viewError.txtError.text = resource.message ?: getString(R.string.error_text)
                    }
                }
            }
        })

    }

    private fun generateContent(data: Restaurant){
        with(binding){
            Glide.with(this@DetailActivity)
                .load(data.getPictureUrl())
                .placeholder(R.drawable.bg_placeholder)
                .error(R.drawable.bg_error)
                .into(imgPicture)

            txtName.text = data.name
            txtDescription.text = data.description
        }
    }

    private fun initListeners(){
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == android.R.id.home) {
            onBackPressed()
        }
        return true
    }

}