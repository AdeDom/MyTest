package com.adedom.mytest

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.adedom.mytest.databinding.ItemMainBinding
import com.bumptech.glide.Glide

class MainAdapter : RecyclerView.Adapter<MainViewHolder>() {

    private var list = listOf<MainModel>()

    fun submitList(list: List<MainModel>) {
        this.list = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val binding = ItemMainBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false,
        )
        return MainViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }
}

class MainViewHolder(
    private val binding: ItemMainBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(data: MainModel) {
        binding.apply {
            val hasImage = data.image != null
            if (hasImage) {
                Glide.with(ivImage)
                    .load(data.image)
                    .into(ivImage)
            } else {
                ivImage.visibility = View.GONE
            }

            tvId.text = data.id.toString()
            tvTitle.text = data.title
            tvSubTitle.text = data.subTitle
        }
    }
}
