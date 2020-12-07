package com.example.assignment.adapter

import android.view.LayoutInflater
import android.view.View.GONE
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.assignment.databinding.LayoutItemBinding
import com.example.assignment.model.BreadResponse

class BreedListAdapter(private val mList: List<BreadResponse>) :
    RecyclerView.Adapter<BreedListAdapter.ListBreedViewHolderItem>() {

    class ListBreedViewHolderItem(val binding: LayoutItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListBreedViewHolderItem {
        return ListBreedViewHolderItem(
            LayoutItemBinding.inflate(
                LayoutInflater.from(
                    parent.context
                ), parent, false
            )
        )
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    override fun onBindViewHolder(holder: ListBreedViewHolderItem, position: Int) {
        with(holder.binding){
            ivImage.visibility = GONE
            ivShare.visibility = GONE
            tvTitle.text = mList[position].name
            tvSubtitle.text = mList[position].id
        }
    }
}