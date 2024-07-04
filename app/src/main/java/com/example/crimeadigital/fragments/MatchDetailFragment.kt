package com.example.crimeadigital.fragments

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.crimeadigital.R
import com.example.crimeadigital.model.MatchDetail

class MatchDetailFragment : Fragment(R.layout.fragment_match_detail) {

    private val args: MatchDetailFragmentArgs by navArgs()
    private lateinit var matchDetail: MatchDetail

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        matchDetail = args.match

        view.findViewById<TextView>(R.id.matchNumber).text = "Match Number: ${matchDetail.MatchNumber}"
        view.findViewById<TextView>(R.id.roundNumber).text = "Round Number: ${matchDetail.RoundNumber}"
        view.findViewById<TextView>(R.id.dateUtc).text = "Date: ${matchDetail.DateUtc}"
        view.findViewById<TextView>(R.id.location).text = "Location: ${matchDetail.Location}"
        view.findViewById<TextView>(R.id.homeTeam).text = "Home Team: ${matchDetail.HomeTeam}"
        view.findViewById<TextView>(R.id.awayTeam).text = "Away Team: ${matchDetail.AwayTeam}"
        view.findViewById<TextView>(R.id.homeTeamScore).text = "Home Team Score: ${matchDetail.HomeTeamScore}"
        view.findViewById<TextView>(R.id.awayTeamScore).text = "Away Team Score: ${matchDetail.AwayTeamScore}"
    }
}
