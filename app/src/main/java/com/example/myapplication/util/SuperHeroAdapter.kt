package com.example.myapplication.util

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplication.R
import com.example.myapplication.databinding.ItemHeroBinding
import com.example.myapplication.domain.model.Character


class SuperHeroAdapter(private val context: Context, var itemList:ArrayList<Character>):
    RecyclerView.Adapter<SuperHeroAdapter.CharacterListViewHolder>() {

    inner class CharacterListViewHolder(view: View):RecyclerView.ViewHolder(view){
        private val binding = ItemHeroBinding.bind(view)
        val characterName = binding.tvCharacterName
        val characterImage = binding.superHeroImage

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterListViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return CharacterListViewHolder(layoutInflater.inflate(R.layout.item_hero, parent, false))
    }

    override fun onBindViewHolder(holder: CharacterListViewHolder, position: Int) {
        val list  = itemList[position]
        holder.characterName.text = list.name
        val imageUrl = "${list.thumbnail}/portrait_xlarge.${list.thumbnailExt}"
        Glide.with(context).load(imageUrl).into(holder.characterImage)

    }

    override fun getItemCount(): Int = itemList.size





    fun setData(characterList: ArrayList<Character>){
        this.itemList.addAll(characterList)
        notifyDataSetChanged()
    }


}