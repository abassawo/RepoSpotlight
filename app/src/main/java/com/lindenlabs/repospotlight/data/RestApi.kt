package com.lindenlabs.repospotlight.data

import com.lindenlabs.repospotlight.data.models.RawSpotlightResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface RestApi {

    @GET("/search/repositories?q=stars:>0")
    suspend fun popularRepos(
        @Query("page") page: Int,
        @Query("per_page") pageSize: Int = 25
    ): RawSpotlightResponse
}