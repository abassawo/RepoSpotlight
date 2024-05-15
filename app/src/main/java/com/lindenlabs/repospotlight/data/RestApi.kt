package com.lindenlabs.repospotlight.data

import com.lindenlabs.repospotlight.data.models.RawSpotlightResponse
import retrofit2.http.GET

interface RestApi {
    @GET("/search/repositories?q=stars:>0")
    suspend fun popularRepos(): RawSpotlightResponse
}