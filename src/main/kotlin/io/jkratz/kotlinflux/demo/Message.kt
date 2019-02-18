package io.jkratz.kotlinflux.demo

import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Field
import java.time.Instant

class Message(@Id val id: ObjectId,
              @Field val toUser: User,
              @Field val fromUser: User,
              @Field val content: String,
              @Field val timestamp: Instant) {
}