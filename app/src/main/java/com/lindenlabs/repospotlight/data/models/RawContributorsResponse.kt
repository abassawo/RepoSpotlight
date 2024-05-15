package com.lindenlabs.repospotlight.data.models

import com.google.gson.annotations.SerializedName

data class Contributor(@SerializedName("login") val userName: String, val id: String, @SerializedName("avatar_url") val avatarUrl: String, val url: String, val contributions: Int)