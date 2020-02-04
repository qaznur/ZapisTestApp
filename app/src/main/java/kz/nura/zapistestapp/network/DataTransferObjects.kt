package kz.nura.zapistestapp.network

import kz.nura.zapistestapp.domain.Salon

data class NetworkResponse(
    val salons: List<NetworkSalon>
)

data class NetworkSalon(
    val id: Long,
    val name: String,
    val type: String,
    val checkRating: Double,
    val pictureUrl: String?
)

fun NetworkResponse.asDomainModel(): List<Salon> {
    return salons.map {
        Salon(
            id = it.id,
            name = it.name,
            type = it.type,
            checkRating = it.checkRating,
            pictureUrl = it.pictureUrl
        )
    }.toList()
}