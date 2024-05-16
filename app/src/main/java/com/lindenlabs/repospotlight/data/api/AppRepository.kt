package com.lindenlabs.repospotlight.data.api

import com.google.gson.Gson
import com.lindenlabs.repospotlight.data.models.Contributor
import com.lindenlabs.repospotlight.data.models.RepoModel
import com.squareup.leakcanary.core.BuildConfig
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException

class AppRepository : AppDataSource {
    override suspend fun getPopularRepos(page: Int): List<RepoModel> {
        return api.popularRepos(page).items
    }

    override suspend fun getTopContributors(repoModel: RepoModel): List<Contributor> {
        val client = OkHttpClient()
        val url = repoModel.contributorsUrl

        return url?.let {
            val request = Request.Builder()
                .url(url)
                .build()
            val response = withContext(Dispatchers.IO) {
                client.newCall(request).execute()
            }
            if (!response.isSuccessful) {
                throw IOException("API failure $response")
            }
            val body = response.body?.string()
            return Gson().fromJson(body.toString(), Array<Contributor>::class.java).toList()
        } ?: emptyList()

    }

    companion object {
        private val BASE_URL = "https://api.github.com"
        private val api = Retrofit.Builder().baseUrl(BASE_URL)
            .client(OkHttpClient())
            .addConverterFactory(GsonConverterFactory.create(Gson()))
            .build().create(SpotlightApi::class.java)
    }
}