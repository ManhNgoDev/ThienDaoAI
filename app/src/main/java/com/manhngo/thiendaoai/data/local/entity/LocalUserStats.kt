package com.manhngo.thiendaoai.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.manhngo.thiendaoai.data.model.CanhGioi

@Entity(tableName = "user_stats")
data class LocalUserStats(
    @PrimaryKey val id: Int = 1, // Singleton row
    val currentExp: Long,
    val linhLuc: Double,
    val thanThuc: Double,
    val canhGioi: String, // Store enum as string
    val tang: Int
)
