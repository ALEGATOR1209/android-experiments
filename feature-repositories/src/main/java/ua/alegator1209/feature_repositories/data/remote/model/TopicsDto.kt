package ua.alegator1209.feature_repositories.data.remote.model

import kotlinx.serialization.Serializable

@Serializable
internal data class TopicsDto(val names: List<String>)
