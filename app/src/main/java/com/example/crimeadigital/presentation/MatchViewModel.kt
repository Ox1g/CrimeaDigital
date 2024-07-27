package com.example.crimeadigital.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.crimeadigital.domain.model.Match
import com.example.crimeadigital.domain.repository.MatchRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MatchViewModel @Inject constructor(
    private val matchRepository: MatchRepository
) : ViewModel() {

    private val _matches = MutableStateFlow<List<Match>>(emptyList())
    val matches: StateFlow<List<Match>> = _matches

    private val _loading = MutableStateFlow(true)
    val loading: StateFlow<Boolean> = _loading

    init {
        fetchMatches()
    }

    private fun fetchMatches() {
        viewModelScope.launch {
            _loading.value = true
            try {
                matchRepository.fetchAndCacheMatches()
                matchRepository.getMatches().collect { matchList ->
                    _matches.value = matchList
                    _loading.value = false
                }
            } catch (e: Exception) {
                _loading.value = false
                Log.e("MatchViewModel", "Error fetching matches", e)
            }
        }
    }

}
