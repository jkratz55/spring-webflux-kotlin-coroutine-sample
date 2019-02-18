package io.jkratz.kotlinflux.demo

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.Unconfined
import kotlinx.coroutines.reactive.awaitFirstOrDefault
import kotlinx.coroutines.reactive.awaitLast
import kotlinx.coroutines.reactor.flux

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux

@RestController
class ReactiveController(private val userRepository: UserRepository,
                         private val messageRepository: MessageRepository) {

    @GetMapping("/reactiveCoroutine")
    fun getMessages(): Flux<Message> = CoroutineScope(Unconfined).flux {
//        userRepository.findById("joejoe").awaitFirstOrDefault(null)?.let {
//            val messages = messageRepository.findAll().awaitLast()
//            messages
//        }

        val users = userRepository.findAll()
        val messages = messageRepository.findAll()
        messages
    }

    @GetMapping("/reactive")
    fun getMessagesReactive(): Flux<Message> {
        return userRepository.findAll()
                .next()
                .flatMapMany { u ->
                    messageRepository.findAll()
                }
    }
}