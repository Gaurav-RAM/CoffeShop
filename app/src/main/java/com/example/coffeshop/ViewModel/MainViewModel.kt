package com.example.coffeshop.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.coffeshop.Domain.BannerModel
import com.example.coffeshop.Domain.CategoryModel
import com.example.coffeshop.Repository.MainRepository

class MainViewModel : ViewModel() {
    private val repository= MainRepository()

    fun  loadBanner(): LiveData<MutableList<BannerModel>>{
        return repository.loadBanner()
    }

    fun loadCategory(): LiveData<MutableList<CategoryModel>>{
        return repository.loadCategory()
    }
}