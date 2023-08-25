package com.ml.hotel.controller

import com.ml.hotel.model.Room
import com.ml.hotel.service.RoomService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/rooms")
class RoomController @Autowired constructor(private val roomService: RoomService) {

    @GetMapping
    fun getAllRooms(): ResponseEntity<List<Room>> {
        val rooms = roomService.getAllRooms()
        return ResponseEntity.ok(rooms)
    }

    @GetMapping("/{id}")
    fun getRoomById(@PathVariable id: Long): ResponseEntity<Room> {
        val room = roomService.getRoomById(id)
        return ResponseEntity.ok(room)
    }

    @PostMapping
    fun createRoom(@RequestBody room: Room): ResponseEntity<Room> {
        val createdRoom = roomService.createRoom(room)
        return ResponseEntity.status(HttpStatus.CREATED).body(createdRoom)
    }

    @PutMapping("/{id}")
    fun updateRoom(@PathVariable id: Long, @RequestBody updatedRoom: Room): ResponseEntity<Room> {
        val room = roomService.updateRoom(id, updatedRoom)
        return ResponseEntity.ok(room)
    }

    @DeleteMapping("/{id}")
    fun deleteRoom(@PathVariable id: Long): ResponseEntity<Room> {
        val room = roomService.deleteRoom(id)
        return ResponseEntity.ok(room)
    }
}
