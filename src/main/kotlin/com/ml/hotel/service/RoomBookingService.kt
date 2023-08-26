package com.ml.hotel.service

import com.ml.hotel.model.Person
import com.ml.hotel.model.Room
import com.ml.hotel.model.RoomBooking
import com.ml.hotel.repository.PersonRepository
import com.ml.hotel.repository.RoomBookingRepository
import com.ml.hotel.repository.RoomRepository
import com.ml.hotel.util.RoomStatusEnum
import jakarta.persistence.EntityNotFoundException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.Date

@Service
class RoomBookingService @Autowired constructor(
    private val roomBookingRepository: RoomBookingRepository,
    private val roomRepository: RoomRepository,
    private val personRepository: PersonRepository
) {

    fun createRoomBooking(newRoomBooking: RoomBooking): RoomBooking {
        val roomId = newRoomBooking.room?.id
        val personId = newRoomBooking.person?.id

        if (roomId == null || personId == null) {
            throw IllegalArgumentException("Room ID and Person ID must be provided")
        }

        val room = roomRepository.findById(roomId)
            .orElseThrow { EntityNotFoundException("Room not found with id: $roomId") }
        val person = personRepository.findById(personId)
            .orElseThrow { EntityNotFoundException("Person not found with id: $personId") }

        val date: Date = newRoomBooking.date
        val existingRoomBookings = roomBookingRepository.findByDateAndRoom(date, room)
        if (existingRoomBookings.isNotEmpty()) {
            throw IllegalArgumentException("A Room Booking already exists for the given date and room")
        }

        newRoomBooking.room = room
        newRoomBooking.person = person

        return roomBookingRepository.save(newRoomBooking)
    }

    fun updateRoomBooking(id: Long, updatedRoomBooking: RoomBooking): RoomBooking? {
        val roomBooking = roomBookingRepository.findById(id)
            .orElseThrow { EntityNotFoundException("RoomBooking not found with id: $id") }

        if (roomBooking != null) {
            val personId = updatedRoomBooking.person?.id

            if (personId == null) {
                throw IllegalArgumentException("Person ID must be provided")
            }

            val person = personRepository.findById(personId)
                .orElseThrow { EntityNotFoundException("Person not found with id: $personId") }

            roomBooking.person = person
            roomBooking.status = updatedRoomBooking.status
            return roomBookingRepository.save(roomBooking)
        }
        return null
    }

    fun getRoomBookingsByDate(date: Date): List<RoomBooking> {
        val roomBookings = roomBookingRepository.findByDate(date)
        val rooms = roomRepository.findAll()

        val roomBookingMap = roomBookings.associateBy { it.room?.id }

        val updatedRoomBookings = mutableListOf<RoomBooking>()

        for (room in rooms) {
            if (roomBookingMap.containsKey(room.id)) {
                updatedRoomBookings.add(roomBookingMap[room.id]!!)
            } else {
                val dummyRoomBooking = RoomBooking(null, date, room, null, RoomStatusEnum.AVAILABLE, null)
                updatedRoomBookings.add(dummyRoomBooking)
            }
        }

        return updatedRoomBookings
    }
}
