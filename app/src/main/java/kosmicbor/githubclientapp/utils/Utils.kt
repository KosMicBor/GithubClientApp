package kosmicbor.githubclientapp.utils

import kosmicbor.githubclientapp.data.room.LocalUserEntity
import kosmicbor.githubclientapp.data.retrofit.GithubUserEntityDTO
import kosmicbor.githubclientapp.domain.GithubUser

fun convertEntityToUsersList(entityList: List<LocalUserEntity>): List<GithubUserEntityDTO> {
    return entityList.map {
        GithubUserEntityDTO(
            login = it.login,
            id = it.id,
            nodeId = it.nodeId,
            avatarUrl = it.avatarUrl,
            followersUrl = it.followersUrl,
            followingUrl = it.followingUrl,
            subscriptionsUrl = it.subscriptionsUrl,
            organizationsUrl = it.organizationsUrl,
            reposUrl = it.reposUrl,
            eventsUrl = it.eventsUrl,
            type = it.type,
            siteAdmin = it.siteAdmin,
            name = it.name,
            company = it.company,
            blog = it.blog,
            location = it.location,
            email = it.email,
            bio = it.bio,
            createdAt = it.createdAt,
            updatedAt = it.updatedAt
        )
    }
}

fun convertLocalUsersListToUsersList(entityList: List<LocalUserEntity>): List<GithubUser> {
    return entityList.map {
        GithubUser(
            login = it.login,
            id = it.id,
            nodeId = it.nodeId,
            avatarUrl = it.avatarUrl,
            followersUrl = it.followersUrl,
            followingUrl = it.followingUrl,
            subscriptionsUrl = it.subscriptionsUrl,
            organizationsUrl = it.organizationsUrl,
            reposUrl = it.reposUrl,
            eventsUrl = it.eventsUrl,
            type = it.type,
            siteAdmin = it.siteAdmin,
            name = it.name,
            company = it.company,
            blog = it.blog,
            location = it.location,
            email = it.email,
            bio = it.bio,
            createdAt = it.createdAt,
            updatedAt = it.updatedAt
        )
    }
}

fun convertGithubUserToLocalUserEntity(githubUser: GithubUser): LocalUserEntity {
    return LocalUserEntity(
        login = githubUser.login,
        id = githubUser.id,
        nodeId = githubUser.nodeId,
        avatarUrl = githubUser.avatarUrl,
        followersUrl = githubUser.followersUrl,
        followingUrl = githubUser.followingUrl,
        subscriptionsUrl = githubUser.subscriptionsUrl,
        organizationsUrl = githubUser.organizationsUrl,
        reposUrl = githubUser.reposUrl,
        eventsUrl = githubUser.eventsUrl,
        type = githubUser.type,
        siteAdmin = githubUser.siteAdmin,
        name = githubUser.name,
        company = githubUser.company,
        blog = githubUser.blog,
        location = githubUser.location,
        email = githubUser.email,
        bio = githubUser.bio,
        createdAt = githubUser.createdAt,
        updatedAt = githubUser.updatedAt
    )
}

fun convertLocalUserEntityToGithubUser(localUser: LocalUserEntity): GithubUser {
    return GithubUser(
        login = localUser.login,
        id = localUser.id,
        nodeId = localUser.nodeId,
        avatarUrl = localUser.avatarUrl,
        followersUrl = localUser.followersUrl,
        followingUrl = localUser.followingUrl,
        subscriptionsUrl = localUser.subscriptionsUrl,
        organizationsUrl = localUser.organizationsUrl,
        reposUrl = localUser.reposUrl,
        eventsUrl = localUser.eventsUrl,
        type = localUser.type,
        siteAdmin = localUser.siteAdmin,
        name = localUser.name,
        company = localUser.company,
        blog = localUser.blog,
        location = localUser.location,
        email = localUser.email,
        bio = localUser.bio,
        createdAt = localUser.createdAt,
        updatedAt = localUser.updatedAt
    )
}

