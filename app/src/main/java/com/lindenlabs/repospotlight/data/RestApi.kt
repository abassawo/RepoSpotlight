package com.lindenlabs.repospotlight.data

import com.lindenlabs.repospotlight.data.models.RawSpotlightResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface RestApi {
//    @GET("/search/repositories?q=stars:>0")
//    suspend fun popularRepos(): RawSpotlightResponse

    @GET("/search/repositories?q=stars:>0")
    suspend fun popularRepos(
        @Query("per_page") pageSize: Int = 100,
        @Query("page") page: Int = 1
    ): RawSpotlightResponse
}