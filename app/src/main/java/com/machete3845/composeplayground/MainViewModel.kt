package com.machete3845.composeplayground

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    val items = mutableStateOf<List<String>>(emptyList())
    private var currentPage = 0
    private val pageSize = 20
    private var isLoading = false

    init {
        loadMoreItems()
    }

    fun loadMoreItems() {
        if (isLoading) return

        viewModelScope.launch {
            isLoading = true
            val newItems = (1..pageSize).map { "Item ${items.value.size + it}" }
            items.value = items.value + newItems
            currentPage++
            isLoading = false
        }
    }
}