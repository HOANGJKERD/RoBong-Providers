package com.robong.providers

import com.robong.providers.core.*

/**
 * Nguồn FreeM3u
 * API: https://freem3u.xyz/api/channels/x_1.0.1/app.json
 *
 * Cần inject HttpClient (OkHttp / Ktor) từ project RoBong.
 */
class FreeM3uProvider(
    private val httpGet: suspend (String) -> String
) : BaseProvider {

    override val name = "FreeM3U"
    override val key = "freem3u"
    override val priority = 20

    override suspend fun getRealMainUrl(): String {
        return DomainConfig.get("freem3u")
    }

    override suspend fun fetchMatches(sport: String): List<Match> {
        val base = getRealMainUrl()
        val json = httpGet("$base/api/channels/x_1.0.1/app.json")
        // TODO: Parse JSON theo response thật của freem3u
        // Hiện để skeleton
        return emptyList()
    }

    override suspend fun loadLink(id: String): List<StreamLink> {
        // TODO: Implement theo response API
        return emptyList()
    }
}
