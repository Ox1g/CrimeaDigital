package com.example.crimeadigital.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.crimeadigital.data.local.MatchEntity
import com.example.crimeadigital.domain.model.Match
import com.example.crimeadigital.domain.repository.MatchRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MatchViewModel @Inject constructor(
    private val matchRepository: MatchRepository
) : ViewModel() {

    val pagedMatches: Flow<PagingData<MatchEntity>> = matchRepository.getMatchesPaged().cachedIn(viewModelScope)

    private val _matches = MutableStateFlow<List<Match>>(emptyList())
    val matches: StateFlow<List<Match>> = _matches

    private val _loading = MutableStateFlow(true)
    val loading: StateFlow<Boolean> = _loading

    private val _searchText = MutableLiveData<String>()
    val searchText: LiveData<String> get() = _searchText

    private val _searchResults = MutableLiveData<List<Match>>()
    val searchResults: LiveData<List<Match>> get() = _searchResults

    init {
        fetchMatches()
    }

    fun fetchMatches() {
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

    fun onSearchTextChange(newText: String) {
        _searchText.value = newText
    }

    fun searchMatches(teamName: String) {
        viewModelScope.launch {
            matchRepository.searchMatchesByTeamName(teamName).collect { matches ->
                Log.d("MatchViewModel", "Matches found: ${matches.size}")
                _searchResults.value = matches
            }
        }
    }
}

