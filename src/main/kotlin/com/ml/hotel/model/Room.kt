package com.ml.hotel.model

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id

@Entity
data class Room(
    @Id
    var id: Long? = null, // Room number

    var singleBeds: Int = 0,
    var doubleBeds: Int = 0,
    var arConditioning: Boolean = false,
    var observation: String? = null,
    @Column(name = "is_deleted")
    var isDeleted: Boolean = false
)
