package com.salvador.artapp.domain.domain_models.list

data class InfoModel(
    val licenseLinks: List<String> = listOf(),
    val licenseText: String = "",
    val version: String = ""
)