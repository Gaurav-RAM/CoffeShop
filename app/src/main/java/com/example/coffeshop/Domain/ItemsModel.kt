package com.example.coffeshop.Domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ItemsModel(
    var title: String? = null,
    var description: String? = null,
    var price: Double? = null,
    var rating: Double? = null,
    var extra: String? = null,
    var picUrl: MutableList<String>? = null,
    var categoryId: String? = null,
    var numberInCart: Int = 0  // ✅ added
) : Parcelable