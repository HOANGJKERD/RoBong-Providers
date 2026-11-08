package com.robong.providers

import com.robong.providers.core.*

/**
 * ChuoiChien Provider
 *
 * Flow thật từ GetOut:
 * 1. POST https://api.chuoichientv.com/v1/guest-token/generate
 * 2. GET  https://api.chuoichientv.com/v1/encrypted/token
 * 3. GET  https://api-v2.chuoichientv.com/v2/matches?page=1&limit=100&sport=football&type=blv
 * 4. loadLink(matchId) → lấy m3u8
 *
 * Cần inject HttpClient hỗ trợ POST + header.
 */
class ChuoiChienProvider(
    private val httpGet: suspend (String, Map<String, String>) -> String,
    private val httpPost: suspend (String, String, Map<String, String>) -> String
) : BaseProvider {

    override val name = "Chuỗi Chiến"
    override val key = "chuoichien"
    override val priority = 30

    private var guestToken: String? = null

    override suspend fun getRealMainUrl(): String = DomainConfig.get("chuoichien")

    private suspend fun ensureToken() {
        if (guestToken != null) return
        // TODO: Gọi API guest-token/generate và encrypted/token
        // guestToken = ...
    }

    override suspend fun fetchMatches(sport: String): List<Match> {
        ensureToken()
        val url = "https://api-v2.chuoichientv.com/v2/matches?page=1&limit=100&sport=$sport&type=blv"
        val headers = mapOf(
            "Authorization" to "Bearer ${guestToken.orEmpty()}",
            "User-Agent" to "Mozilla/5.0"
        )
        val json = httpGet(url, headers)
        // TODO: Parse danh sách trận
        return emptyList()
    }

    override suspend fun loadLink(id: String): List<StreamLink> {
        ensureToken()
        // TODO: Gọi API lấy stream theo id
        return emptyList()
    }
}
