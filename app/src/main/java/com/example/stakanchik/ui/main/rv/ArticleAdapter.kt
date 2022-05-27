package com.example.stakanchik.ui.main.rv

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.stakanchik.data.models.ArticlesEntity
import com.example.stakanchik.ui.base.BaseEvent

class ArticleAdapter(
    private val listener: Listener
):RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val items = arrayListOf<Any>()

    fun setNewItems(list: List<Any>){
        this.items.clear()
        this.items.addAll(list)
        notifyDataSetChanged()
//        notifyItemChanged(itemCount)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when(viewType) {
            ViewType.Read -> ReadArticleViewHolder.create(parent, listener)
            ViewType.Unread -> HorizontalArticleViewHolder.create(parent, listener)
            else -> HorizontalArticleViewHolder.create(parent, listener)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder){
            is HorizontalArticleViewHolder -> holder.bind(items[position] as ArticlesEntity)
            is ReadArticleViewHolder -> holder.bind(items[position] as ArticlesEntity)
        }
    }

    override fun getItemCount(): Int {
        return items.count()
    }

    override fun getItemViewType(position: Int): Int {
        return when (val item = items[position]){
            is ArticlesEntity -> {
                return when(item.is_read){
                    true -> ViewType.Read
                    false -> ViewType.Unread
                }
            }
            else -> ViewType.Unread
        }
    }

    interface Listener {
        fun onClick(index: Int)
    }

    object ViewType{
        const val Read = 1
        const val Unread = 0
    }
}