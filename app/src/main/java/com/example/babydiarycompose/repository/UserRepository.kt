package com.example.babydiarycompose.repository

import com.example.babydiarycompose.models.User

interface UserRepository {
    suspend fun createUser(body: User): Boolean
    suspend fun getUserByName()
    suspend fun loginUser()
}