fun convertUserDtoToGithubUser(githubUserEntityDTO: GithubUserEntityDTO): GithubUser {
    return GithubUser(
        login = githubUserEntityDTO.login,
        id = githubUserEntityDTO.id,
        nodeId = githubUserEntityDTO.nodeId,
        avatarUrl = githubUserEntityDTO.avatarUrl,
        followersUrl = githubUserEntityDTO.followersUrl,
        followingUrl = githubUserEntityDTO.followingUrl,
        subscriptionsUrl = githubUserEntityDTO.subscriptionsUrl,
        organizationsUrl = githubUserEntityDTO.organizationsUrl,
        reposUrl = githubUserEntityDTO.reposUrl,
        eventsUrl = githubUserEntityDTO.eventsUrl,
        type = githubUserEntityDTO.type,
        siteAdmin = githubUserEntityDTO.siteAdmin,
        name = githubUserEntityDTO.name,
        company = githubUserEntityDTO.company,
        blog = githubUserEntityDTO.blog,
        location = githubUserEntityDTO.location,
        email = githubUserEntityDTO.email,
        bio = githubUserEntityDTO.bio,
        createdAt = githubUserEntityDTO.createdAt,
        updatedAt = githubUserEntityDTO.updatedAt
    )
}

fun convertGithubUserToUserDto(githubUser: GithubUser): GithubUserEntityDTO {
    return GithubUserEntityDTO(
        login = githubUser.login,
        id = githubUser.id,
        nodeId = githubUser.nodeId,
        avatarUrl = githubUser.avatarUrl,
        followersUrl = githubUser.followersUrl,
        followingUrl = githubUser.followingUrl,
        subscriptionsUrl = githubUser.subscriptionsUrl,
        organizationsUrl = githubUser.organizationsUrl,
        reposUrl = githubUser.reposUrl,
        eventsUrl = githubUser.eventsUrl,
        type = githubUser.type,
        siteAdmin = githubUser.siteAdmin,
        name = githubUser.name,
        company = githubUser.company,
        blog = githubUser.blog,
        location = githubUser.location,
        email = githubUser.email,
        bio = githubUser.bio,
        createdAt = githubUser.createdAt,
        updatedAt = githubUser.updatedAt
    )
}

//Entities converting

fun convertLocalEntityToGithubUserEntity(localUserEntity: LocalUserEntity): GithubUserEntityDTO {
    return GithubUserEntityDTO(
        login = localUserEntity.login,
        id = localUserEntity.id,
        nodeId = localUserEntity.nodeId,
        avatarUrl = localUserEntity.avatarUrl,
        followersUrl = localUserEntity.followersUrl,
        followingUrl = localUserEntity.followingUrl,
        subscriptionsUrl = localUserEntity.subscriptionsUrl,
        organizationsUrl = localUserEntity.organizationsUrl,
        reposUrl = localUserEntity.reposUrl,
        eventsUrl = localUserEntity.eventsUrl,
        type = localUserEntity.type,
        siteAdmin = localUserEntity.siteAdmin,
        name = localUserEntity.name,
        company = localUserEntity.company,
        blog = localUserEntity.blog,
        location = localUserEntity.location,
        email = localUserEntity.email,
        bio = localUserEntity.bio,
        createdAt = localUserEntity.createdAt,
        updatedAt = localUserEntity.updatedAt
    )
}

fun convertGithubUserEntityToLocalEntity(githubUserEntityDTO: GithubUserEntityDTO): LocalUserEntity {
    return LocalUserEntity(
        login = githubUserEntityDTO.login,
        id = githubUserEntityDTO.id,
        nodeId = githubUserEntityDTO.nodeId,
        avatarUrl = githubUserEntityDTO.avatarUrl,
        followersUrl = githubUserEntityDTO.followersUrl,
        followingUrl = githubUserEntityDTO.followingUrl,
        subscriptionsUrl = githubUserEntityDTO.subscriptionsUrl,
        organizationsUrl = githubUserEntityDTO.organizationsUrl,
        reposUrl = githubUserEntityDTO.reposUrl,
        eventsUrl = githubUserEntityDTO.eventsUrl,
        type = githubUserEntityDTO.type,
        siteAdmin = githubUserEntityDTO.siteAdmin,
        name = githubUserEntityDTO.name,
        company = githubUserEntityDTO.company,
        blog = githubUserEntityDTO.blog,
        location = githubUserEntityDTO.location,
        email = githubUserEntityDTO.email,
        bio = githubUserEntityDTO.bio,
        createdAt = githubUserEntityDTO.createdAt,
        updatedAt = githubUserEntityDTO.updatedAt
    )
}