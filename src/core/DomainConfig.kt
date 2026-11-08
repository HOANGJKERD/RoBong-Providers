package com.robong.providers.core

/**
 * Quản lý domain động.
 * Nên tự host file JSON config thay vì phụ thuộc GitHub của người khác.
 *
 * Ví dụ file config (bạn tự host):
 * {
 *   "xoigac": "https://xoigac.top",
 *   "chuoichien": "https://chuoichientv.com",
 *   "biaom": "https://biaomtv.pro",
 *   "gavang": "https://gavangtv33.live"
 * }
 */
object DomainConfig {

    // TODO: Thay bằng URL config của bạn
    private const val REMOTE_CONFIG_URL = "https://your-server.com/robong/domains.json"

    private val fallback = mapOf(
        "xoigac" to "https://xoigac.top",
        "xoilac" to "https://xoigac.top",
        "chuoichien" to "https://chuoichientv.com",
        "biaom" to "https://biaomtv.pro",
        "gavang" to "https://gavangtv33.live",
        "vsc" to "https://vsc9.com",
        "freem3u" to "https://freem3u.xyz",
        "vnres" to "https://json.vnres.co"
    )

    private var cache: Map<String, String> = fallback
    private var lastFetch: Long = 0
    private const val CACHE_TTL = 30 * 60 * 1000L // 30 phút

    suspend fun get(key: String): String {
        refreshIfNeeded()
        return cache[key] ?: fallback[key] ?: error("Unknown provider key: $key")
    }

    private suspend fun refreshIfNeeded() {
        val now = System.currentTimeMillis()
        if (now - lastFetch < CACHE_TTL) return

        try {
            // TODO: Dùng OkHttp / Ktor của project để fetch REMOTE_CONFIG_URL
            // val json = httpClient.get(REMOTE_CONFIG_URL)
            // cache = parseJson(json)
            lastFetch = now
        } catch (e: Exception) {
            // Giữ fallback
        }
    }

    fun updateLocal(key: String, url: String) {
        cache = cache + (key to url)
    }
}
