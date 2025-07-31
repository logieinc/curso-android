package com.example.curso_final_app.data.repository

import com.example.curso_final_app.data.api.RetrofitClient
import com.example.curso_final_app.data.model.Post

class PostRepository {
    suspend fun fetchPosts(): List<Post> = RetrofitClient.api.getPosts()

    suspend fun fetchPostsByUser(userId: Int): List<Post> {
        val posts = RetrofitClient.api.getPosts()
        return posts.filter { it.userID == userId }
    }
}