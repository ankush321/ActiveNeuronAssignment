package com.example.activeneuron.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.activeneuron.R
import com.example.activeneuron.model.ProductDetail

class ProductListAdapter(val context: Context, var productList: List<ProductDetail>) :
    BaseAdapter() {
    override fun getCount(): Int {
        return productList.size
    }

    override fun getItem(position: Int): ProductDetail {
        return productList[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    @SuppressLint("ViewHolder")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var view: View = View.inflate(context, R.layout.product_item_list, null)
        var image: ImageView = view.findViewById(R.id.product_image)
        var title: TextView = view.findViewById(R.id.product_title)
        var price: TextView = view.findViewById(R.id.product_price)

        var item: ProductDetail = productList[position]

//        image.setImageIcon(item.image)
        Glide.with(context).load(item.image).into(image)
        title.text = item.title
        price.text = item.price.toString()

        return view
    }
}