package com.ml.hotel.util

enum class RoomStatusEnum(private val displayName: String) {
    AVAILABLE("Available"),
    RESERVED("Reserved"),
    OCCUPIED("Occupied"),
    PAID("Paid"),
    CHECKED_OUT("Checked Out");

    fun getDisplayName(): String {
        return displayName
    }
}
