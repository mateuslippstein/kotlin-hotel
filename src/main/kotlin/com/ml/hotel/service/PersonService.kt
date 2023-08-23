package com.ml.hotel.service

import com.ml.hotel.exception.DuplicatedDocumentException
import com.ml.hotel.model.Person
import com.ml.hotel.repository.PersonRepository
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.stereotype.Service
import javax.persistence.EntityNotFoundException

@Service
class PersonService(private val personRepository: PersonRepository) {

    fun getAllPersons(): List<Person> {
        return personRepository.findAll()
    }

    fun getPersonById(id: Long): Person {
        return personRepository.findById(id)
            .orElseThrow { EntityNotFoundException("Person not found with id: $id") }
    }

    fun createPerson(person: Person): Person {
        try {
            return personRepository.save(person)
        } catch (e: DataIntegrityViolationException) {
            throw DuplicatedDocumentException("Document already exists: ${person.document}")
        }
    }

    fun updatePerson(id: Long, updatedPerson: Person): Person {
        val person = getPersonById(id)

        person.firstName = updatedPerson.firstName
        person.lastName = updatedPerson.lastName
        person.email = updatedPerson.email

        return personRepository.save(person)
    }

    fun deletePerson(id: Long) {
        val person = getPersonById(id)
        personRepository.delete(person)
    }
}
