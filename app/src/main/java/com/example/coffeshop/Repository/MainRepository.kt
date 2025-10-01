package com.example.coffeshop.Repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.coffeshop.Domain.BannerModel
import com.example.coffeshop.Domain.CategoryModel
import com.example.coffeshop.Domain.ItemsModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.Query
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.getValue

class MainRepository {

    private val firebaseDatabase= FirebaseDatabase.getInstance()

    fun loadBanner(): LiveData<MutableList<BannerModel>>{
        val listData= MutableLiveData<MutableList<BannerModel>>()
        val ref=firebaseDatabase.getReference("Banner")
        ref.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                val list =mutableListOf<BannerModel>()
                for (childSnapshot in snapshot.children){
                    val  item=childSnapshot.getValue(BannerModel::class.java)
                item?.let { list.add(it) }
                }
                listData.value=list
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
        return  listData
    }

    fun loadCategory(): LiveData<MutableList<CategoryModel>> {
        val listData = MutableLiveData<MutableList<CategoryModel>>()
        val ref = firebaseDatabase.getReference("Category") // ✅ Correct path
        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val list = mutableListOf<CategoryModel>()
                for (childSnapshot in snapshot.children) {
                    val item = childSnapshot.getValue(CategoryModel::class.java)
                    item?.let { list.add(it) }
                }
                listData.value = list
            }

            override fun onCancelled(error: DatabaseError) {
                // Handle error gracefully
            }
        })
        return listData
    }

    fun loadPopular(): LiveData<MutableList<ItemsModel>> {
        val listData = MutableLiveData<MutableList<ItemsModel>>()
        val ref = firebaseDatabase.getReference("Popular")

        ref.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val list = mutableListOf<ItemsModel>()
                for (childSnapshot in snapshot.children) {
                    val item = childSnapshot.getValue(ItemsModel::class.java)
                    item?.let { list.add(it) }
                }
                listData.postValue(list)
            }

            override fun onCancelled(error: DatabaseError) {
                listData.postValue(mutableListOf()) // send empty list instead of hanging
            }
        })
        return listData
    }

    fun loadItems(categoryId: String): LiveData<MutableList<ItemsModel>> {
        val itemsLiveData = MutableLiveData<MutableList<ItemsModel>>()
        val ref = firebaseDatabase.getReference("Items")
        val query = ref.orderByChild("categoryId").equalTo(categoryId)

        query.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val list = mutableListOf<ItemsModel>()
                for (child in snapshot.children) {
                    val item = child.getValue(ItemsModel::class.java)
                    if (item != null) list.add(item)
                }
                itemsLiveData.postValue(list)
            }

            override fun onCancelled(error: DatabaseError) {
                itemsLiveData.postValue(mutableListOf()) // empty list on error
            }
        })

        return itemsLiveData
    }

}