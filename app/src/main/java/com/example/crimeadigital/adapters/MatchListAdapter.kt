package com.example.crimeadigital.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.crimeadigital.R
import com.example.crimeadigital.model.MatchDetail

class MatchListAdapter(private val matchList: List<MatchDetail>, private val onClick: (MatchDetail) -> Unit) :
    RecyclerView.Adapter<MatchListAdapter.MatchViewHolder>() {

    inner class MatchViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val matchItem: TextView = view.findViewById(R.id.matchItem)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MatchViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.match_item, parent, false)
        return MatchViewHolder(view)
    }

    override fun onBindViewHolder(holder: MatchViewHolder, position: Int) {
        val match = matchList[position]
        holder.matchItem.text = match.HomeTeam
        holder.itemView.setOnClickListener { onClick(match) }
    }

    override fun getItemCount() = matchList.size
}
