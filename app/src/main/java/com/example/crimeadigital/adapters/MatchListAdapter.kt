package com.example.crimeadigital.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.crimeadigital.R
import com.example.crimeadigital.model.MatchDetail
import com.example.crimeadigital.fragments.MatchListFragmentDirections

class MatchListAdapter : RecyclerView.Adapter<MatchListAdapter.MatchViewHolder>() {
    private var list: List<MatchDetail> = emptyList()

    @SuppressLint("NotifyDataSetChanged")
    fun setItems(newList: List<MatchDetail>) {
        list = newList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MatchViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.match_item, parent, false)
        return MatchViewHolder(view)
    }

    override fun onBindViewHolder(holder: MatchViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size

    inner class MatchViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val titleView: TextView = itemView.findViewById(R.id.matchView_title)

        fun bind(item: MatchDetail) {
            titleView.text = item.HomeTeam
            itemView.setOnClickListener {
                val action = MatchListFragmentDirections.actionMatchListFragmentToMatchDetailFragment(item)
                itemView.findNavController().navigate(action)
            }
        }
    }
}
