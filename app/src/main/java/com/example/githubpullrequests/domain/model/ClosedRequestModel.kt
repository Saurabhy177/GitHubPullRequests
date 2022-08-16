package com.example.githubpullrequests.domain.model

import androidx.annotation.Keep

@Keep
data class ClosedRequestModel(
    val username: String,
    val userImage: String,
    val title: String,
    val createdDate: String,
    val closedDate: String
)
