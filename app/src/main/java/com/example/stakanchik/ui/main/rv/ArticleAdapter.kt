package com.example.stakanchik.ui.main.rv

import android.view.ViewGroup
import android.widget.Toast
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
            ViewType.VERTICAL1 -> ArticleViewHolder.create(parent, listener)
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

//    override fun getItemViewType(position: Int): Int {
//        return when (val item = items[position]){
//            is CharacterEntity -> {
//                return when(item.species){
//                    SPECIES.HUMAN -> ViewType.HUMAN
//                    SPECIES.ALIEN -> ViewType.ALIEN
//                }
//            }
//            else -> ViewType.ADVERT
//        }
//    }

    interface Listener {
        fun onClick(index: Int)
    }

    object ViewType{
        const val  VERTICAL1 = 3
        const val  VERTICAL = 2
        const val  HORIZONTAL = 1

    }
}