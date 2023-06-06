package com.salvador.artapp.data.remote.network_responses.exhibitions.new_exhibits


import com.google.gson.annotations.SerializedName

data class SuggestAutocompleteAll(
    @SerializedName("contexts")
    val contexts: Contexts? = Contexts(),
    @SerializedName("input")
    val input: List<String?>? = listOf()
)