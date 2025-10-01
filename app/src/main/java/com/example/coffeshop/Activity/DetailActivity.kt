package com.example.coffeshop.Activity

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.coffeshop.Domain.ItemsModel
import com.example.coffeshop.Helper.ManagmentCart
import com.example.coffeshop.R
import com.example.coffeshop.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    private lateinit var item: ItemsModel
    private lateinit var managmentCart: ManagmentCart

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        managmentCart = ManagmentCart(this)
        loadItemFromIntent()
        initSizeList()
    }

    private fun initSizeList() {
        binding.apply {
            smallBtn.setOnClickListener {
                smallBtn.setBackgroundResource(R.drawable.brown_stork_bg)
                mediumBtn.setBackgroundResource(0)
                largeBtn.setBackgroundResource(0)
            }

            mediumBtn.setOnClickListener {
                smallBtn.setBackgroundResource(0)
                mediumBtn.setBackgroundResource(R.drawable.brown_stork_bg)
                largeBtn.setBackgroundResource(0)
            }

            largeBtn.setOnClickListener {
                smallBtn.setBackgroundResource(0)
                mediumBtn.setBackgroundResource(0)
                largeBtn.setBackgroundResource(R.drawable.brown_stork_bg)
            }
        }
    }

    private fun loadItemFromIntent() {
        binding.apply {
            // ✅ FIX: Use Parcelable instead of Serializable
            item = intent.getParcelableExtra("object") ?: return

            Glide.with(this@DetailActivity)
                .load(item.picUrl?.getOrNull(0))
                .into(binding.picMain)

            titleTxt.text = item.title
            descriptionTxt.text = item.description
            priceTxt.text = "$${item.price}"
            ratingTxt.text = item.rating?.toString() ?: "0.0"

            addToCartBtn.setOnClickListener {
                item.numberInCart = numberInCartTxt.text.toString().toIntOrNull() ?: 1
                managmentCart.insertItems(item)
            }

            backBtn.setOnClickListener { finish() }

            plusBtn.setOnClickListener {
                item.numberInCart++
                numberInCartTxt.text = item.numberInCart.toString()
            }

            minusBtn.setOnClickListener {
                if (item.numberInCart > 0) {
                    item.numberInCart--
                    numberInCartTxt.text = item.numberInCart.toString()
                }
            }
        }
    }
}
