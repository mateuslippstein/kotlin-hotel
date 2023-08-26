package com.ml.hotel.controller

import com.ml.hotel.model.RoomBooking
import com.ml.hotel.service.RoomBookingService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.format.annotation.DateTimeFormat
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.Date

@RestController
@RequestMapping("/api/room-bookings")
class RoomBookingController @Autowired constructor(private val roomBookingService: RoomBookingService) {

    @PostMapping
    fun createRoomBooking(@RequestBody roomBooking: RoomBooking): ResponseEntity<RoomBooking> {
        val createdRoomBooking = roomBookingService.createRoomBooking(roomBooking)
        return ResponseEntity.status(HttpStatus.CREATED).body(createdRoomBooking)
    }

    @PutMapping("/{id}")
    fun updateRoomBooking(@PathVariable id: Long, @RequestBody updatedRoomBooking: RoomBooking): ResponseEntity<RoomBooking> {
        val updatedBooking = roomBookingService.updateRoomBooking(id, updatedRoomBooking)
        return if (updatedBooking != null) {
            ResponseEntity.ok(updatedBooking)
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @GetMapping("/by-date/{date}")
    fun getRoomBookingsByDate(@PathVariable @DateTimeFormat(pattern = "MM-dd-yyyy") date: Date): ResponseEntity<List<RoomBooking>> {
        val roomBookings = roomBookingService.getRoomBookingsByDate(date)
        return if (roomBookings.isNotEmpty()) {
            ResponseEntity.ok(roomBookings)
        } else {
            ResponseEntity.noContent().build()
        }
    }
}
