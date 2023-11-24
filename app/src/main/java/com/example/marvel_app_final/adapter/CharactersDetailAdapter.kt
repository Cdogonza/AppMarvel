package com.example.marvel_app_final.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.marvel_app_final.R
import com.example.marvel_app_final.databinding.ItemCharacterDetailBinding
import com.example.marvel_app_final.model.character.Character
import com.example.marvel_app_final.model.comics.Comic
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso

class CharactersDetailAdapter(private val characterList: List<Comic>, private val onItemClickListener:((Comic) -> Unit)): RecyclerView.Adapter<CharactersDetailAdapter.CharacterDetailViewHolder>() {

    inner class CharacterDetailViewHolder(val binding: ItemCharacterDetailBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharactersDetailAdapter.CharacterDetailViewHolder {
        val viewBinding = ItemCharacterDetailBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return  CharacterDetailViewHolder(viewBinding)
    }

    override fun onBindViewHolder(holder: CharactersDetailAdapter.CharacterDetailViewHolder, position: Int) {
        val characterDetail = characterList[position]
        with(holder.binding){
            val uri = "${characterDetail.thumbnail.path}.jpg"
            Picasso
                .get()
                .load(uri)
                .error(R.drawable.ic_launcher_foreground)
                .into(holder.binding.comicimageView, object : Callback {
                    override fun onSuccess() {
                        holder.binding.progressBar.visibility = View.GONE;
                    }

                    override fun onError(e: Exception?) {

                    }
                })
            comicTxtView.text = characterDetail.title
            holder.itemView.setOnClickListener {
                onItemClickListener.invoke(characterDetail)
            }
        }
    }

    override fun getItemCount(): Int {
        return characterList.size
    }



}