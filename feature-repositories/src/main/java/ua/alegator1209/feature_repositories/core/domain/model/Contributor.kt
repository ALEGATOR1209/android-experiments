package ua.alegator1209.feature_repositories.core.domain.model

internal data class Contributor(
    val login: String,
    val id: Int,
    val avatarUrl: String,
    val contributions: Int,
)
