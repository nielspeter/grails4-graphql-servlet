package io.strandberg.graphql

import com.coxautodev.graphql.tools.SchemaParser
import graphql.schema.GraphQLSchema
import graphql.servlet.GraphQLContext
import graphql.servlet.GraphQLServlet
import graphql.servlet.SimpleGraphQLServlet
import org.springframework.boot.web.servlet.ServletRegistrationBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

import javax.inject.Inject
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Configuration
class Config {

    @Inject
    Query query

    @Inject
    Mutation mutation

    @Bean
    ServletRegistrationBean servletRegistrationBean() {
        return new ServletRegistrationBean(graphQLServlet(), "/graphql");
    }

    @Bean
    GraphQLServlet graphQLServlet() {
        SimpleGraphQLServlet servlet = new SimpleGraphQLServlet(buildSchema()) {
            protected GraphQLContext createContext(Optional<HttpServletRequest> request, Optional<HttpServletResponse> response) {
                // Get JWT etc. .... request.getHeader("Authorization")
                Principal principal = new Principal(name: 'admin')
                return new AuthContext(principal, request, response);
            }
        };
        return servlet
    }

    @Bean
    GraphQLSchema buildSchema() {
        return SchemaParser.newParser()
                .file("schema.graphqls")
                .resolvers(query, mutation)
                .scalars(Scalars.dateTime)
                .build()
                .makeExecutableSchema()
    }


}
