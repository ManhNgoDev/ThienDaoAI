package com.manhngo.thiendaoai.data.repository

import com.manhngo.thiendaoai.data.model.CanhGioi
import com.manhngo.thiendaoai.data.model.UserStats
import kotlin.math.tan

object CultivationSystem {
    private const val MAX_TANG = 10


    /**
     * Tính kinh nghiệm để đột phá lên các tầng tiếp theo
     * công thức: base + tang * 100
     * */
    private fun getExpNeeded(canhGioi: CanhGioi, tang: Int): Long {
        val base = canhGioi.ordinal * 1000L
        return base + tang * 100L
    }

    /**
     * cộng exp và xử lý tầng và cảnh giới
     */
    fun addExp(stats: UserStats, exp: Long): UserStats {
        var currentExp = stats.currentExp + exp
        var tang = stats.tang
        var canhGioi = stats.canhGioi
        var linhLuc = stats.linhLuc
        var thanThuc = stats.thanThuc

        while (true) {
            val expNeeded = getExpNeeded(canhGioi, tang)

            if(currentExp < expNeeded) break

            currentExp -= expNeeded
            tang++

            var multiplier = canhGioi.ordinal + 1

            linhLuc += 10 * tang * multiplier
            thanThuc += 5 * tang * multiplier

            if(tang > MAX_TANG) {
                tang = 1
                canhGioi = nextCanhGioi(canhGioi)

                val m = canhGioi.ordinal + 1
                linhLuc += 1000 * m
                thanThuc += 500 * m
            }
        }
        return stats.copy(
            currentExp = currentExp,
            tang = tang,
            canhGioi = canhGioi,
            linhLuc = linhLuc,
            thanThuc = thanThuc
        )
    }

    fun nextCanhGioi(current: CanhGioi): CanhGioi {
        val values = CanhGioi.values()
        val currentIndex = values.indexOf(current)

        return if (currentIndex < values.size - 1) {
            values[currentIndex + 1]
        } else {
            current
        }
    }

    fun getCurrentExpNeeded(stats: UserStats): Long {
        return getExpNeeded(stats.canhGioi, stats.tang)
    }

    fun getProgress(stats: UserStats): Float {
        val need = getExpNeeded(stats.canhGioi, stats.tang)
        return if(need == 0L) 0f
        else stats.currentExp.toFloat() / need
    }
}