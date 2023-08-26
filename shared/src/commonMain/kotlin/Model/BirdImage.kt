package Model

import kotlinx.serialization.Serializable
@Serialization
data class BirdImage(
    val author: String,
    val category: String,
    val path: String
)