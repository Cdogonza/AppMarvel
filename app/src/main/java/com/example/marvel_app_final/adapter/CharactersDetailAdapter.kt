package com.example.marvel_app_final.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.marvel_app_final.databinding.ItemCharacterDetailBinding
import com.example.marvel_app_final.model.comics.Comic

class CharactersDetailAdapter(private val characterList: List<Comic>): RecyclerView.Adapter<CharactersDetailAdapter.CharacterDetailViewHolder>() {

    inner class CharacterDetailViewHolder(val binding: ItemCharacterDetailBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharactersDetailAdapter.CharacterDetailViewHolder {
        val viewBinding = ItemCharacterDetailBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return  CharacterDetailViewHolder(viewBinding)
    }

    override fun onBindViewHolder(holder: CharactersDetailAdapter.CharacterDetailViewHolder, position: Int) {
        val characterDetail = characterList[position]
        with(holder.binding){
            val uri = "${characterDetail.thumbnail.path}.jpg"
            Glide.with(comicimageView).load(uri).into(comicimageView)

            comicTxtView.text = characterDetail.title
        }
    }

    override fun getItemCount(): Int {
        return characterList.size
    }

}