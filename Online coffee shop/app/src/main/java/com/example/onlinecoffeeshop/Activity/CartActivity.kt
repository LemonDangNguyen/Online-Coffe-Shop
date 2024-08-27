package com.example.onlinecoffeeshop.Activity

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.onlinecoffeeshop.Adapter.CartAdapter
import com.example.onlinecoffeeshop.Helper.ChangeNumberItemsListener
import com.example.onlinecoffeeshop.R
import com.example.onlinecoffeeshop.databinding.ActivityCartBinding
import com.example.project1762.Helper.ManagmentCart

class CartActivity : BaseActivity() {
    lateinit var binding: ActivityCartBinding
    lateinit var managment: ManagmentCart
    private var tax:Double= 0.0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCartBinding.inflate(layoutInflater)
        setContentView(binding.root)
        managment = ManagmentCart(this)

        caculateCart()
        setVariabe()
        initCartList()
    }

    private fun initCartList() {
        with(binding){
            cartView.layoutManager=LinearLayoutManager(this@CartActivity,LinearLayoutManager.VERTICAL,false)
            cartView.adapter = CartAdapter(managment.getListCart(), this@CartActivity, object :ChangeNumberItemsListener{
                override fun onChanged() {
                    caculateCart()
                }

            })
        }
    }

    private fun setVariabe() {
        binding.backBtn.setOnClickListener {
            finish()
        }
    }

    private fun caculateCart() {
       val percentTax = 0.02
        var delivery = 15.0
        tax = Math.round((managment.getTotalFee()*percentTax)*100)/100.0
        val total = Math.round((managment.getTotalFee()+tax+delivery)*100)/100
        val itemTotal = Math.round((managment.getTotalFee()*100)/100)

        with(binding){
            totalFeeTxt.text="$$itemTotal"
            taxTxt.text="$$tax"
            deliveryTxt.text="$$delivery"
            totalTxt.text="$$total"
        }
    }
}