package com.example.composeexample.data.mappers

import com.example.composeexample.data.datasources.remote.model.LocationDto
import com.example.composeexample.domain.model.LocationModel

fun LocationDto.toDomain() = LocationModel(
    latitude = latitude?.toDouble() ?: 0.0,
    longitude = longitude?.toDouble() ?: 0.0,
)