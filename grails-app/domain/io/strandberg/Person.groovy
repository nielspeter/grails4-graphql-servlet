package io.strandberg

import java.time.ZonedDateTime


class Person {

    String name
    ZonedDateTime createdAt

    static constraints = {
        name blank: false
    }
}
