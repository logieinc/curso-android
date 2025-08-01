package com.example.curso_final_app.ui.post

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.curso_final_app.R
import com.example.curso_final_app.data.model.Post

class PostAdapter (
    private var posts: List<Post>,
    private val onEdit: (Post) -> Unit,
    private val onDelete: (Post) -> Unit
) : RecyclerView.Adapter<PostAdapter.PostViewHolder>() {

    class PostViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvTitle: TextView = view.findViewById(R.id.postTitle)
        val tvBody: TextView = view.findViewById(R.id.postBody)
        val btnEdit: Button = view.findViewById(R.id.btnEdit)
        val btnDelete: Button = view.findViewById(R.id.btnDelete)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_post, parent, false)
        return PostViewHolder(view)
    }

    override fun getItemCount(): Int = posts.size

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
       val post = posts[position]
        holder.tvTitle.text = post.title
        holder.tvBody.text = post.body

        holder.btnEdit.setOnClickListener { onEdit(post) }
        holder.btnDelete.setOnClickListener { onDelete(post) }

    }


}