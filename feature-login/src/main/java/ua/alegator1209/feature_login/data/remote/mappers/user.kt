package ua.alegator1209.feature_login.data.remote.mappers

import ua.alegator1209.core.domain.model.User
import ua.alegator1209.feature_login.data.remote.model.UserDto

fun UserDto.toUser() = User(
    login = login,
    id = id,
    avatarUrl = avatarUrl,
    type = type,
    name = name,
    company = company,
    blog = blog,
    location = location,
    email = email,
    publicRepos = publicRepos,
    publicGists = publicGists,
    followers = followers,
    following = following
)
