package com.example.assignment.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.example.assignment.R
import com.example.assignment.databinding.LayoutItemBinding
import com.example.assignment.model.ResultsItem

class ListSetAdapter(private val mListData: List<ResultsItem>) :
    RecyclerView.Adapter<ListSetAdapter.ListSetViewHolderItem>() {

    class ListSetViewHolderItem(val binding: LayoutItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListSetViewHolderItem {
        return ListSetViewHolderItem(
            LayoutItemBinding.inflate(
                LayoutInflater.from(
                    parent.context
                ), parent, false
            )
        )
    }

    override fun getItemCount(): Int {
        return mListData.size
    }

    override fun onBindViewHolder(holder: ListSetViewHolderItem, position: Int) {
        with(holder.binding) {
            val mData = mListData[position]
            val context = ivImage.context
            val options: RequestOptions? = RequestOptions().dontTransform()
                .diskCacheStrategy(DiskCacheStrategy.DATA).placeholder(R.drawable.placeholder)
            if (null != context) {
                options?.let {
                    Glide.with(context).load(mData.multimedia?.get(3)?.url).apply(it).into(ivImage)
                }
            }
            tvTitle.text = mData.title
            tvSubtitle.text = mData.byline
            ivShare.setOnClickListener {
                val shareIntent = Intent()
                shareIntent.action = Intent.ACTION_SEND
                shareIntent.putExtra(Intent.EXTRA_SUBJECT, mListData[holder.adapterPosition].title)
                shareIntent.putExtra(Intent.EXTRA_TEXT, mListData[holder.adapterPosition].shortUrl)
                shareIntent.type = "text/plain"
                if (null != context.packageManager && null != shareIntent.resolveActivity(
                        context.packageManager
                    )
                ) {
                    context.startActivity(Intent.createChooser(shareIntent, "Share to"))
                }
            }
        }
    }
}