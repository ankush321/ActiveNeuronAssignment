package com.example.activeneuron.api

import com.example.activeneuron.model.ProductDetail
import retrofit2.Call
import retrofit2.http.GET

interface ProductApiInterface {

    @GET("products")
    fun getProductList(): Call<List<ProductDetail>>
}