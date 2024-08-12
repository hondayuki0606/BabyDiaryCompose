package com.example.babydiarycompose.repository

import com.example.babydiarycompose.api.PostsApi
import javax.inject.Inject

class PostsRepositoryImpl @Inject constructor(
    private val postsApi: PostsApi
) : PostsRepository {

    override suspend fun getPost(id: Long) {
        val ret = postsApi.getPost(id)
    }

    override suspend fun getPosts() {
        val ret = postsApi.getPosts()
    }
}
