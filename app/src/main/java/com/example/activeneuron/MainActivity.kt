package com.example.activeneuron

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.GridView
import com.example.activeneuron.adapter.ProductListAdapter
import com.example.activeneuron.api.NetworkHelper
import com.example.activeneuron.api.ProductApiInterface
import com.example.activeneuron.databinding.ActivityMainBinding
import com.example.activeneuron.model.ProductDetail
import com.example.activeneuron.model.SortByUserId
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    var productDetailList: List<ProductDetail>? = null
    var listSort: List<SortByUserId>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        supportActionBar?.title = "Product List"
        setContentView(binding.root)


        getProductList()

    }

    private fun getProductList() {
        val apiCall = NetworkHelper.getInstance().create(ProductApiInterface::class.java)
        apiCall.getSortProduct().enqueue(object : Callback<List<SortByUserId>> {
            override fun onResponse(
                call: Call<List<SortByUserId>>,
                response: Response<List<SortByUserId>>
            ) {
                listSort = response.body()
                val spinner = binding.dropdownSpinner
                binding.dropdownProgressBar.visibility = View.INVISIBLE
                spinner.visibility = View.VISIBLE
                var list: ArrayList<String> = ArrayList()
                for (item in listSort!!) {
                    list.add(item.userId.toString())
                }
                val adapter: ArrayAdapter<String> = ArrayAdapter(
                    this@MainActivity,
                    android.R.layout.simple_spinner_dropdown_item,
                    list
                )
                spinner.adapter = adapter
                spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                    override fun onItemSelected(
                        p0: AdapterView<*>?,
                        p1: View?,
                        position: Int,
                        p3: Long
                    ) {
                        Log.i("ID : ", position.toString())
                        val sortByUserId = listSort!![position]
                        val listProduct = sortByUserId.products
                        val prodDetailList = ArrayList<ProductDetail>()
                        for (prod in listProduct) {
                            for (list in this@MainActivity.productDetailList!!) {

                            }
                        }
                    }

                    override fun onNothingSelected(p0: AdapterView<*>?) {
                    }
                }

            }

            override fun onFailure(call: Call<List<SortByUserId>>, t: Throwable) {

            }
        })
        apiCall.getProductList().enqueue(object : Callback<List<ProductDetail>> {
            override fun onResponse(
                call: Call<List<ProductDetail>>,
                response: Response<List<ProductDetail>>
            ) {
                productDetailList = response.body()
                productDetailList?.let {
                    val productListAdapter = ProductListAdapter(this@MainActivity, it)
                    val gridView: GridView = binding.productListView
                    gridView.adapter = productListAdapter
                    gridView.onItemClickListener =
                        AdapterView.OnItemClickListener { p0, p1, position, p3 ->
                            val intent = Intent(this@MainActivity, ProductDetailScreen::class.java)
                            intent.putExtra("ProductDetail", productDetailList!![position])
                            startActivity(intent)
                        }
                    binding.progressBar.visibility = View.INVISIBLE

                }

                Log.i("Response : ", productDetailList.toString())
            }

            override fun onFailure(call: Call<List<ProductDetail>>, t: Throwable) {
            }
        })
    }
}