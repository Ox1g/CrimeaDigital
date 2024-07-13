package com.example.crimeadigital.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.crimeadigital.R
import com.example.crimeadigital.adapters.MatchListAdapter
import com.example.crimeadigital.model.MatchDetail

class MatchListFragment : Fragment(R.layout.fragment_match_list) {

    private val matchListAdapter: MatchListAdapter by lazy { MatchListAdapter() }
    private lateinit var recyclerView: RecyclerView
    private var isGrid = false
    private var matchList: List<MatchDetail> = emptyList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        matchList = generateFakeValues()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_match_list, container, false)
        recyclerView = view.findViewById(R.id.recyclerView)
        setupRecyclerView()
        return view
    }

    private fun generateFakeValues(): List<MatchDetail> {
        return List(20) { i ->
            MatchDetail(
                MatchNumber = i,
                RoundNumber = 1,
                DateUtc = "2023-07-08",
                Location = "Stadium $i",
                HomeTeam = "Team $i",
                AwayTeam = "Team ${i + 1}",
                Group = "Group A",
                HomeTeamScore = i,
                AwayTeamScore = i + 1
            )
        }
    }

    private fun setupRecyclerView() {
        recyclerView.apply {
            adapter = matchListAdapter
            layoutManager = if (isGrid) GridLayoutManager(context, 2) else LinearLayoutManager(context)
            setHasFixedSize(true)
        }
        matchListAdapter.setItems(matchList)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun switchLayout() {
        isGrid = !isGrid
        setupRecyclerView()
        matchListAdapter.notifyDataSetChanged()
    }
}
