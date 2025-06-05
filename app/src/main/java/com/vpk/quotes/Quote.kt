package com.vpk.quotes

import kotlinx.serialization.Serializable

@Serializable
data class Quote(
    val text: String,
    val author: String
)