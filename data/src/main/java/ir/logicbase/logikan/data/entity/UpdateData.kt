package ir.logicbase.logikan.data.entity

import com.google.gson.annotations.SerializedName

data class UpdateData(
    @field:SerializedName("version_name")
    val versionName: String,
    @field:SerializedName("version_code")
    val versionCode: Int,
    @field:SerializedName("is_forced")
    val isForced: Boolean,
    @field:SerializedName("message")
    val message: String?,
    @field:SerializedName("url")
    val url: String,
    @field:SerializedName("deep_link")
    val deepLink: String?,
    @field:SerializedName("deep_link_package")
    val deepLinkPackage: String?
)