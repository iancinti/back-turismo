package com.brainycorp.tourism.auth.application.port.out

import com.brainycorp.tourism.auth.adapter.out.jdbc.model.UserAuth

interface LoginRepository {
    fun execute(username: String?): UserAuth
}