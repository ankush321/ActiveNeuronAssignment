package com.example.activeneuron

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.GridView
import com.example.activeneuron.adapter.ProductListAdapter
import com.example.activeneuron.api.NetworkHelper
import com.example.activeneuron.api.ProductApiInterface
import com.example.activeneuron.databinding.ActivityMainBinding
import com.example.activeneuron.model.ProductDetail
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    var list: List<ProductDetail>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getProductList()

    }

    private fun getProductList() {
        val apiCall = NetworkHelper.getInstance().create(ProductApiInterface::class.java)
        apiCall.getProductList().enqueue(object : Callback<List<ProductDetail>> {
            override fun onResponse(
                call: Call<List<ProductDetail>>,
                response: Response<List<ProductDetail>>
            ) {
                list = response.body()
                list?.let {
                    val productListAdapter = ProductListAdapter(this@MainActivity, it)
                    val gridView: GridView = binding.productListView
                    gridView.adapter = productListAdapter
                    gridView.onItemClickListener =
                        AdapterView.OnItemClickListener { p0, p1, position, p3 ->
                            val intent = Intent(this@MainActivity, ProductDetailScreen::class.java)
                            intent.putExtra("ProductDetail", list!![position])
                            startActivity(intent)
                        }
                    binding.progressBar.visibility = View.INVISIBLE

                }

                Log.i("Response : ", list.toString())
            }

            override fun onFailure(call: Call<List<ProductDetail>>, t: Throwable) {
            }
        })
    }
}