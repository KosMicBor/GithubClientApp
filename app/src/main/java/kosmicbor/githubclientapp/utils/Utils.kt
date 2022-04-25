package kosmicbor.githubclientapp.utils

import kosmicbor.githubclientapp.data.room.LocalUserDto
import kosmicbor.githubclientapp.data.retrofit.GithubUserDto
import kosmicbor.githubclientapp.domain.GithubUser

fun convertLocalUsersListToUsersList(dtoList: List<LocalUserDto>): List<GithubUser> {
    return dtoList.map {
        GithubUser(
            login = it.login,
            avatarUrl = it.avatarUrl
        )
    }
}

fun convertGithubUserDtoToLocalUserDto(githubUser: GithubUser): LocalUserDto {
    return LocalUserDto(
        login = githubUser.login,
        avatarUrl = githubUser.avatarUrl
    )
}

fun convertLocalUserDtoToGithubUser(localUser: LocalUserDto): GithubUser {
    return GithubUser(
        login = localUser.login,
        avatarUrl = localUser.avatarUrl
    )
}

fun convertGithubUserDtoToGithubUser(githubUserDto: GithubUserDto): GithubUser {
    return GithubUser(
        login = githubUserDto.login,
        avatarUrl = githubUserDto.avatarUrl
    )
}