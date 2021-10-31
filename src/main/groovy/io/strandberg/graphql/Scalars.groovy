package io.strandberg.graphql

import graphql.language.IntValue
import graphql.language.StringValue
import graphql.schema.Coercing
import graphql.schema.GraphQLScalarType

import java.time.Instant
import java.time.ZoneOffset
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

/**
 * Class containing custom scalars 
 */
class Scalars {
    
    static GraphQLScalarType dateTime = new GraphQLScalarType("DateTime", "DataTime scalar", new Coercing() {
        @Override
        String serialize(Object input) {
            return ((ZonedDateTime)input).format(DateTimeFormatter.ISO_OFFSET_DATE_TIME);
        }

        @Override
       Object parseValue(Object input) {
            return serialize(input);
        }

        @Override
        ZonedDateTime parseLiteral(Object input) {
            if (input instanceof StringValue) {
                return ZonedDateTime.parse(((StringValue) input).getValue());
            } else if (input instanceof IntValue) {
                return Instant.ofEpochMilli(((IntValue) input).getValue().longValue()).atZone(ZoneOffset.UTC);
            } else {
                return null;
            }
        }
    });
}
