package com.brainycorp.tourism.auth.application.port.out

import com.brainycorp.tourism.auth.domain.User

interface ResgisterRepository {

    fun execute(user: User)
}