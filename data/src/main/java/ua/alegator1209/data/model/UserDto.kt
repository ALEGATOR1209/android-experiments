package ua.alegator1209.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserDto(
    val login: String,
    val id: Int,
    @SerialName("avatar_url")
    val avatarUrl: String,
    val type: String,
    val name: String,
    val company: String? = null,
    val blog: String? = null,
    val location: String? = null,
    val email: String? = null,
    @SerialName("public_repos")
    val publicRepos: Int,
    @SerialName("public_gists")
    val publicGists: Int,
    val followers: Int,
    val following: Int,
)
