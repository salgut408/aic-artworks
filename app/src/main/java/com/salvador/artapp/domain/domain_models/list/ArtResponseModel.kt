package com.salvador.artapp.domain.domain_models.list

data class ArtResponseModel(
    val config: ConfigModel = ConfigModel(),
    val artWorks: List<ArtworkModel> = listOf(),
    val info: InfoModel = InfoModel(),
    val pagination: PaginationModel = PaginationModel()
)