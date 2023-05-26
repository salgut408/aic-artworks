package com.salvador.artapp.domain.domain_models.exhibit

import com.salvador.artapp.data.remote.network_responses.exhibitions.Exhibit

data class ExhibitionsResponseModel(
    val exhibits: List<ExhibitModel> = listOf(),
    )