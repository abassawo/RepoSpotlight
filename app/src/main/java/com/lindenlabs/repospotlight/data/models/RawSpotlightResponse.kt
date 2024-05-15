package com.lindenlabs.repospotlight.data.models

data class RawSpotlightResponse(
    val totalCount: Int,
    val incompleteResults: Boolean,
    val items: List<RepoModel>
)

data class RepoModel(
    val id: String?,
    val description: String?,
    val nodeId: String?,
    val name: String?,
    val fullName: String?,
    val private: Boolean?,
    val owner: OwnerModel?,
    val collaboratorsUrl: String?,
    val topics: List<String>?,
    val starGazersCount: Int?
)

data class OwnerModel(
    val login: String,
    val id: String,
    val nodeId: String,
    val avatarUrl: String
)