package com.ml.hotel.model

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity
data class Person(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,

    var firstName: String = "",
    var lastName: String = "",
    var email: String = "",

    @Column(unique = true)
    var document: String = ""
)
