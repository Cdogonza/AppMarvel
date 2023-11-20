package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.databinding.InfoHeroBinding
import com.bumptech.glide.Glide
import com.example.myapplication.domain.model.MarvelComic
import com.example.myapplication.util.ItemComicAdapter
import com.example.myapplication.util.SuperHeroAdapter

class InfoHero : AppCompatActivity() {
    private lateinit var binding: InfoHeroBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = InfoHeroBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.infoHeroName.text = intent.getStringExtra("name")
        binding.infoHeroDescription.text = intent.getStringExtra("description")
        //val imageUrl = "${list.thumbnail}/portrait_xlarge.${list.thumbnailExt}"
        //        Glide.with(context).load(imageUrl).into(holder.characterImage)
        val imageUrl = "${intent.getStringExtra("thumbnail")}/portrait_xlarge.${intent.getStringExtra("thumbnailExt")}"
        Glide.with(this).load(imageUrl).into(binding.ivInfoHero)
        initRecyclerView()
    }

    private fun initRecyclerView() {
        val decoration = DividerItemDecoration(this, DividerItemDecoration.HORIZONTAL)
        binding.rvComic.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        val comicsList : ArrayList<MarvelComic> = intent.getParcelableArrayListExtra<Parcelable>("comics") as ArrayList<MarvelComic>
        // incorporar el onComicClicked, para cuando se haga click en un comic
        binding.rvComic.adapter = ItemComicAdapter(this, comicsList )

        binding.rvComic.addItemDecoration(decoration)
    }

    fun onComicClicked(comic: MarvelComic) {
        val intent = Intent(this, InfoComic::class.java)
        startActivity(intent)
    }




}