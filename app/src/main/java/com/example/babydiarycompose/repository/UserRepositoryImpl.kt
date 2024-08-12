package com.example.babydiarycompose.repository

import com.example.babydiarycompose.api.UserApi
import com.example.babydiarycompose.models.User
import javax.inject.Inject


class UserRepositoryImpl @Inject constructor(
    private val userApi: UserApi
) : UserRepository {

    override suspend fun createUser(user: User) {
        val ret = userApi.createUser(user)
    }
}
