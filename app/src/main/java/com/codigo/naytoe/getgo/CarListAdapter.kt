package com.codigo.naytoe.getgo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.codigo.naytoe.getgo.databinding.ItemCarBinding

class CarListAdapter(private val listener: ItemListener) : RecyclerView.Adapter<CarListAdapter.ViewHolder>() {

    interface ItemListener {
        fun onClicked()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: ItemCarBinding = ItemCarBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding, listener)
    }

    override fun getItemCount(): Int = 8

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind()

    inner class ViewHolder(private val itemBinding: ItemCarBinding, private val listener: CarListAdapter.ItemListener) : RecyclerView.ViewHolder(itemBinding.root),
        View.OnClickListener {

        init {
            itemBinding.root.setOnClickListener(this)
        }

        fun bind() {
            Glide.with(itemBinding.root)
                .load(R.raw.mazda)
                .into(itemBinding.imageView4)
        }

        override fun onClick(v: View?) {
            listener.onClicked()
        }
    }
}