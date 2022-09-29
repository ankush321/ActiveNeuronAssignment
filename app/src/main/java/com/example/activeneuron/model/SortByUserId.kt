package com.example.activeneuron.model


data class SortByUserId(
    val __v: Int,
    val date: String,
    val id: Int,
    val products: List<Product>,
    val userId: Int
)