package com.lindenlabs.repospotlight

import com.lindenlabs.repospotlight.data.models.Contributor
import com.lindenlabs.repospotlight.data.models.OwnerModel
import com.lindenlabs.repospotlight.data.models.RepoModel

object TestData {
    val repoModel = RepoModel(
        id = "id",
        description = "Description",
        nodeId = "",
        name = "",
        fullName = "",
        private = false,
        owner = OwnerModel("Owner", "owner_id", "node_id", "some_url"),
        contributorsUrl = "",
        topics = listOf(),
        starGazersCount = 200,
        htmlUrl = ""
    )

    val contributors = listOf(
        Contributor("User", "id", "Avatar_url", "Url", contributions = 200)
    )
}