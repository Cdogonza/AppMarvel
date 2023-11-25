package com.example.marvel_app_final.view

import android.content.Context.MODE_PRIVATE
import android.net.ConnectivityManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.filter
import androidx.recyclerview.widget.GridLayoutManager
import com.example.marvel_app_final.adapter.AllCharactersAdapter
import com.example.marvel_app_final.databinding.FragmentAllCharactersBinding
import com.example.marvel_app_final.helper.NetworkChecker
import com.example.marvel_app_final.model.character.Character
import com.example.marvel_app_final.viewmodel.AllCharactersViewModel
import com.example.marvel_app_final.viewmodel.SearchCharacterbyIdViewModel
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class AllCharactersFragment : Fragment() {

    private val viewModel: AllCharactersViewModel by viewModel()
    private val networkChecker by lazy {
        NetworkChecker(ContextCompat.getSystemService(requireContext(), ConnectivityManager::class.java))
    }
    private lateinit var binding: FragmentAllCharactersBinding
    private lateinit var allCharactersAdapter: AllCharactersAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentAllCharactersBinding.inflate(inflater, container, false)
        // colocar el binding.searchview
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                //usar viewmodel allcharactersviewmodel

                lifecycleScope.launch {
                    binding.allCharactersRefresh.isRefreshing = false
                    viewModel.getListData().collectLatest { pagingData ->
                        if(query != null){
                            allCharactersAdapter.submitData(pagingData.filter { it.name.startsWith(query) })
                        } else {
                            allCharactersAdapter.submitData(pagingData)
                        }
                    }
                }
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {

                lifecycleScope.launch {
                    binding.allCharactersRefresh.isRefreshing = false
                    viewModel.getListData().collectLatest { pagingData ->
                        if(newText != null){
                            allCharactersAdapter.submitData(pagingData.filter { it.name.startsWith(newText) })
                        } else {
                            allCharactersAdapter.submitData(pagingData)
                        }

                    }
                }
                return false
            }
        })
        binding.logOutBtn.setOnClickListener {
            signOut()
            val directions = AllCharactersFragmentDirections
                .actionAllCharactersFragmentToLoginFragment()
            findNavController().navigate(directions)
        }

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                //usar viewmodel allcharactersviewmodel

                lifecycleScope.launch {
                    binding.allCharactersRefresh.isRefreshing = false
                    viewModel.getListData().collectLatest { pagingData ->
                        if (query != null) {
                            allCharactersAdapter.submitData(pagingData.filter {
                                it.name.startsWith(
                                    query
                                )
                            })
                        } else {
                            allCharactersAdapter.submitData(pagingData)
                        }
                    }
                }
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {

                lifecycleScope.launch {
                    binding.allCharactersRefresh.isRefreshing = false
                    viewModel.getListData().collectLatest { pagingData ->
                        if (newText != null ) {
                            allCharactersAdapter.submitData(pagingData.filter {
                                it.name.startsWith(
                                    newText
                                )
                            })
                        } else {
                            allCharactersAdapter.submitData(pagingData)
                        }

                    }
                }
                return false
            }
        })

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerView()
        setRefreshListener()
        networkChecker.performActionIfConnected { initViewModel() }
    }

    private fun signOut() {
        // eliminar preferencias
        val editor = binding.root.context.getSharedPreferences("sharedPrefs", MODE_PRIVATE).edit()
        editor.putBoolean("isLogged", false)
        editor.putString("username", "")
        editor.apply()

        // cerrar sesion
        FirebaseAuth.getInstance().signOut()
    }

    private fun initRecyclerView() {
        binding.charactersRecyclerView.apply {
            layoutManager = GridLayoutManager(context, 2)

            allCharactersAdapter = AllCharactersAdapter { character ->
                val directions = AllCharactersFragmentDirections
                    .actionAllCharactersFragmentToCharacterDetailsFragment(character)
                findNavController().navigate(directions)
            }
            adapter = allCharactersAdapter

            allCharactersAdapter.addLoadStateListener { loadState ->
                val isListEmpty =
                    loadState.refresh is LoadState.NotLoading && allCharactersAdapter.itemCount == 0
                showEmptyList(isListEmpty)

                binding.progressBar.isVisible = loadState.source.refresh is LoadState.Loading

            }
        }
    }

    private fun showEmptyList(listEmpty: Boolean) {
        if (listEmpty) {
            binding.emptyListTextView.visibility = View.VISIBLE
            binding.charactersRecyclerView.visibility = View.GONE

        } else {
            binding.emptyListTextView.visibility = View.GONE
            binding.charactersRecyclerView.visibility = View.VISIBLE
        }
    }

    private fun setRefreshListener() {
        binding.allCharactersRefresh.setOnRefreshListener {
            initViewModel()
        }
    }

    private fun initViewModel() {
        lifecycleScope.launch {
            binding.allCharactersRefresh.isRefreshing = false
            viewModel.getListData().collectLatest { pagingData ->
                allCharactersAdapter.submitData(pagingData)
            }
        }
    }
}


