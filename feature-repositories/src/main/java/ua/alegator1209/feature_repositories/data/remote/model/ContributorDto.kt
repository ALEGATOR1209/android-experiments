package ua.alegator1209.feature_repositories.data.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class ContributorDto(
    val login: String,
    val id: Int,
    @SerialName("avatar_url")
    val avatarUrl: String,
    val contributions: Int,
)
