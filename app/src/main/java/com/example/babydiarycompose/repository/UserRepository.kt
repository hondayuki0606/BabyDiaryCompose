package com.example.babydiarycompose.repository

import com.example.babydiarycompose.models.User

interface UserRepository {
    suspend fun  createUser(body: User)
}