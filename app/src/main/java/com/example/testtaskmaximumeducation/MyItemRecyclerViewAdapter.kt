package com.example.testtaskmaximumeducation

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.testtaskmaximumeducation.databinding.ItemInListOfArticleBinding
import javax.inject.Inject

class MyItemRecyclerViewAdapter @Inject constructor(
    val repository: Repository
) : RecyclerView.Adapter<MyItemRecyclerViewAdapter.ViewHolder>() {
    private lateinit var context: Context
    var isGrid = false
    lateinit var itemClickListener: ((position: Int) -> Unit)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        context = parent.context

        return ViewHolder(
            ItemInListOfArticleBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = repository.listArticle[position]

        if (isGrid){
            val params = LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.FILL_PARENT)
            params.weight = 0f;
            holder.name.visibility = View.GONE
            holder.published.visibility = View.GONE
            holder.newsSite.visibility = View.GONE
            holder.image.layoutParams = params

        }
        else{
            holder.name.text = item.title
            holder.newsSite.text = item.newsSite
            holder.published.text = item.publishDate.toLocaleString()

        }
        Glide.with(context)
            .load(item.imageUrl)
            .error(R.drawable.ic_baseline_photo_24)
            .into(holder.image)

    }


    override fun getItemCount(): Int = repository.listArticle.size

    fun setOnItemClickListener(itemClickListener: ((position: Int) -> Unit)) {
        this.itemClickListener = itemClickListener
    }

    inner class ViewHolder(binding: ItemInListOfArticleBinding) :

        RecyclerView.ViewHolder(binding.root) {
        init {
            binding.root.setOnClickListener {
                itemClickListener.invoke(absoluteAdapterPosition)
            }
        }

        val name: TextView = binding.itemName
        val newsSite: TextView = binding.itemSite
        val published: TextView = binding.itemPublished
        val image: ImageView = binding.imageView

        override fun toString(): String {
            return super.toString() + " '" + this.toString() + "'"
        }
    }

}