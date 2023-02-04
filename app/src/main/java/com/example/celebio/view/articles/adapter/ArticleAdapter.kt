package com.example.celebio.view.articles.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.celebio.R
import com.example.celebio.data.model.Articles
import com.example.celebio.databinding.ItemArticleListBinding
import com.example.celebio.view.articles.ArticlesFragmentDirections

class ArticleAdapter : RecyclerView.Adapter<ArticleAdapter.ViewHolder>() {

    private val callback = object : DiffUtil.ItemCallback<Articles>() {
        override fun areItemsTheSame(oldItem: Articles, newItem: Articles): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Articles, newItem: Articles): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, callback)
    var dataArticles : List<Articles>
        get() = differ.currentList
        set(value) {
            differ.submitList(value)
        }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_article_list, parent, false))

    override fun getItemCount(): Int = dataArticles.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val article = dataArticles[position]
        dataArticles[position]

        holder.bind(dataArticles[position])
        holder.itemView.setOnClickListener {mViews ->
            val direction = ArticlesFragmentDirections.actionArticlesFragmentToDetailFragment(article)
            mViews.findNavController().navigate(direction)
        }
    }

   inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        private val binding = ItemArticleListBinding.bind(itemView)
       fun bind(data: Articles) {
           with(binding){
               binding.articles = data
                Glide.with(itemView.context)
                    .load(data.contentThumbnail)
                    .centerCrop()
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .error(R.drawable.ic_error)
                    .into(ivArticles)
           }
       }
    }
}