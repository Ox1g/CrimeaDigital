package com.example.crimeadigital.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.crimeadigital.databinding.FragmentMatchListBinding
import com.example.crimeadigital.R
import com.example.crimeadigital.adapters.MatchListAdapter
import com.example.crimeadigital.model.MatchDetail

class MatchListFragment : Fragment(R.layout.fragment_match_list) {

    private var _binding: FragmentMatchListBinding? = null
    private val binding get() = _binding!!

    private lateinit var matchListAdapter: MatchListAdapter
    private val matchList = listOf(
        MatchDetail(
            MatchNumber = 1,
            RoundNumber = 1,
            DateUtc = "2021-08-13 19:00:00Z",
            Location = "Brentford Community Stadium",
            HomeTeam = "Brentford",
            AwayTeam = "Arsenal",
            Group = null,
            HomeTeamScore = 2,
            AwayTeamScore = 0
        )
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMatchListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        matchListAdapter = MatchListAdapter(matchList) { match ->
            val action = MatchListFragmentDirections.actionMatchListFragmentToMatchDetailFragment(match)
            findNavController().navigate(action)
        }

        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = matchListAdapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
