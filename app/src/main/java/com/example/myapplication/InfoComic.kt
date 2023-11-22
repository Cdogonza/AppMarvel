package com.example.myapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.myapplication.databinding.InfoComicBinding
import com.example.myapplication.domain.model.MarvelComic

class InfoComic(comic: MarvelComic) : AppCompatActivity() {
    private lateinit var binding: InfoComicBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = InfoComicBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.infoComicTitle.text = intent.getStringExtra("title")
        binding.infoComicDescription.text = intent.getStringExtra("description")
        val imageUrl = "${intent.getStringExtra("thumbnail")}/portrait_xlarge.${intent.getStringExtra("thumbnailExt")}"
        Glide.with(this).load(imageUrl).into(binding.ivInfoComic)
        binding.infoComicNumber.text = intent.getStringExtra("number")

    }


}