package com.heriawanfx.restaurant.detail

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
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

        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            subtitle = resources.getString(R.string.title_detail)
        }

        viewModel.restaurantDetail.observe(this, { resource ->
            if(resource != null){
                when(resource){
                    is Resource.Loading -> {
                        binding.progressBar.visibility = View.VISIBLE
                    }
                    is Resource.Success -> {
                        binding.progressBar.visibility = View.GONE
                        val item = resource.data
                        if(item == null){
                            binding.viewEmpty.root.visibility = View.VISIBLE
                        } else {
                            binding.viewEmpty.root.visibility = View.GONE
                            binding.groupContent.visibility = View.VISIBLE

                            binding.fabFavorite.setOnClickListener {
                                viewModel.toggleFavorite(item)

                                if(!item.isFavorite){
                                    Toast.makeText(this, "Berhasil ditambah ke daftar favorit", Toast.LENGTH_SHORT).show()
                                } else {
                                    Toast.makeText(this, "Restoran ini telah dihapus dari daftar favorit", Toast.LENGTH_SHORT).show()
                                }

                                generateFavorite(item)
                            }

                            generateContent(item)
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

    private fun generateContent(restaurant: Restaurant){
        with(binding){
            Glide.with(this@DetailActivity)
                .load(restaurant.getPictureUrl())
                .placeholder(R.drawable.bg_placeholder)
                .error(R.drawable.bg_error)
                .into(imgPicture)

            txtName.text = restaurant.name
            txtDescription.text = restaurant.description

            generateFavorite(restaurant)

        }
    }

    private fun generateFavorite(restaurant: Restaurant){
        if(restaurant.isFavorite){
            binding.fabFavorite.setImageDrawable(ContextCompat.getDrawable(this@DetailActivity, R.drawable.ic_favorite))
        } else {
            binding.fabFavorite.setImageDrawable(ContextCompat.getDrawable(this@DetailActivity, R.drawable.ic_favorite_outline))
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == android.R.id.home) {
            onBackPressed()
        }
        return true
    }

}