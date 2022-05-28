package com.example.stakanchik.ui.main.rv

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.stakanchik.R
import com.example.stakanchik.databinding.ReadHorizontalItemArticleRecyclerviewBinding
import com.example.stakanchik.domain.models.Article

class ReadArticleViewHolder(
    private val binding: ReadHorizontalItemArticleRecyclerviewBinding
): RecyclerView.ViewHolder(binding.root) {

    fun bind(item: Article) {
        binding.run {
            Glide.with(itemView.context).load(item.image).into(articleImage)
            articleTitle.text = item.topic
            articleViewsCount.text = item.views.toString()
        }
    }

    companion object {
        fun create(parent: ViewGroup, listener: ArticleAdapter.Listener): ReadArticleViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.read_horizontal_item_article_recyclerview,parent,false)

            val binding = ReadHorizontalItemArticleRecyclerviewBinding.bind(view)

            return ReadArticleViewHolder(binding).apply {
                itemView.setOnClickListener {
                    listener.onClick(adapterPosition)
                }
            }
        }
    }
}