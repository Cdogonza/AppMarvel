package com.example.marvel_app_final.adapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.marvel_app_final.R
import com.example.marvel_app_final.databinding.ItemCharacterDetailBinding
import com.example.marvel_app_final.model.character.Character
import com.example.marvel_app_final.model.comics.Comic
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import com.example.marvel_app_final.repository.GetCharacterByComicId



class ComicDetailAdapter( private val characterList: List<Character>,
                          ): RecyclerView.Adapter<ComicDetailAdapter.ComicDetailViewHolder>(){



    inner class ComicDetailViewHolder(val binding: ItemCharacterDetailBinding):RecyclerView.ViewHolder(binding.root){

        fun bind(data: Character) {
        val finalPath = data.thumbnail.path
        val extension = data.thumbnail.extension
        val path = "$finalPath.$extension"
        Picasso
            .get()
            .load(path)
            .error(R.drawable.ic_launcher_foreground)
            .into(binding.comicimageView, object : Callback {
                override fun onSuccess() {
                    binding.progressBar.visibility = View.GONE;
                }
                override fun onError(e: Exception?) {
                    Toast.makeText(itemView.context, "Error", Toast.LENGTH_SHORT).show()
                }
            })
        binding.comicTxtView.text = data.name
        binding.root.setOnClickListener {
            Toast.makeText(itemView.context, "END OF ROAD", Toast.LENGTH_SHORT).show()
        }
     }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ComicDetailAdapter.ComicDetailViewHolder {
       val viewBinding = ItemCharacterDetailBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ComicDetailViewHolder(viewBinding)
    }

    override fun onBindViewHolder(holder: ComicDetailAdapter.ComicDetailViewHolder, position: Int) {
        val comicDetail = characterList[position]
        comicDetail.let { holder.bind(it) }

    }

    override fun getItemCount(): Int {
       return characterList.size
    }

}