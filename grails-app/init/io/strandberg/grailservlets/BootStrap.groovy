package io.strandberg.grailservlets


import io.strandberg.Person

import java.time.ZonedDateTime

class BootStrap {

    def init = { servletContext ->
        new Person(name: 'Lucas', createdAt: ZonedDateTime.now()).save()
        new Person(name: 'Niels Peter', createdAt: ZonedDateTime.now()).save()
    }
    def destroy = {
    }
}
