package com.company.repathapp.Repo

data class PotholeLocation(
    val snappedPoints: List<SnappedPoint>
)

data class SnappedPoint(
    val location: Location,
    val originalIndex: Int,
    val placeId: String
)

data class Location(
    val latitude: Double,
    val longitude: Double
)