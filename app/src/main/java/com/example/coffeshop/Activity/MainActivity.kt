package com.example.coffeshop.Activity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.GridLayout
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.helper.widget.Grid
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.coffeshop.Adapter.CategoryAdapter
import com.example.coffeshop.Adapter.PopularAdapter
import com.example.coffeshop.ViewModel.MainViewModel
import com.example.coffeshop.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel = MainViewModel()

    // ✅ keep adapter as a property (not re-created each time)
    private lateinit var categoryAdapter: CategoryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initBanner()
        initCategory()
        initPopular()
        initBottomMenu()
    }

    private fun initBottomMenu(){
        binding.cartBtn.setOnClickListener {
            startActivity(Intent(this, CartActivity::class.java))
        }
    }

    private fun  initPopular(){
        binding.ProgressBarPopular.visibility = View.VISIBLE

        // Use LiveData observation
        viewModel.loadPopular().observeForever {
         binding.recyclerViewPopular.layoutManager= GridLayoutManager(this,2)
         binding.recyclerViewPopular.adapter= PopularAdapter(it)
         binding.ProgressBarPopular.visibility= View.GONE
        }
      viewModel.loadPopular()
    }

    private fun initCategory() {
        binding.progressBarCategory.visibility = View.VISIBLE

        categoryAdapter = CategoryAdapter(mutableListOf())
        binding.categoryView.apply {
            layoutManager = LinearLayoutManager(
                this@MainActivity, LinearLayoutManager.HORIZONTAL, false
            )
            adapter = categoryAdapter
        }

        // Use LiveData observation
        viewModel.loadCategory().observe(this) { categories ->
            categoryAdapter.updateData(categories)
            binding.progressBarCategory.visibility = View.GONE
        }
    }


    @SuppressLint("CheckResult")
    private fun initBanner() {
        binding.progressBarBanner.visibility = View.VISIBLE

        // ✅ lifecycle-aware observer instead of observeForever
        viewModel.loadBanner().observe(this) { banners ->
            if (banners.isNotEmpty()) {
                Glide.with(this@MainActivity)
                    .load(banners[0].url)
                    .into(binding.banner)
            }
            binding.progressBarBanner.visibility = View.GONE
        }
    }
}
