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
            userApi.createUser(user).let {
                if (it.isSuccessful) {
                    val body = it.body()
                    Log.i("createUser", "success. ret=$it")
                    Log.i("createUser", "success. body=$body")
                    return true
                } else {
                    return false
                }
            }
        } catch (e: IllegalArgumentException) {
            Log.e("", e.stackTraceToString())
        }
        return false
    }

    override suspend fun getUserByName() {
        try {
           userApi.getUserByName(username = "username").let {
                if (it.isSuccessful) {
                    val body = it.body()
                    Log.i("getUserByName", "success. ret=$it")
                    Log.i("getUserByName", "success. body=$body")
                } else {

                }
            }
        } catch (e: Exception) {
            Log.e("getUserByName", "failure. ${e.stackTraceToString()}")
        }
    }

    override suspend fun loginUser() {
        try {
            val ret = userApi.loginUser(username = "1", password = "1")
            val body = ret.body()
            Log.i("loginUser", "success. body=$body")
        } catch (e: Exception) {
            Log.e("loginUser", "failure. ${e.stackTraceToString()}")
        }
    }
}
