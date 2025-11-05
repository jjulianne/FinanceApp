package com.example.financeapp.data.repository.network.dto

data class UserNetworkDto(
    val id: Int,
    val email: String,
    val username: String,
    val name: NameDto,
    val address: AddressDto,
    val phone: String
)

data class NameDto(
    val firstname: String,
    val lastname: String
)

data class AddressDto(
    val geolocation: GeolocationDto,
    val city: String,
    val street: String,
    val number: Int,
    val zipcode: String
)

data class GeolocationDto(
    val lat: String,
    val long: String
)