package com.example.onlinecoffeeshop.Activity

import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.onlinecoffeeshop.Adapter.SizeAdapter
import com.example.onlinecoffeeshop.Model.ItemsModel
import com.example.onlinecoffeeshop.databinding.ActivityDetailBinding
import com.example.project1762.Helper.ManagmentCart

class DetailActivity : BaseActivity() {
    lateinit var binding: ActivityDetailBinding
    private lateinit var item:ItemsModel
    private lateinit var managmentCart: ManagmentCart

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)


        bundle()
        initSizeList()
        managmentCart = ManagmentCart(this)

    }

    private fun initSizeList() {
        val sizeList=ArrayList<String>()
        sizeList.add("1")
        sizeList.add("2")
        sizeList.add("3")
        sizeList.add("4")
        binding.sizeList.adapter=SizeAdapter(sizeList)
        binding.sizeList.layoutManager=LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        val colorList=ArrayList<String>()
        for (imageUrl in item.picUrl){
            colorList.add(imageUrl)

        }
        Glide.with(this)
            .load(colorList[0]).apply(RequestOptions.bitmapTransform(RoundedCorners(100)))
            .into(binding.picMain)
    }

    private fun bundle(){
        binding.apply {
            item = intent.getParcelableExtra("object")!!
            titleTxt.text=item.title
            descriptionTxt.text=item.description
            priceTxt.text="$"+item.price
            ratingBar.rating=item.rating.toFloat()
            addtoCardBtn.setOnClickListener {
                item.numberInCart=Integer.valueOf(numberItemTxt.text.toString())
                managmentCart.insertItems(item)
            }
            backBtn.setOnClickListener { startActivity(Intent(this@DetailActivity,MainActivity::class.java)) }
            plusCart.setOnClickListener {
                numberItemTxt.text=(item.numberInCart+1).toString()
                item.numberInCart++
            }
            minusCart.setOnClickListener {
                if (item.numberInCart>0){
                    numberItemTxt.text=(item.numberInCart-1).toString()
                    item.numberInCart--
                }
               // numberItemTxt.text=(item.numberInCart-1).toString()
            }
        }
    }
}