package com.manhngo.thiendaoai.data.model

data class UserStats(
    val currentExp: Long = 0,
    val linhLuc: Double = 0.0,
    val thanThuc: Double = 0.0,
    val canhGioi: CanhGioi = CanhGioi.LuyenKhi,
    val tang: Int = 1
)

enum class CanhGioi{
    LuyenKhi,
    TrucCo,
    KimDan,
    NguyenAnh,
    HoaThan,
    LuyenHu,
    HopThe,
    DaiThua,
    DoKiep
}
