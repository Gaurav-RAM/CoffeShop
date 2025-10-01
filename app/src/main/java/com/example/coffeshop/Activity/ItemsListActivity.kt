package com.example.coffeshop.Activity

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.coffeshop.Adapter.ItemsListCategoryAdapter
import com.example.coffeshop.ViewModel.MainViewModel
import com.example.coffeshop.databinding.ActivityItemsListBinding

class ItemsListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityItemsListBinding
    private val viewModel = MainViewModel()
    private var id: String = ""
    private var title: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityItemsListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getBundles()
        initList()
    }

    private fun initList() {
        binding.apply {
            // Show loading while fetching
            progressBar4.visibility = View.VISIBLE

            // Observe data from ViewModel
            viewModel.loadItems(id).observe(this@ItemsListActivity) { items ->
                listView.layoutManager = GridLayoutManager(this@ItemsListActivity, 2)
                listView.adapter = ItemsListCategoryAdapter(items)

                // Hide progress bar when data is loaded
                progressBar4.visibility = View.GONE
            }
            backBtn.setOnClickListener {
                finish()
            }
        }
    }

    private fun getBundles() {
        intent.extras?.let { bundle ->
            id = bundle.getString("id", "")
            title = bundle.getString("title", "")
            binding.categoryTxt.text = title
        }
    }
}
