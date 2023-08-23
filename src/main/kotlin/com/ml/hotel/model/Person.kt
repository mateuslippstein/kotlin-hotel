package com.ml.hotel.model

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

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
