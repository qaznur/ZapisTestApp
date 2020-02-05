package kz.nura.zapistestapp.network

import kz.nura.zapistestapp.domain.Salon
import kz.nura.zapistestapp.domain.SalonDetail
import kz.nura.zapistestapp.domain.SalonServices

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

data class NetworkSalonResponse(
    val salon: NetworkSalonDetail,
    val services: List<NetworkSalonService>
)

data class NetworkSalonDetail(
    val id: Long,
    val name: String,
    val address: String,
    val pictures: List<String>
)

data class NetworkSalonService(
    val id: Long,
    val name: String,
    val price: Int
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

fun List<NetworkSalonService>.asDomainModel(): List<SalonServices> {
    return map {
        SalonServices(
            id = it.id,
            name = it.name,
            price = it.price
        )
    }.toList()
}

fun NetworkSalonResponse.asDomainModel(): SalonDetail {
    return SalonDetail(
        id = salon.id,
        name = salon.name,
        address = salon.address,
        pictures = salon.pictures,
        services = services.asDomainModel()
    )
}