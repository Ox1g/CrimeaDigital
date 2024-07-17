package com.example.crimeadigital.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.crimeadigital.R
import com.example.crimeadigital.adapters.MatchListAdapter
import kotlinx.coroutines.launch
import com.example.crimeadigital.repository.MatchRepository
import com.example.crimeadigital.utils.Result

class MatchListFragment : Fragment(R.layout.fragment_match_list) {

    private val matchListAdapter: MatchListAdapter by lazy { MatchListAdapter() }
    private lateinit var recyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar
    private var isGrid = false
    private val matchRepository = MatchRepository()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_match_list, container, false)
        recyclerView = view.findViewById(R.id.recyclerView)
        progressBar = view.findViewById(R.id.progressBar)
        setupRecyclerView()
        observeMatches()
        return view
    }


    private fun setupRecyclerView() {
        recyclerView.apply {
            adapter = matchListAdapter
            layoutManager = if (isGrid) GridLayoutManager(context, 2) else LinearLayoutManager(context)
            setHasFixedSize(true)
        }
    }

    private fun observeMatches() {
        lifecycleScope.launch {
            matchRepository.getMatches().collect { result ->
                when (result) {
                    is Result.Loading -> {
                        progressBar.visibility = View.VISIBLE
                        recyclerView.visibility = View.GONE
                    }
                    is Result.Success -> {
                        progressBar.visibility = View.GONE
                        recyclerView.visibility = View.VISIBLE
                        matchListAdapter.setItems(result.data)
                    }
                    is Result.Error -> {
                        Toast.makeText(context, "Ошибка загрузки данных: ${result.exception}", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

}
