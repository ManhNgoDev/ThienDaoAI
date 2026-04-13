package com.manhngo.thiendaoai.data.model

data class UserStats(
    val currentExp: Long = 0,
    val linhLuc: Double = 0.0,
    val thanThuc: Double = 0.0,
    val canhGioi: CanhGioi = CanhGioi.LuyenKhi,
    val tang: Int = 1
)

enum class CanhGioi(val displayName: String){
    LuyenKhi("Luyện Khí Kỳ"),
    TrucCo("Trúc Cơ Kỳ"),
    KimDan("Kim Đan Kỳ"),
    NguyenAnh("Nguyên Anh Kỳ"),
    HoaThan("Hoá Thần Kỳ"),
    LuyenHu("Luyện Hư Kỳ"),
    HopThe("Hợp Thể Kỳ"),
    DaiThua("Đại Thừa Kỳ"),
    DoKiep("Độ Kiếp Kỳ")
}
