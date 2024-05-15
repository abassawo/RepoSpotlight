package com.lindenlabs.repospotlight.data

import com.google.gson.Gson
import com.lindenlabs.repospotlight.data.models.RepoModel
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

interface AppDataSource {
    suspend fun getPopularRepos(): List<RepoModel>
}

class AppRepository : AppDataSource {
    override suspend fun getPopularRepos(): List<RepoModel> {
        return api.popularRepos().items
    }

    companion object {
        private val BASE_URL = "https://api.github.com"
        private val api = Retrofit.Builder().baseUrl(BASE_URL)
            .client(OkHttpClient())
            .addConverterFactory(GsonConverterFactory.create(Gson()))
            .build().create(RestApi::class.java)
    }
}