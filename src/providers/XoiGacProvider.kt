package com.robong.providers

import com.robong.providers.core.*

/**
 * XoiGac Provider
 *
 * Lưu ý: Thường bị Cloudflare.
 * GetOut dùng class CloudflareKiller để bypass.
 *
 * Flow:
 * - getRealMainUrl() → xoigac.top / xoigac.pro
 * - Lấy token (tokenAsync)
 * - Lấy stream (streamAsync)
 */
class XoiGacProvider(
    private val httpGet: suspend (String, Map<String, String>) -> String
) : BaseProvider {

    override val name = "Xôi Gác"
    override val key = "xoigac"
    override val priority = 40

    override suspend fun getRealMainUrl(): String = DomainConfig.get("xoigac")

    override suspend fun fetchMatches(sport: String): List<Match> {
        val base = getRealMainUrl()
        // TODO: Implement theo API thật của XoiGac
        return emptyList()
    }

    override suspend fun loadLink(id: String): List<StreamLink> {
        // TODO: token → stream
        return emptyList()
    }
}
