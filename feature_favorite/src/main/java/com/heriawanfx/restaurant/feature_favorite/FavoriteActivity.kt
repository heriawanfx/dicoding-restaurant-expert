package com.heriawanfx.restaurant.feature_favorite

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.heriawanfx.restaurant.core.domain.model.Restaurant
import com.heriawanfx.restaurant.core.ui.RestaurantListAdapter
import com.heriawanfx.restaurant.detail.DetailActivity
import com.heriawanfx.restaurant.feature_favorite.databinding.ActivityFavoriteBinding
import com.heriawanfx.restaurant.feature_favorite.di.favoriteModule
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules

class FavoriteActivity : AppCompatActivity(), RestaurantListAdapter.Listener {

    private var _binding: ActivityFavoriteBinding? = null
    private val binding get() = _binding!!

    private val viewModel: FavoriteViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = ActivityFavoriteBinding.inflate(layoutInflater)

        setContentView(binding.root)

        loadKoinModules(favoriteModule)

        initViews()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun initViews(){

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val listAdapter = RestaurantListAdapter(this)
        binding.rvRestaurant.adapter = listAdapter


        binding.progressBar.visibility = View.VISIBLE
        viewModel.restaurants.observe(this, { result ->
            binding.progressBar.visibility = View.GONE

            if(result.isNullOrEmpty()){
                binding.txtEmpty.visibility = View.VISIBLE
                return@observe
            }

            binding.txtEmpty.visibility = View.GONE

            listAdapter.submitList(result)

        })

    }


    override fun onItemClick(item: Restaurant) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra("EXTRA_ID", item.id)
        startActivity(intent)
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == android.R.id.home){
            onBackPressed()
        }
        return true
    }


}