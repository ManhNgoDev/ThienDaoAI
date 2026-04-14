package com.manhngo.thiendaoai.data.repository

import com.manhngo.thiendaoai.data.local.dao.UserDao
import com.manhngo.thiendaoai.data.local.entity.LocalUserStats
import com.manhngo.thiendaoai.data.model.CanhGioi
import com.manhngo.thiendaoai.data.model.UserStats
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UserRepository(private val userDao: UserDao) {

    fun getUserStats(): Flow<UserStats> {
        return userDao.getUserStats().map { local ->
            local?.let {
                UserStats(
                    currentExp = it.currentExp,
                    linhLuc = it.linhLuc,
                    thanThuc = it.thanThuc,
                    canhGioi = CanhGioi.values().find { cg -> cg.name == it.canhGioi } ?: CanhGioi.LuyenKhi,
                    tang = it.tang
                )
            } ?: UserStats() // Default if none exists
        }
    }

    suspend fun saveUserStats(stats: UserStats) {
        userDao.insertUserStats(
            LocalUserStats(
                currentExp = stats.currentExp,
                linhLuc = stats.linhLuc,
                thanThuc = stats.thanThuc,
                canhGioi = stats.canhGioi.name,
                tang = stats.tang
            )
        )
    }
}

