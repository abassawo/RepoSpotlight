package com.lindenlabs.repospotlight.data.api

import com.lindenlabs.repospotlight.data.models.RawSpotlightResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface SpotlightApi {

    @GET("/search/repositories?q=stars:>0")
    suspend fun popularRepos(
        @Query("page") page: Int,
        @Query("per_page") pageSize: Int = 25
    ): RawSpotlightResponse

}