package com.ml.hotel.repository

import org.springframework.data.jpa.repository.JpaRepository
import com.ml.hotel.model.Room
import org.springframework.stereotype.Repository


@Repository
interface RoomRepository : JpaRepository<Room, Long> {
}