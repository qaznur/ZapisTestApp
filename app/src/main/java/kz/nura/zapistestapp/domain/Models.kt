package kz.nura.zapistestapp.domain

data class Salon(
    val id: Long,
    val name: String,
    val type: String,
    val checkRating: Double,
    val pictureUrl: String?
)

data class SalonDetail(
    val id: Long,
    val name: String,
    val address: String,
    val pictures: List<String>,
    val services: List<SalonService>
)

data class SalonService(
    val id: Long,
    val name: String,
    val price: Int
)