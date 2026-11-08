package com.robong.providers.core

/**
 * Model chung cho RoBong Providers
 */

data class Match(
    val id: String,
    val title: String,
    val league: String? = null,
    val homeTeam: String? = null,
    val awayTeam: String? = null,
    val startTime: Long? = null,
    val poster: String? = null,
    val provider: String,
    val isLive: Boolean = false,
    val extra: Map<String, String> = emptyMap()
)

data class Channel(
    val id: String,
    val name: String,
    val group: String? = null,
    val logo: String? = null,
    val url: String? = null,
    val tvgId: String? = null,
    val provider: String
)

data class StreamLink(
    val url: String,
    val quality: String = "Auto",
    val headers: Map<String, String> = emptyMap(),
    val type: StreamType = StreamType.HLS,
    val label: String? = null
)

enum class StreamType {
    HLS, DASH, FLV, PROGRESSIVE, OTHER
}

data class EpgItem(
    val title: String,
    val start: Long,
    val end: Long,
    val description: String? = null
)
