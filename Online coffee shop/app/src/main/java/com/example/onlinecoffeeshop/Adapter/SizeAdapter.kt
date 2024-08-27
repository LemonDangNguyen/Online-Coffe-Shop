package com.example.onlinecoffeeshop.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.onlinecoffeeshop.R
import com.example.onlinecoffeeshop.databinding.ViewholderSizeBinding

class SizeAdapter(val items: ArrayList<String>):RecyclerView.Adapter<SizeAdapter.Viewholder>() {

        private var selectecPosition =-1
    private var lastselectedPosition = -1
    private  lateinit var context: Context
    inner class Viewholder(var binding: ViewholderSizeBinding) :RecyclerView.ViewHolder(binding.root)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SizeAdapter.Viewholder {
        context = parent.context
        val binding = ViewholderSizeBinding.inflate(LayoutInflater.from(context),parent,false)
        return Viewholder(binding)
    }

    override fun onBindViewHolder(holder: SizeAdapter.Viewholder, position: Int) {
        holder.binding.root.setOnClickListener {
            lastselectedPosition=selectecPosition
            selectecPosition=position
            notifyItemChanged(lastselectedPosition)
            notifyItemChanged(selectecPosition)
        }
        if (selectecPosition==position){
            holder.binding.imgg.setBackgroundResource(R.drawable.orange_bg)

        }else{
            holder.binding.imgg.setBackgroundResource(R.drawable.size_bg)
        }
        val imageSize=when(position){
            0 -> 45.dpToPx(context)
            1 -> 50.dpToPx(context)
            2 -> 50.dpToPx(context)
            3 -> 60.dpToPx(context)
            else -> 70.dpToPx(context)

        }
        val layoutParams = holder.binding.imgg.layoutParams
        layoutParams.width=imageSize
        layoutParams.height = imageSize
        holder.binding.imgg.layoutParams = layoutParams
    }
    private fun Int.dpToPx(context: Context):Int{
        return (this*context.resources.displayMetrics.density).toInt()
    }
    override fun getItemCount(): Int =items.size
}