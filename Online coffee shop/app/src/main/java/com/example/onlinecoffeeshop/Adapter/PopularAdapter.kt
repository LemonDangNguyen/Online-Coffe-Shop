package com.example.onlinecoffeeshop.Adapter

import android.content.Context
import android.content.Intent
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.example.onlinecoffeeshop.Activity.DetailActivity
import com.example.onlinecoffeeshop.Model.ItemsModel
import com.example.onlinecoffeeshop.databinding.ActivityIntroBinding
import com.example.onlinecoffeeshop.databinding.ViewholderPopularBinding

class PopularAdapter(val items:MutableList<ItemsModel>):RecyclerView.Adapter<PopularAdapter.ViewHolder>() {

    private var context: Context? = null

    class ViewHolder(val binding: ViewholderPopularBinding) : RecyclerView.ViewHolder(binding.root)



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularAdapter.ViewHolder {
        context = parent.context
        val binding= ViewholderPopularBinding.inflate(LayoutInflater.from(context),parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PopularAdapter.ViewHolder, position: Int) {
        holder.binding.titeTxt.text = items[position].title
        holder.binding.priceTxt.text="$" + items[position].price.toString()
        holder.binding.ratingBar.rating=items[position].rating.toFloat()
        holder.binding.extraTxt.text=items[position].extra

        Glide.with(holder.itemView.context)
            .load(items[position].picUrl[0])
            .into(holder.binding.pic)




        holder.itemView.setOnClickListener{
        val intent = Intent(holder.itemView.context, DetailActivity::class.java)
            intent.putExtra("object",items[position])
            holder.itemView.context.startActivity(intent)
        }

    }

    override fun getItemCount(): Int = items.size
}