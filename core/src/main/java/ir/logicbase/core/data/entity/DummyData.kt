package ir.logicbase.core.data.entity

import com.google.gson.annotations.SerializedName
import ir.logicbase.core.ui.recyclerview.diffutil.Equatable

/**
 * Can be used for all data sources
 * if we want to use different data for each data source
 * we might consider using mappers (and maybe with a extra domain layer)
 */
data class DummyData (
    @field:SerializedName("name")
    val name: String = "",
    @field:SerializedName("title")
    val title: String = "",
    @field:SerializedName("category")
    val category: String = "",
    @field:SerializedName("price")
    val price: String = "",
    @field:SerializedName("shortText")
    val shortText: String = "",
    @field:SerializedName("longText")
    val longText: String = "",
    @field:SerializedName("address")
    val address: String = "",
    @field:SerializedName("profile")
    val profile: String = "",
    @field:SerializedName("imageRectangle")
    val imageRectangle: String = "",
    @field:SerializedName("imageSquare")
    val imageSquare: String = ""
): Equatable