package com.example.curso_final_app.data.api

import com.example.curso_final_app.data.model.Post
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("posts") // Tomar lista de posts
    suspend fun getPosts(): List<Post>

    @GET("posts/{id}") // Tomar uno identificado por id
    suspend fun getPostById(@Path("id") id: Int): Post
}