# Thiên Đạo AI (ThienDaoAI)

**Thiên Đạo AI** là một ứng dụng di động độc đáo kết hợp giữa trí tuệ nhân tạo (AI) và thế giới tu tiên huyền ảo. Ứng dụng cho phép người dùng trò chuyện với "Thiên Đạo" để tìm kiếm lời giải cho vận mệnh, lĩnh ngộ đạo pháp và thăng tiến trên con đường tu tiên kỹ thuật số.

![Thiên Đạo AI](app/src/main/res/drawable/yin_yang.png)

## 📱 Giao Diện Ứng Dụng (Screenshots)

| Màn Hình Chính | Tàng Thư Các | Hồ Sơ Tu Sĩ |
| :---: | :---: | :---: |
| ![Main Screen](<img width="1080" height="2424" alt="Screenshot_20260414_145622" src="https://github.com/user-attachments/assets/8df71261-c155-4945-bb7d-18a8634fa3be" />) | ![History Screen](<img width="1080" height="2424" alt="Screenshot_20260414_145628" src="https://github.com/user-attachments/assets/b42230cd-1b78-4f29-9edb-89d52a01345a" />
) | ![Profile Screen](<img width="1080" height="2424" alt="Screenshot_20260414_145635" src="https://github.com/user-attachments/assets/d9bab14b-7f99-419c-ac39-88340ef95731" />
) |

## 🌟 Tính Năng Chính
- **Đàm Đạo Với Thiên Đạo**: Trò chuyện trực tiếp với AI được huấn luyện theo phong cách tiên hiệp, cổ phong. Mỗi câu trả lời đều mang đậm khí chất của bậc đại năng.
- **Hệ Thống Tu Vi (XP System)**: Tích lũy kinh nghiệm thông qua việc đàm đạo và đặt câu hỏi. Càng giao tiếp nhiều, tu vi của bạn càng thăng tiến.
- **Tàng Thư Các (History)**: Lưu trữ các đoạn đối thoại tâm đắc dưới dạng các điển tích. Bạn có thể quay lại xem hoặc tiếp tục các đoạn chat cũ bất cứ lúc nào.
- **Hồ Sơ Tu Sĩ**: Theo dõi các chỉ số tu vi, cấp bậc và linh căn của bản thân trong quá trình tu luyện.
- **Giao Diện Đậm Chất Cổ Phong**: Thiết kế sang trọng với tông màu kem, chữ Serif và các hiệu ứng micro-animation mang lại trải nghiệm huyền ảo.

## 🤖 Mô Hình Trí Tuệ Nhân Tạo (AI Engine)

Ứng dụng được thiết kế để hoạt động với các mô hình ngôn ngữ lớn (LLM) chạy cục bộ nhằm đảm bảo quyền riêng tư và tốc độ xử lý:
- **Công cụ chạy Local**: [LM Studio](https://lmstudio.ai/)
- **Mô hình khuyến nghị**: `google/gemma-4-e4b` (Chạy local thông qua server OpenAI-compatible của LM Studio).
- **Kết nối**: Ứng dụng kết nối tới Localhost thông qua Retrofit với định dạng chuẩn OpenAI.

## 🛠️ Công Nghệ Sử Dụng

- **Ngôn ngữ**: Kotlin
- **UI Framework**: Jetpack Compose (Modern & Reactive UI)
- **Cơ sở dữ liệu**: Room Database (Lưu trữ lịch sử chat và chỉ số người dùng)
- **Mạng**: Retrofit & OkHttp (Kết nối với API Thiên Đạo)
- **Xử lý bất đồng bộ**: Coroutines & Flow
- **Dependency Management**: Gradle Version Catalog (libs.versions.toml)
- **Annotation Processing**: KSP (Kotlin Symbol Processing)

## 🚀 Cấu Trúc Dự Án

- `ui/screens`: Chứa các màn hình chính (Chat, History, Profile, Splash).
- `ui/component`: Các thành phần giao diện tái sử dụng.
- `data/local`: Cấu hình Room Database, Entities và DAO.
- `data/remote`: Cấu hình API Service và các Model dữ liệu từ máy chủ.
- `data/repository`: Lớp trung gian xử lý logic dữ liệu giữa Local và Remote.

## 📥 Cài Đặt và Chạy Thử

1. Clone repository này về máy.
2. Mở dự án bằng **Android Studio (Ladybug trở lên)**.
3. Đảm bảo bạn đã cấu hình JDK 17+.
4. Sync Gradle và chọn `Run` trên thiết bị giả lập hoặc máy thật Android (API 26+).

## 📜 Giấy Phép

Dự án này được phát triển bởi **ManhNgoDev**. Vui lòng liên hệ nếu bạn muốn sử dụng mã nguồn cho mục đích thương mại.

---
*Chúc bạn sớm đắc đạo thành tiên!*
