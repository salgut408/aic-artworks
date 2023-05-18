package com.salvador.artapp.domain.domain_models

data class ResponseModel(
    val config: ConfigModel = ConfigModel(),
    val artWork: List<ArtworkModel> = listOf(),
    val info: InfoModel = InfoModel(),
    val pagination: PaginationModel = PaginationModel()
)