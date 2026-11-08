package com.robong.providers

import com.robong.providers.core.*

/**
 * Nguồn M3U local (copy từ assets/m3u.txt của GetOut)
 * Đây là nguồn ổn định nhất để bắt đầu.
 */
class M3uLocalProvider(
    private val m3uContent: String
) : BaseProvider {

    override val name = "GetOut M3U"
    override val key = "m3u_local"
    override val priority = 10

    private val channels by lazy {
        M3uParser.parse(m3uContent, name)
    }

    override suspend fun getRealMainUrl(): String = "local"

    override suspend fun fetchMatches(sport: String): List<Match> {
        // Coi mỗi channel như 1 "match" đang live
        return channels.map {
            Match(
                id = it.id,
                title = it.name,
                league = it.group,
                poster = it.logo,
                provider = name,
                isLive = true
            )
        }
    }

    override suspend fun fetchChannels(): List<Channel> = channels

    override suspend fun loadLink(id: String): List<StreamLink> {
        val ch = channels.find { it.id == id } ?: return emptyList()
        val url = ch.url ?: return emptyList()

        val type = when {
            url.contains(".m3u8", ignoreCase = true) -> StreamType.HLS
            url.contains(".flv", ignoreCase = true) -> StreamType.FLV
            else -> StreamType.OTHER
        }

        return listOf(
            StreamLink(
                url = url,
                quality = "Auto",
                type = type
            )
        )
    }
}
