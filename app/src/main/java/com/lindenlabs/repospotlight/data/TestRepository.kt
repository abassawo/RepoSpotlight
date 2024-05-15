package com.lindenlabs.repospotlight.data

import android.content.Context
import com.google.gson.Gson
import com.lindenlabs.repospotlight.R
import com.lindenlabs.repospotlight.data.models.RawSpotlightResponse
import com.lindenlabs.repospotlight.data.models.RepoModel
import java.io.InputStreamReader

class TestRepository(val context: Context) : AppDataSource {

    override suspend fun getPopularRepos(page: Int): List<RepoModel> {
        val inputStream =
            context.resources.openRawResource(R.raw.spotlight_repos_200) // Replace `your_json_file` with the actual filename without extension

        val reader = InputStreamReader(inputStream)
        val rawSpotlightResponse = Gson().fromJson(reader, RawSpotlightResponse::class.java)
        return rawSpotlightResponse.items
    }
}