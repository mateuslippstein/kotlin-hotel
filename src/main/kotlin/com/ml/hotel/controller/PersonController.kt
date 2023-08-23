package com.ml.hotel.controller

import com.ml.hotel.model.Person
import com.ml.hotel.service.PersonService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/persons")
class PersonController(private val personService: PersonService) {

    @GetMapping
    fun getAllPersons(): ResponseEntity<List<Person>> {
        val persons = personService.getAllPersons()
        return ResponseEntity.ok(persons)
    }

    @GetMapping("/{id}")
    fun getPersonById(@PathVariable id: Long): ResponseEntity<Person> {
        val person = personService.getPersonById(id)
        return ResponseEntity.ok(person)
    }

    @PostMapping
    fun createPerson(@RequestBody person: Person): ResponseEntity<Person> {
        val createdPerson = personService.createPerson(person)
        return ResponseEntity.status(HttpStatus.CREATED).body(createdPerson)
    }

    @PutMapping("/{id}")
    fun updatePerson(@PathVariable id: Long, @RequestBody updatedPerson: Person): ResponseEntity<Person> {
        val person = personService.updatePerson(id, updatedPerson)
        return ResponseEntity.ok(person)
    }

    @DeleteMapping("/{id}")
    fun deletePerson(@PathVariable id: Long): ResponseEntity<Void> {
        personService.deletePerson(id)
        return ResponseEntity.noContent().build()
    }
}
