package com.ml.hotel.model

import com.ml.hotel.util.RoomStatusEnum
import java.math.BigDecimal
import java.util.Date
import jakarta.persistence.*

@Entity
data class RoomBooking(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    var date: Date,

    @ManyToOne
    @JoinColumn(name = "room_id")
    var room: Room? = null,

    @ManyToOne
    @JoinColumn(name = "person_id")
    var person: Person? = null,

    @Enumerated(EnumType.STRING)
    var status: RoomStatusEnum? = null,

    var price: BigDecimal? = null
)
