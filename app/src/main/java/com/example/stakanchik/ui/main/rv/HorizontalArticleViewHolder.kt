package com.example.stakanchik.ui.main.rv

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.stakanchik.R
import com.example.stakanchik.data.models.ArticlesEntity
import com.example.stakanchik.databinding.HorizontalItemArticleRecyclerviewBinding

class HorizontalArticleViewHolder(
    private val binding: HorizontalItemArticleRecyclerviewBinding
): RecyclerView.ViewHolder(binding.root) {

    fun bind(item: ArticlesEntity) {
        binding.run {
            Glide.with(itemView.context).load(item.image).into(articleImage)
            articleTitle.text = item.topic
            articleViewsCount.text = item.views.toString()
        }
    }

    companion object {
        fun create(parent: ViewGroup, listener: ArticleAdapter.Listener): HorizontalArticleViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.horizontal_item_article_recyclerview,parent,false)

            val binding = HorizontalItemArticleRecyclerviewBinding.bind(view)

            return HorizontalArticleViewHolder(binding).apply {
                itemView.setOnClickListener {
                    listener.onClick(adapterPosition)
                }
            }
        }
    }
}