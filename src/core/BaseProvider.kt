package com.robong.providers.core

/**
 * Interface chung cho mọi nguồn stream của RoBong
 */
interface BaseProvider {
    val name: String
    val key: String
    val priority: Int
        get() = 100

    /** Lấy domain thật (hỗ trợ domain động) */
    suspend fun getRealMainUrl(): String

    /** Lấy danh sách trận / sự kiện */
    suspend fun fetchMatches(sport: String = "football"): List<Match>

    /** Lấy danh sách kênh (nếu provider hỗ trợ TV) */
    suspend fun fetchChannels(): List<Channel> = emptyList()

    /** Lấy link xem thật của 1 trận/kênh */
    suspend fun loadLink(id: String): List<StreamLink>

    /** EPG (tuỳ chọn) */
    suspend fun getEpg(channelId: String): List<EpgItem> = emptyList()
}
