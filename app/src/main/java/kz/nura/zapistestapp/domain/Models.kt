package kz.nura.zapistestapp.domain

data class Salon(
    val id: Long,
    val name: String,
    val type: String,
    val checkRating: Double,
    val pictureUrl: String?
)