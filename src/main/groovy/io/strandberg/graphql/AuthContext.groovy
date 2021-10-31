package io.strandberg.graphql


import graphql.servlet.GraphQLContext

import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 * Objects of this class are intended to be used as context during operation execution.
 * Can be used to obtain the currently logged-in user.
 */
class AuthContext extends GraphQLContext {

    private final Principal principal;

    AuthContext(Principal principal, Optional<HttpServletRequest> request, Optional<HttpServletResponse> response) {
        super(request, response)
        this.principal = principal
    }

    Principal getPrincipal() {
        return principal
    }
}
