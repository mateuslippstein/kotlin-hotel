package com.ml.hotel.repository

import com.ml.hotel.model.Room
import com.ml.hotel.model.RoomBooking
import com.ml.hotel.util.RoomStatusEnum
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.Date

@Repository
interface RoomBookingRepository : JpaRepository<RoomBooking, Long> {
    fun findByDate(date: Date): List<RoomBooking>
    fun findByDateAndRoom(date: Date, room: Room): List<RoomBooking>
    fun findByRoomIdAndStatus(roomId: Long, status: RoomStatusEnum): List<RoomBooking>
    fun findByPersonIdAndStatus(personId: Long, status: RoomStatusEnum): List<RoomBooking>
}
