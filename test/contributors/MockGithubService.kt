package contributors

import kotlinx.coroutines.delay
import retrofit2.Call
import retrofit2.Response
import retrofit2.mock.Calls

object MockGithubService : GitHubService {
    override fun getOrgReposCall(org: String): Call<List<Repo>> {
        return Calls.response(repos)
    }

    override fun getRepoContributorsCall(owner: String, repo: String): Call<List<User>> {
        return Calls.response(reposMap.getValue(repo).users)
    }

    override suspend fun getOrgRepos(org: String): Response<List<Repo>> {
        return Response.success(repos)
    }

    override suspend fun getRepoContributor(owner: String, repo: String): Response<List<User>> {
        return Response.success(reposMap.getValue(repo).users)
    }

/*
    // Uncomment the following implementations after adding these methods to GitHubService:

    override suspend fun getOrgRepos(org: String): Response<List<Repo>> {
        delay(reposDelay)
        return Response.success(repos)
    }

    override suspend fun getRepoContributors(owner: String, repo: String): Response<List<User>> {
        val testRepo = reposMap.getValue(repo)
        delay(testRepo.delay)
        return Response.success(testRepo.users)
    }
*/
}