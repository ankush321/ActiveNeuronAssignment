package com.example.activeneuron.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Product(
    val productId: Int,
    val quantity: Int
) : Parcelable