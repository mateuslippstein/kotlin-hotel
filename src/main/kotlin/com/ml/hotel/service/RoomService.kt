package com.ml.hotel.service

import com.ml.hotel.model.Room
import com.ml.hotel.repository.RoomRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import jakarta.persistence.EntityNotFoundException

@Service
class RoomService @Autowired constructor(private val roomRepository: RoomRepository) {

    fun getAllRooms(): List<Room> {
        return roomRepository.findAll()
    }

    fun getRoomById(id: Long): Room {
        return roomRepository.findById(id)
            .orElseThrow { EntityNotFoundException("Room not found with id: $id") }
    }

    fun createRoom(room: Room): Room {
        return roomRepository.save(room)
    }

    fun updateRoom(id: Long, updatedRoom: Room): Room {
        val room = getRoomById(id)

        room.singleBeds = updatedRoom.singleBeds
        room.doubleBeds = updatedRoom.doubleBeds

        return roomRepository.save(room)
    }

    fun deleteRoom(id: Long): Room {
        val room = getRoomById(id)
        room.isDeleted = true
        return roomRepository.save(room)
    }
}
