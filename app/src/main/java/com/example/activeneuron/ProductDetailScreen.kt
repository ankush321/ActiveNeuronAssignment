package com.example.activeneuron

import android.R
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.activeneuron.databinding.ActivityProductDetailBinding
import com.example.activeneuron.model.ProductDetail

class ProductDetailScreen : AppCompatActivity() {
    lateinit var binding: ActivityProductDetailBinding
    private var productDetail: ProductDetail? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val actionBar = supportActionBar!!
        actionBar.setDisplayHomeAsUpEnabled(true)
        actionBar.title = "Product Detail"

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

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.home -> {
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}