package ua.alegator1209.core.domain.model

data class User(
    val login: String,
    val id: Int,
    val avatarUrl: String,
    val type: String,
    val name: String,
    val company: String? = null,
    val blog: String? = null,
    val location: String? = null,
    val email: String? = null,
    val publicRepos: Int,
    val publicGists: Int,
    val followers: Int,
    val following: Int,
)
