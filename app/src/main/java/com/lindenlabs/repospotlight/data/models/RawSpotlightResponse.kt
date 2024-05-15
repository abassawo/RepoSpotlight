package com.lindenlabs.repospotlight.data.models

import com.google.gson.annotations.SerializedName

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
    @SerializedName("contributors_url") val contributorsUrl: String?,
    val topics: List<String>?,
    val starGazersCount: Int?,
    @SerializedName("html_url") val htmlUrl: String
)

sealed class

data

class OwnerModel(
    val login: String,
    val id: String,
    val nodeId: String,
    val avatarUrl: String
)