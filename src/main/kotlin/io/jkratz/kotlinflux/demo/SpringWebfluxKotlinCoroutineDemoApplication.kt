package io.jkratz.kotlinflux.demo

import org.bson.types.ObjectId
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import java.time.Instant
import java.util.*

@SpringBootApplication
class SpringWebfluxKotlinCoroutineDemoApplication(val userRepository: UserRepository,
                                                  val messageRepository: MessageRepository): ApplicationRunner {

    override fun run(args: ApplicationArguments?) {

        val userJoe = User(firstName = "Joe",
                lastName = "Joe",
                email = "joejoe@joe.com",
                id = ObjectId())

        val userBeks = User(firstName = "Bekah",
                lastName = "Beks",
                email = "bekah.beks@bek.com",
                id = ObjectId())

        val message1 = Message(toUser = userBeks,
                fromUser = userJoe,
                content = "Hey what is happening",
                timestamp = Instant.now(),
                id = ObjectId())

        val message2 = Message(toUser = userBeks,
                fromUser = userJoe,
                content = "Not much, just thinking about sleep",
                timestamp = Instant.now(),
                id = ObjectId())

        userRepository.save(userBeks).subscribe()
        userRepository.save(userJoe).subscribe()

        messageRepository.save(message1).subscribe()
        messageRepository.save(message2).subscribe()
    }
}

fun main(args: Array<String>) {
    runApplication<SpringWebfluxKotlinCoroutineDemoApplication>(*args)
}
