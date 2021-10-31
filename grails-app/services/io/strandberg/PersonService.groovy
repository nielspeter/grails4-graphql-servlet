package io.strandberg

import grails.gorm.transactions.Transactional

import java.time.ZonedDateTime

@Transactional
class PersonService {

    def getAll() {
        Person.all
    }

    Person create(String name) {
        new Person(name: name, createdAt: ZonedDateTime.now()).save()
    }
}
