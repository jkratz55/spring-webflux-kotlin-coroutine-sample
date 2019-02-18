package io.jkratz.kotlinflux.demo

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.Unconfined
import kotlinx.coroutines.reactive.awaitFirst
import kotlinx.coroutines.reactive.awaitFirstOrDefault
import kotlinx.coroutines.reactive.awaitLast
import kotlinx.coroutines.reactor.flux
import kotlinx.coroutines.reactor.mono

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@RestController
class ReactiveController(private val userRepository: UserRepository,
                         private val messageRepository: MessageRepository) {

    @GetMapping("/reactiveCoroutine")
    fun getMessages(): Mono<Message> = CoroutineScope(Unconfined).mono {

        val users = userRepository.findAll().awaitFirst()
        val messages = messageRepository.findAll().awaitFirst()
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