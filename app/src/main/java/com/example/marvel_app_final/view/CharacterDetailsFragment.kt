package com.example.marvel_app_final.view

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.marvel_app_final.R
import com.example.marvel_app_final.adapter.CharactersDetailAdapter
import com.example.marvel_app_final.databinding.FragmentCharacterDetailsBinding
import com.example.marvel_app_final.model.comics.Comic
import com.example.marvel_app_final.viewmodel.CharacterDetailsViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.paging.LoadState
import androidx.recyclerview.widget.GridLayoutManager
import com.example.marvel_app_final.adapter.AllCharactersAdapter
import com.example.marvel_app_final.adapter.ComicDetailAdapter
import com.example.marvel_app_final.databinding.FragmentAllCharactersBinding
import com.example.marvel_app_final.helper.NetworkChecker
import com.example.marvel_app_final.utils.Favorites
import com.example.marvel_app_final.viewmodel.AllCharactersViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class CharacterDetailsFragment : Fragment() {

    val args: CharacterDetailsFragmentArgs by navArgs()
    private lateinit var binding: FragmentCharacterDetailsBinding
    private val viewModel: CharacterDetailsViewModel by viewModel()
    private lateinit var comicDetailAdapter: CharactersDetailAdapter
    private val navController: NavController by lazy {
        findNavController()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentCharacterDetailsBinding.inflate(inflater, container, false)

        binding.favBtn.setOnClickListener(){
            var fav = Favorites.favorites
            if (fav.contains(args.character.id)) {
                binding.favBtn.background = ContextCompat.getDrawable(requireContext(), R.drawable.baseline_favorite_shadow_24)
                fav.remove(args.character.id)
            }else{
                binding.favBtn.background = ContextCompat.getDrawable(requireContext(), R.drawable.baseline_favorite_24)
                fav.add(args.character.id)
            }
            super.onResume()
            Favorites.favorites = fav
        }
        super.onResume()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val(uri,description,name) = getDetailsWithSafeArgs()
        setupViews(uri,description,name)

        viewModel.character.observe(viewLifecycleOwner){ comicList ->
            setRecyclerView(comicList)
        }
        viewModel.getComicsByCharacterId(args.character.id)

    }
    private fun getDetailsWithSafeArgs() : Triple<String,String,String> {
        val uri = args.character.thumbnail.path + "." + args.character.thumbnail.extension
        val description = args.character.description
        val name = args.character.name
        super.onResume()
        return  Triple(uri,description,name)

    }
    @SuppressLint("StringFormatInvalid")
    private fun setupViews(uri:String, description:String, name:String){
        binding.apply {
            Glide.with(this@CharacterDetailsFragment).load(uri).into(detailsCharacterImageView)
            detailsCharacterNameTxt.text = name
            if (description.isEmpty()) {
                detailsCharacterDescriptionTxtView.text = getString(R.string.character_unknown,name)
            }else {
                detailsCharacterDescriptionTxtView.text = description
            }
        }
    }

    override fun onResume() {
        super.onResume()
        var fav = Favorites.favorites
        if (fav.contains(args.character.id)){
            binding.favBtn.setBackgroundResource(R.drawable.baseline_favorite_24)
        }else{
            binding.favBtn.setBackgroundResource(R.drawable.baseline_favorite_shadow_24)
        }
    }



    private fun setRecyclerView(comicList: List<Comic>){
        binding.detailRecyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext(),
                LinearLayoutManager.HORIZONTAL,false)

            adapter = CharactersDetailAdapter(comicList) { comic ->
                val directions = CharacterDetailsFragmentDirections
                    .actionCharacterDetailsFragmentToComicsDetails(comic)
                findNavController().navigate(directions)
            }
        }
    }

}