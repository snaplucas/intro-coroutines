package tasks

import contributors.*
import kotlinx.coroutines.*

suspend fun loadContributorsConcurrent(service: GitHubService, req: RequestData): List<User> = coroutineScope {
    val repos = service.getOrgRepos(req.org)
        .also { logRepos(req, it) }
        .body() ?: listOf()

    val allUsers = repos.map { repo ->
        async {
            service.getRepoContributor(req.org, repo.name)
                .also { logUsers(repo, it) }
                .body() ?: listOf()
        }
    }.awaitAll().flatten()
    allUsers.aggregate()
}