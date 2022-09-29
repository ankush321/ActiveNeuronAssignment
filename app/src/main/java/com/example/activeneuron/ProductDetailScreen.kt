package com.example.activeneuron

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.bumptech.glide.Glide
import com.example.activeneuron.databinding.ActivityProductDetailBinding
import com.example.activeneuron.model.ProductDetail

class ProductDetailScreen : AppCompatActivity() {
    lateinit var binding: ActivityProductDetailBinding
    private var productDetail: ProductDetail? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        productDetail = intent.extras?.getParcelable("ProductDetail")
        Log.i("ProductScreen : ", productDetail.toString())
        productDetail?.let {
            Glide.with(this).load(it.image).into(binding.image)
            binding.title.text = it.title
            binding.price.text = it.price.toString()
            binding.productDescription.text = it.description
        }
    }
}