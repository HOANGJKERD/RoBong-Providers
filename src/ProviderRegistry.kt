package com.robong.providers

import com.robong.providers.core.BaseProvider

/**
 * Đăng ký tất cả provider tại một chỗ.
 * Chỉ cần inject HttpClient là dùng được.
 */
object ProviderRegistry {

    fun createAll(
        m3uContent: String,
        httpGet: suspend (String) -> String,
        httpGetWithHeaders: suspend (String, Map<String, String>) -> String,
        httpPost: suspend (String, String, Map<String, String>) -> String
    ): List<BaseProvider> {
        return listOf(
            M3uLocalProvider(m3uContent),
            FreeM3uProvider(httpGet),
            VnResProvider(httpGet),
            ChuoiChienProvider(httpGetWithHeaders, httpPost),
            XoiGacProvider(httpGetWithHeaders)
            // Thêm BiaOm, GaVang, VSC... sau
        ).sortedBy { it.priority }
    }
}
