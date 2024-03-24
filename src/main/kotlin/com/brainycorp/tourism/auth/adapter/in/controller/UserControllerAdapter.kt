package com.brainycorp.tourism.auth.adapter.`in`.controller

import com.brainycorp.tourism.auth.adapter.`in`.controller.model.AuthResponse
import com.brainycorp.tourism.auth.application.port.`in`.RegisterCommand
import com.brainycorp.tourism.auth.domain.User
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/users")
class UserControllerAdapter(
    val registerCommand: RegisterCommand,
) {

    @PostMapping("/register")
    fun register(@RequestBody user: User) : ResponseEntity<AuthResponse> {
        registerCommand.execute(user)
        return ResponseEntity(HttpStatus.CREATED)
    }
}