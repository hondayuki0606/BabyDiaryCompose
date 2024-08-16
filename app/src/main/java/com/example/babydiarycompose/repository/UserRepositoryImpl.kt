package com.example.babydiarycompose.repository

import android.util.Log
import com.example.babydiarycompose.api.UserApi
import com.example.babydiarycompose.models.User
import javax.inject.Inject


class UserRepositoryImpl @Inject constructor(
    private val userApi: UserApi
) : UserRepository {

    override suspend fun createUser(user: User) {
        try {
            val ret = userApi.createUser(user)
        } catch (e: IllegalArgumentException) {
            Log.e("", e.stackTraceToString())
        }
    }
}
