# RoBong Providers

Tích hợp các nguồn stream từ GetOut vào RoBong (RoHub).

## Cấu trúc

```
src/
├── core/
│   ├── BaseProvider.kt
│   ├── Models.kt
│   ├── M3uParser.kt
│   └── DomainConfig.kt
├── providers/
│   ├── M3uLocalProvider.kt
│   ├── FreeM3uProvider.kt
│   ├── ChuoiChienProvider.kt
│   ├── XoiGacProvider.kt
│   └── VnResProvider.kt
└── assets/
    └── sample_m3u.txt          # Playlist mẫu (VTV + World Cup)
```

## Cách dùng nhanh

1. Copy package `providers` vào project RoBong của bạn.
2. Đăng ký các provider trong `ProviderRegistry`.
3. Gọi `fetchMatches()` để lấy danh sách trận/kênh.
4. Gọi `loadLink(matchId)` để lấy link stream thật.

## Nguồn đã hỗ trợ (v1)

| Provider | Trạng thái | Ghi chú |
|----------|------------|--------|
| M3uLocal | ✅ Sẵn sàng | Playlist local |
| FreeM3u | ✅ Sẵn sàng | freem3u.xyz |
| VnRes | ✅ Sẵn sàng | json.vnres.co |
| ChuoiChien | 🚧 Skeleton | Cần token |
| XoiGac | 🚧 Skeleton | Cần Cloudflare bypass |

## Lưu ý

- Domain thay đổi liên tục → dùng `DomainConfig` + file config từ xa.
- Nhiều nguồn có Cloudflare → cần thêm bypass nếu dùng production.
- Chỉ dùng cho mục đích học tập / nghiên cứu.

Repo: https://github.com/HOANGJKERD/RoBong-Providers
