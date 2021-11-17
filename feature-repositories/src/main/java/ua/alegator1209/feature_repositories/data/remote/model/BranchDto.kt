package ua.alegator1209.feature_repositories.data.remote.model

import kotlinx.serialization.Serializable

@Serializable
internal data class BranchDto(
    val name: String,
    val protected: Boolean
)
