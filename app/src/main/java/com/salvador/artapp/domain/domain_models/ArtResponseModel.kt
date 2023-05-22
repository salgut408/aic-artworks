package com.salvador.artapp.domain.domain_models

data class ArtResponseModel(
    val config: ConfigModel = ConfigModel(),
    val artWorks: List<ArtworkModel> = listOf(),
    val info: InfoModel = InfoModel(),
    val pagination: PaginationModel = PaginationModel()
)