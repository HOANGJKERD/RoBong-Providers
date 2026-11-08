package com.robong.providers

import com.robong.providers.core.*

/**
 * Nguồn json.vnres.co
 * - https://json.vnres.co/all_live_rooms.json
 * - https://json.vnres.co/match_recommend.json
 */
class VnResProvider(
    private val httpGet: suspend (String) -> String
) : BaseProvider {

    override val name = "VnRes"
    override val key = "vnres"
    override val priority = 25

    override suspend fun getRealMainUrl(): String = DomainConfig.get("vnres")

    override suspend fun fetchMatches(sport: String): List<Match> {
        val base = getRealMainUrl()
        val json = httpGet("$base/all_live_rooms.json?v=${System.currentTimeMillis()}")
        // TODO: Parse JSON
        return emptyList()
    }

    override suspend fun loadLink(id: String): List<StreamLink> {
        return emptyList()
    }
}
