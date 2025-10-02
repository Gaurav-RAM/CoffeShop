package com.example.coffeshop.Adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.request.RequestOptions
import com.example.coffeshop.Domain.ItemsModel
import com.example.coffeshop.Helper.ChangeNumberItemsListener
import com.example.coffeshop.Helper.ManagmentCart
import com.example.coffeshop.databinding.ViewholderCartBinding

class CartAdapter(
    private val listItemSelected: ArrayList<ItemsModel>,
    private val context: Context,
    private val changeNumberItemsListener: ChangeNumberItemsListener
) : RecyclerView.Adapter<CartAdapter.Viewholder>() {

    class Viewholder(val binding: ViewholderCartBinding) :
        RecyclerView.ViewHolder(binding.root)

    private val managmentCart = ManagmentCart(context)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): Viewholder {
        val binding =
            ViewholderCartBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Viewholder(binding)
    }

    override fun onBindViewHolder(holder: Viewholder, @SuppressLint("RecyclerView") position: Int) {
        val item = listItemSelected[position]

        val price = item.price ?: 0.0
        val total = item.numberInCart * price

        holder.binding.titleTxt.text = item.title
        holder.binding.feeEachItem.text = "$$price"
        holder.binding.totalEachItem.text = "$$total"
        holder.binding.numberInCartTxt.text = item.numberInCart.toString()

        // Load product image
        Glide.with(holder.itemView.context)
            .load(item.picUrl?.getOrNull(0))
            .apply(RequestOptions().transform(CenterCrop()))
            .into(holder.binding.picCart)

        // Handle plus button click
        holder.binding.plusBtn.setOnClickListener {
            managmentCart.plusItem(listItemSelected, position, object : ChangeNumberItemsListener {
                override fun onChanged() {
                    notifyDataSetChanged()
                    changeNumberItemsListener?.onChanged()
                }
            })
        }

        // Handle minus button click
        holder.binding.minusBtn.setOnClickListener {
            managmentCart.minusItem(listItemSelected, position, object : ChangeNumberItemsListener {
                override fun onChanged() {
                    notifyItemChanged(position)
                    changeNumberItemsListener.onChanged()
                }
            })
        }

        // Handle remove button click
        holder.binding.removeItemBtn.setOnClickListener {
            managmentCart.removeItem(listItemSelected, position, object : ChangeNumberItemsListener {
                override fun onChanged() {
                    notifyItemChanged(position)
                    changeNumberItemsListener.onChanged()
                }
            })
        }
    }

    override fun getItemCount(): Int = listItemSelected.size
}
