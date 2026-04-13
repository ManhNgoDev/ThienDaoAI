package com.manhngo.thiendaoai.ui.screens.profile

import androidx.lifecycle.ViewModel
import com.manhngo.thiendaoai.data.model.UserStats
import com.manhngo.thiendaoai.data.repository.CultivationSystem
import kotlinx.coroutines.flow.MutableStateFlow

class UserViewModel: ViewModel() {
    private val _stats = MutableStateFlow(UserStats())
    val stats = _stats

    fun train(exp: Long) {
        _stats.value = CultivationSystem.addExp(_stats.value, exp)
    }
}