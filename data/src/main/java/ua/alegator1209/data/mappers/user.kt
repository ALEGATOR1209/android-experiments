package ua.alegator1209.data.mappers

import ua.alegator1209.core.domain.model.User
import ua.alegator1209.data.model.UserDto

internal fun UserDto.toUser() = User(
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
    following = following,
)

internal fun User.toDto() = UserDto(
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
    following = following,
)
