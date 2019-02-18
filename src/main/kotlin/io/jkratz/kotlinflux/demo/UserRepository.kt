package io.jkratz.kotlinflux.demo

import org.bson.types.ObjectId
import org.springframework.data.mongodb.repository.ReactiveMongoRepository
import java.util.*

interface UserRepository: ReactiveMongoRepository<User, ObjectId> {
}