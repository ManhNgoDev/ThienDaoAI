package com.manhngo.thiendaoai.ui.screens.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.manhngo.thiendaoai.data.model.UserStats
import com.manhngo.thiendaoai.data.repository.CultivationSystem
import com.manhngo.thiendaoai.data.repository.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class UserViewModel(private val repository: UserRepository): ViewModel() {
    private val _stats = MutableStateFlow(UserStats())
    val stats: StateFlow<UserStats> = _stats.asStateFlow()

    init {
        viewModelScope.launch {
            repository.getUserStats().collect {
                _stats.value = it
            }
        }
    }

    fun train(exp: Long) {
        val newStats = CultivationSystem.addExp(_stats.value, exp)
        _stats.value = newStats
        viewModelScope.launch {
            repository.saveUserStats(newStats)
        }
    }
}