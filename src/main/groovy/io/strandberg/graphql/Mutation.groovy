package io.strandberg.graphql

import com.coxautodev.graphql.tools.GraphQLMutationResolver
import io.strandberg.Person
import io.strandberg.PersonService
import graphql.schema.DataFetchingEnvironment
import org.springframework.stereotype.Component

import javax.inject.Inject

@Component
class Mutation implements GraphQLMutationResolver {

    @Inject
    PersonService personService

    Person createPerson(String name, DataFetchingEnvironment env) {
        AuthContext context = env.getContext()
        Principal principal = context.principal
        println 'created by ' + principal.name
        personService.create(name)
    }

}
