package com.robong.providers.core

/**
 * Parser M3U đơn giản, đủ dùng cho playlist GetOut / IPTV
 */
object M3uParser {

    fun parse(content: String, providerName: String = "m3u"): List<Channel> {
        val lines = content.lines()
        val result = mutableListOf<Channel>()

        var i = 0
        while (i < lines.size) {
            val line = lines[i].trim()
            if (line.startsWith("#EXTINF", ignoreCase = true)) {
                val info = parseExtInf(line)
                val url = lines.getOrNull(i + 1)?.trim()
                if (!url.isNullOrBlank() && !url.startsWith("#")) {
                    result += Channel(
                        id = info["tvg-id"] ?: url.hashCode().toString(),
                        name = info["name"] ?: "Unknown",
                        group = info["group-title"],
                        logo = info["tvg-logo"],
                        url = url,
                        tvgId = info["tvg-id"],
                        provider = providerName
                    )
                    i++
                }
            }
            i++
        }
        return result
    }

    private fun parseExtInf(line: String): Map<String, String> {
        val map = mutableMapOf<String, String>()

        // name nằm sau dấu phẩy cuối
        val commaIdx = line.lastIndexOf(',')
        if (commaIdx != -1 && commaIdx < line.length - 1) {
            map["name"] = line.substring(commaIdx + 1).trim()
        }

        // attributes: tvg-id="..." group-title="..." ...
        val attrRegex = Regex("""([\w-]+)=\"([^\"]*)\"""")
        attrRegex.findAll(line).forEach { m ->
            map[m.groupValues[1]] = m.groupValues[2]
        }
        return map
    }
}
