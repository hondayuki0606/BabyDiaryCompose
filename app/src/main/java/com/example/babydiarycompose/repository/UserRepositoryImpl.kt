package com.example.babydiarycompose.repository

import android.util.Log
import com.example.babydiarycompose.api.UserApi
import com.example.babydiarycompose.models.User
import javax.inject.Inject


class UserRepositoryImpl @Inject constructor(
    private val userApi: UserApi
) : UserRepository {

    override suspend fun createUser(user: User): Boolean {
        try {
            val ret = userApi.createUser(user)
            return true
        } catch (e: IllegalArgumentException) {
            Log.e("", e.stackTraceToString())
        }
        return false
    }

    override suspend fun loginUser() {
        try {
            val ret = userApi.loginUser(username = "", password = "")
            val body = ret.body()
            Log.i("", "honda body=$body")
        } catch (e: IllegalArgumentException) {
            Log.e("", e.stackTraceToString())
        } catch (e: Exception) {

        }
    }
}
