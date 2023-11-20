package com.example.myapplication.util

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplication.R
import com.example.myapplication.databinding.ItemComicBinding
import com.example.myapplication.domain.model.MarvelComic

class ItemComicAdapter(private val context: Context, var itemList:ArrayList<MarvelComic>):
    RecyclerView.Adapter<ItemComicAdapter.ComicListViewHolder>() {

    inner class ComicListViewHolder(view: View): RecyclerView.ViewHolder(view){
        private val binding = ItemComicBinding.bind(view)
        val comicName = binding.tvComicName
        val comicImage = binding.ComicImage
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ComicListViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ComicListViewHolder(layoutInflater.inflate(R.layout.item_comic, parent, false))
    }

    override fun onBindViewHolder(holder: ComicListViewHolder, position: Int) {
        val list  = itemList[position]
        holder.comicName.text = list.title
        val imageUrl = "${list.thumbnail}/portrait_xlarge.${list.thumbnailExt}"
        Glide.with(context).load(imageUrl).into(holder.comicImage)
    }

    override fun getItemCount(): Int = itemList.size

    fun setData(comicList: ArrayList<MarvelComic>){
        this.itemList.addAll(comicList)
        notifyDataSetChanged()
    }


}