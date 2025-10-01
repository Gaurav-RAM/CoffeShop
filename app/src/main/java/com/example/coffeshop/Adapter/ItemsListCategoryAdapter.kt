package com.example.coffeshop.Adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.coffeshop.Activity.DetailActivity
import com.example.coffeshop.Domain.ItemsModel
import com.example.coffeshop.databinding.ActivityMainBinding
import com.example.coffeshop.databinding.ViewholderPopularBinding

class ItemsListCategoryAdapter(val items: MutableList<ItemsModel>) :
RecyclerView.Adapter<ItemsListCategoryAdapter.Viewholder>() {
    class Viewholder(val binding: ViewholderPopularBinding):
    RecyclerView.ViewHolder(binding.root)

    lateinit var context: Context

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ItemsListCategoryAdapter.Viewholder {
        context=parent.context
        val  binding= ViewholderPopularBinding
            .inflate(LayoutInflater.from(context),parent,false)
       return Viewholder(binding)
    }

    override fun onBindViewHolder(holder: ItemsListCategoryAdapter.Viewholder, position: Int) {
        holder.binding.titlerText.text=items[position].title
        holder.binding.priceTxt.text="$"+items[position].price.toString()
       holder.binding.subtitleText.text=items[position].extra.toString()

        Glide.with(context)
            .load(items[position].picUrl?.get(0))
            .into(holder.binding.pic)

        holder.itemView.setOnClickListener {
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra("object", items[position]) // ✅ Now works
            context.startActivity(intent) // <-- don't forget to start A
        }

    }

    override fun getItemCount(): Int =items.size


}