package com.salvador.artapp.domain.domain_models

data class ResponseModelModel(
    val config: ConfigModel = ConfigModel(),
    val `data`: List<DataModel> = listOf(),
    val info: InfoModel = InfoModel(),
    val pagination: PaginationModel = PaginationModel()
)