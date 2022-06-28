package {{applicationId}}.data.repository.items.remote

import {{applicationId}}.data.model.HNItem
import {{applicationId}}.data.model.HNTopStories
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

// Official HN API documentation: https://github.com/{{pascalCase appName}}/API
interface {{pascalCase appName}}Api {

    @GET("topstories.json")
    suspend fun getTopStories(): Response<HNTopStories>

    @GET("item/{itemId}.json")
    suspend fun getItemDetails(
        @Path("itemId") itemId: Long
    ): Response<HNItem>

}