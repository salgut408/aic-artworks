package com.salvador.artapp.domain.domain_models.exhibit.new_exhibit

data class InfoModel(
    val license_links: List<String?>? = listOf(),
    val license_text: String? = "",
    val version: String? = ""
)