package io.strandberg.graphql

import com.coxautodev.graphql.tools.GraphQLRootResolver
import io.strandberg.Person
import io.strandberg.PersonService
import org.springframework.stereotype.Component

import javax.inject.Inject

@Component
class Query implements GraphQLRootResolver {

    @Inject
    PersonService personService

    List<Person> allPersons() {
        return personService.all
    }

}


