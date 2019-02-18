package io.jkratz.kotlinflux.demo

import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Field
import java.util.*

class User(@Id val id: ObjectId,
           @Field val firstName: String,
           @Field val lastName: String,
           @Field val email: String) {
}