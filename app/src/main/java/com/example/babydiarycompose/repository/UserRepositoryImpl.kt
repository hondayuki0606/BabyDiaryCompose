package com.example.babydiarycompose.repository

import com.example.babydiarycompose.api.UserApi
import com.example.babydiarycompose.data.EventData
import com.example.babydiarycompose.model.Event
import com.example.babydiarycompose.models.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor() : UserRepository {

    override suspend fun createUser(user: User): Unit {
        val ret = UserApi().createUser(user)
    }

}
