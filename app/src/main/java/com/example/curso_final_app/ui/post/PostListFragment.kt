package com.example.curso_final_app.ui.post

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.coroutines.launch
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.curso_final_app.R
import com.example.curso_final_app.data.repository.PostRepository
import com.example.curso_final_app.databinding.FragmentPostListBinding


/**
 * A simple [Fragment] subclass.
 * Use the [PostListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class PostListFragment : Fragment() {

    private var _binding: FragmentPostListBinding? = null
    private val binding get() = _binding!!

    private val adapter by lazy {
        PostAdapter(emptyList(),
            onEdit = { post -> Log.d("Edit", "Editar: ${post.id}") },
            onDelete = { post -> Log.d("Delete", "Eliminar: ${post.id}") }
        )
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentPostListBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvPosts.layoutManager = LinearLayoutManager(requireContext())
        binding.rvPosts.adapter = adapter

        loadPosts()

        binding.swipeRefreshLayout.setOnRefreshListener {
            loadPosts()
        }
    }

    private fun loadPosts() {
        binding.swipeRefreshLayout.isRefreshing = true

        lifecycleScope.launch {
            try {
                val posts = PostRepository().fetchPosts()
                adapter.updateList(posts)
            } catch (e: Exception) {
                Log.e("PostListFragment", "Error al cargar posts", e)
            } finally {
                binding.swipeRefreshLayout.isRefreshing = false
            }
        }
    }
}