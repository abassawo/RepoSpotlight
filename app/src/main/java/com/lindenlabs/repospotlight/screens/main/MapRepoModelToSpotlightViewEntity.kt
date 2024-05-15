package com.lindenlabs.repospotlight.screens.main

import com.lindenlabs.repospotlight.data.models.RepoModel
import javax.inject.Inject

class MapRepoModelToSpotlightViewEntity @Inject constructor() {

    operator fun invoke(
        repositories: List<RepoModel>
    ): List<SpotlightRepoViewEntity> {
        return repositories.map { it.toViewEntity() }
    }

    private fun RepoModel.toViewEntity(): SpotlightRepoViewEntity {
        return SpotlightRepoViewEntity(
            this,
            isRelatedToAndroid = topics?.isAndroidRelated() == true
        )
    }

    private fun List<String>.isAndroidRelated(): Boolean {
        return this.contains("Android") || this.contains("android")
    }
}



