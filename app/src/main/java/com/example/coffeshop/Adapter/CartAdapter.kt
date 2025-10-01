package com.example.coffeshop.Adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.coffeshop.Domain.ItemsModel
import com.example.coffeshop.Helper.ChangeNumberItemsListener
import com.example.coffeshop.databinding.ActivityCartBinding
import com.example.coffeshop.databinding.ActivityDetailBinding
import com.google.firebase.database.core.Context

class CartAdapter(
    private  val  listItemSelected: ArrayList<ItemsModel>,
    context: Context,
    var changeNumberItemsListener: ChangeNumberItemsListener
): RecyclerView.Adapter<CartAdapter.Viewholder>() {
    class Viewholder(val binding: ActivityCartBinding):
    RecyclerView.ViewHolder(binding.root)


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CartAdapter.Viewholder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: CartAdapter.Viewholder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

}