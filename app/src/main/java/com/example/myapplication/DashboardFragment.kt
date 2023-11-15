package com.example.myapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.FragmentDashboardBinding
import com.example.myapplication.ui.characterList.CharactersViewModel
import com.example.myapplication.util.SuperHeroAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class DashboardFragment : Fragment() , SearchView.OnQueryTextListener {

    private var _binding: FragmentDashboardBinding? = null
    private val binding get() = _binding!!

    private lateinit var searchTerm: String
    var flag = 3
    var paginatedValue = 0
    private lateinit var recyclerView: RecyclerView
    private lateinit var layoutManager: GridLayoutManager
    private lateinit var adapter: SuperHeroAdapter
    private val viewModel: CharactersViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_dashboard, container, false)


//        recyclerView = binding.charactersRecyclerView
//        layoutManager = GridLayoutManager(this, 2)
//        initRecyclerView()
//        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
//            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
//                super.onScrollStateChanged(recyclerView, newState)
//                if (layoutManager.findLastCompletelyVisibleItemPosition() == layoutManager.itemCount - 1) {
//                    paginatedValue += 20
//                    callApi()
//                    viewModel.getAllCharactersData(paginatedValue)
//
//                }
//            }
//        })

    }

    private fun initRecyclerView() {
        adapter = SuperHeroAdapter(requireContext(), ArrayList())
        recyclerView.adapter = adapter
        recyclerView.layoutManager = layoutManager
    }


    private fun callApi() {
        CoroutineScope(Dispatchers.Main).launch {
            repeat(flag) {
//                viewModel._marvelValue.collect{
//                    when{
//                        it.isLoading -> {
//                            binding.progressbar.visibility = View.VISIBLE
//                        }
//                        it.error.isNotBlank() -> {
//                            binding.progressbar.visibility = View.GONE
//                            flag = 0
//                            Toast.makeText(this@MainActivity, it.error, Toast.LENGTH_SHORT).show()
//                            adapter.setData(it.characterList as ArrayList<Character>)
//
//                        }
//
//                        it.characterList.isNotEmpty() -> {
//                            binding.progressbar.visibility = View.GONE
//                            flag = 0
//                            adapter.setData(it.characterList as ArrayList<Character>)
//                        }
//                    }
//                    delay(1000)
//                }

            }
        }
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        if (query != null) {
            searchTerm = query
        }
        if (searchTerm.isNotEmpty()) {
            search()
        }
        return true
    }

    private fun search() {

        viewModel.getSearchedCharacters(searchTerm)
        CoroutineScope(Dispatchers.Main).launch {
//            viewModel._marvelValue.collect{
//                when{
//                    it.isLoading -> {
//                        binding.progressbar.visibility = View.VISIBLE
//                    }
//                    it.error.isNotBlank() -> {
//                        binding.progressbar.visibility = View.GONE
//                        Toast.makeText(this@MainActivity, it.error, Toast.LENGTH_SHORT).show()
//                        //adapter.setData(it.characterList as ArrayList<java.lang.Character>)
//
//                    }
//
//                    it.characterList.isNotEmpty() -> {
//                        binding.progressbar.visibility = View.GONE
//                    }
//                }
//                delay(1000)
//            }

        }
    }

    override fun onQueryTextChange(p0: String?): Boolean {
        return true
    }
}
