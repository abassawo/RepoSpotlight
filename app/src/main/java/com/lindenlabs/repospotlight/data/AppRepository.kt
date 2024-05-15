package com.lindenlabs.repospotlight.data

import com.google.gson.Gson
import com.lindenlabs.repospotlight.data.models.RepoModel
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

interface AppDataSource {
    suspend fun getPopularRepos(): List<RepoModel>
}

class AppRepository @Inject constructor() : AppDataSource {
    override suspend fun getPopularRepos(): List<RepoModel> {
        return api.popularRepos().items
    }

    companion object {
        val BASE_URL = "https://api.github.com"
        val api = Retrofit.Builder().baseUrl(BASE_URL)
            .client(OkHttpClient())
            .addConverterFactory(GsonConverterFactory.create(Gson()))
            .build().create(RestApi::class.java)
    }

}