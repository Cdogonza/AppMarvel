package com.example.marvel_app_final.view

import android.annotation.SuppressLint
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
import com.example.marvel_app_final.adapter.ComicDetailAdapter
import com.example.marvel_app_final.databinding.FragmentComicsDetailsBinding
import com.example.marvel_app_final.model.character.Character
import com.example.marvel_app_final.viewmodel.ComicDetailViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class ComicsDetailsFragment : Fragment()  {

    val args: ComicsDetailsFragmentArgs by navArgs()
    private lateinit var binding: FragmentComicsDetailsBinding
    private val viewModel: ComicDetailViewModel by viewModel()
    private lateinit var comicDetailAdapter: ComicDetailAdapter
    private val navController: NavController by lazy {
        findNavController()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentComicsDetailsBinding.inflate(inflater, container, false)

        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val(uri,title,issueNumber) = getDetailsWithSafeArgs()
        setupViews(uri,title, issueNumber)

        viewModel.characters.observe(viewLifecycleOwner){ characterList ->
            setRecyclerView(characterList)
        }
        viewModel.getCharacterByComicId(args.comic.id)
    }
    private fun getDetailsWithSafeArgs() : Triple<String,String,String> {
        val uri = args.comic.thumbnail.path + "." + args.comic.thumbnail.extension
        val title =  args.comic.title + " #" + args.comic.issueNumber
        val detail = args.comic.description
        return  Triple(uri,title,detail)

    }
    @SuppressLint("StringFormatInvalid")
    private fun setupViews(uri:String, title:String, detail: String){
        binding.apply {
            Glide.with(this@ComicsDetailsFragment).load(uri).into(detailsComicImageView)
            TittleComic.text = title
            if (detail.isEmpty()){
                DetailsComic.text = getString(R.string.comic_unknown)
            }else{
                DetailsComic.text = detail
            }

        }
    }

        private fun setRecyclerView(characterList: List<Character>){
        binding.ComicDetailRecyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext(),
                LinearLayoutManager.HORIZONTAL,false)

            adapter = ComicDetailAdapter(characterList)
        }
    }




}