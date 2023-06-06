package com.salvador.artapp.domain.domain_models.exhibit.new_exhibit

data class FullExhibitModel(
    val config: ConfigModel = ConfigModel(),
    val exhibits: List<ExhibitModel> = listOf(),
    val pagination: PaginationModel = PaginationModel()
)