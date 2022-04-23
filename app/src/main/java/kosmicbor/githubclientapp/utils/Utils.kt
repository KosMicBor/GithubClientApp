package kosmicbor.githubclientapp.utils

import kosmicbor.githubclientapp.data.room.LocalUserEntity
import kosmicbor.githubclientapp.domain.GithubUserEntity

fun convertEntityToUsersList(entityList: List<LocalUserEntity>): List<GithubUserEntity> {
    return entityList.map {
        GithubUserEntity(
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

fun convertGithubUserEntityToLocalEntity(githubUserEntity: GithubUserEntity): LocalUserEntity {
    return LocalUserEntity(
        login = githubUserEntity.login,
        id = githubUserEntity.id,
        nodeId = githubUserEntity.nodeId,
        avatarUrl = githubUserEntity.avatarUrl,
        followersUrl = githubUserEntity.followersUrl,
        followingUrl = githubUserEntity.followingUrl,
        subscriptionsUrl = githubUserEntity.subscriptionsUrl,
        organizationsUrl = githubUserEntity.organizationsUrl,
        reposUrl = githubUserEntity.reposUrl,
        eventsUrl = githubUserEntity.eventsUrl,
        type = githubUserEntity.type,
        siteAdmin = githubUserEntity.siteAdmin,
        name = githubUserEntity.name,
        company = githubUserEntity.company,
        blog = githubUserEntity.blog,
        location = githubUserEntity.location,
        email = githubUserEntity.email,
        bio = githubUserEntity.bio,
        createdAt = githubUserEntity.createdAt,
        updatedAt = githubUserEntity.updatedAt
    )
}

fun convertLocalEntityToGithubUserEntity(localUserEntity: LocalUserEntity): GithubUserEntity {
    return GithubUserEntity(
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