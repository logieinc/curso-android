package com.example.curso_final_app.data.api

import com.example.curso_final_app.data.model.Post
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {
    @GET("posts")
    suspend fun getPosts(): List<Post>

    @GET("posts/{id}")
    suspend fun getPostById(@Path("id") id: Int): Post

    /*
    @POST("posts/{id}")
    suspend fun addPost(@Path("id") id: Int): Post
     */

}