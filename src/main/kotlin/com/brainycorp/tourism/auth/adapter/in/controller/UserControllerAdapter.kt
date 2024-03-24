package com.brainycorp.tourism.auth.adapter.`in`.controller

import com.brainycorp.tourism.auth.adapter.`in`.controller.model.AuthResponse
import com.brainycorp.tourism.auth.application.port.`in`.RegisterCommand
import com.brainycorp.tourism.auth.domain.User
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/users")
class UserControllerAdapter(
    val registerCommand: RegisterCommand,
    val authenticationManager: AuthenticationManager
) {

    @PostMapping("/register")
    @CrossOrigin("*")
    fun register(@RequestBody user: User) : ResponseEntity<Any> {
        registerCommand.execute(user)
        return ResponseEntity(HttpStatus.CREATED)
    }

    @PostMapping("/login")
    @CrossOrigin("*")
    fun login(@RequestBody loginRequest: User): ResponseEntity<AuthResponse> {
        val authenticationRequest =
            UsernamePasswordAuthenticationToken.unauthenticated(
                loginRequest.username, loginRequest.password)
        val authenticationResponse =
            authenticationManager.authenticate(authenticationRequest)
        val auth: MutableList<String> = mutableListOf()
        authenticationResponse.authorities.map { auth.add(it.authority) }

        return ResponseEntity(AuthResponse(loginRequest.username, auth), HttpStatus.CREATED)
    }


}