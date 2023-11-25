package com.example.marvel_app_final.adapter


import android.content.SharedPreferences
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.marvel_app_final.R
import com.example.marvel_app_final.databinding.ItemCharacterBinding
import com.example.marvel_app_final.model.character.Character
import com.example.marvel_app_final.utils.Favorites
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso


class AllCharactersAdapter(
    private val onItemClickListener: ((Character) -> Unit)): PagingDataAdapter<Character, AllCharactersAdapter.MyViewHolder>(DiffUtilCallBack()) {
    //se obtiene el usuario del shared preferences
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var editor: SharedPreferences.Editor




    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = getItem(position)
        item?.let { holder.bind(it) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val viewBinding = ItemCharacterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(viewBinding)
    }

    inner class MyViewHolder(private val binding: ItemCharacterBinding): RecyclerView.ViewHolder(binding.root) {


        fun bind(data: Character) {
                var fav = Favorites.favorites
                if (fav.contains(data.id)){
                    binding.favIV.setBackgroundResource(R.drawable.baseline_favorite_24)
                }else{
                    binding.favIV.setBackgroundResource(R.drawable.baseline_favorite_shadow_24)
                }

                val finalPath = data.thumbnail.path
                val extension = data.thumbnail.extension
                val path = "$finalPath.$extension"


                Picasso
                .get()
                .load(path)
                .error(R.drawable.ic_launcher_foreground)
                .into(binding.characterImage, object : Callback {
               override fun onSuccess() {
                   binding.progressBar.visibility = View.GONE;
               }

               override fun onError(e: Exception?) {
                   Toast.makeText(itemView.context, "Error", Toast.LENGTH_SHORT).show()
               }
           })
            binding.characterName.text = data.name


            itemView.setOnClickListener {

             onItemClickListener.invoke(data)

                }

        }
    }

    class DiffUtilCallBack: DiffUtil.ItemCallback<Character>() {
        override fun areItemsTheSame(oldItem: Character, newItem: Character): Boolean {
            return oldItem.name == oldItem.name
        }

        override fun areContentsTheSame(oldItem: Character, newItem: Character): Boolean {
            val oldImage = oldItem.thumbnail.path+"."+oldItem.thumbnail.extension
            val newImage = newItem.thumbnail.path+"."+newItem.thumbnail.extension
            return oldItem.name == newItem.name && oldImage == newImage
        }
    }
}