package com.example.stakanchik.ui.main.rv

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.stakanchik.data.models.ArticlesEntity
import com.example.stakanchik.ui.base.BaseEvent

class ArticleAdapter(
    private val listener: Listener
):RecyclerView.Adapter<HorizontalArticleViewHolder>() {

    private val items = arrayListOf<Any>()

    fun setNewItems(list: List<Any>){
        this.items.clear()
        this.items.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HorizontalArticleViewHolder {
        return HorizontalArticleViewHolder.create(parent, listener)
    }

    override fun onBindViewHolder(holder: HorizontalArticleViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item as ArticlesEntity)
    }

    override fun getItemCount(): Int {
        return items.count()
    }

    interface Listener {
        fun onClick(objectId: String)
    }
}