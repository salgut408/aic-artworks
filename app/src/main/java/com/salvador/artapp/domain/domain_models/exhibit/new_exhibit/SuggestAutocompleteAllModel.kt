package com.salvador.artapp.domain.domain_models.exhibit.new_exhibit

data class SuggestAutocompleteAllModel(
    val contexts: ContextsModel? = ContextsModel(),
    val input: List<String?>? = listOf()
)