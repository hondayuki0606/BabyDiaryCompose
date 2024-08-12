package com.example.babydiarycompose.repository


interface PostsRepository {
    suspend fun  getPost(id: Long)
    suspend fun  getPosts()
}
