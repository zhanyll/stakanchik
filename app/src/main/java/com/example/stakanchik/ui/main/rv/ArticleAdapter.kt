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
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when(viewType) {
            ViewType.VERTICAL -> ArticleViewHolder.create(parent, listener)
            ViewType.HORIZONTAL -> HorizontalArticleViewHolder.create(parent, listener)
            else -> ArticleViewHolder.create(parent, listener)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder) {
            is ArticleViewHolder -> holder.bind(items[position] as ArticlesEntity)
            is HorizontalArticleViewHolder -> holder.bind(items[position] as ArticlesEntity)
            else -> {}
        }
    }

    override fun getItemCount(): Int {
        return items.count()
    }

    override fun getItemViewType(position: Int): Int {
        return when (items[position]){
            is ArticlesEntity -> {
                return if (position == 0) {
                    ViewType.HORIZONTAL
                } else {
                    when ((position - 1) % 3) {
                        0 -> ViewType.HORIZONTAL
                        1 -> ViewType.VERTICAL
                        else -> 0
                    }
                }
            }
            else -> ViewType.VERTICAL
        }
    }

    interface Listener {
        fun onClick(index: Int)
    }

    object ViewType{
        const val VERTICAL = 2
        const val HORIZONTAL = 1
    }
}