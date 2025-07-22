package com.example.curso_final_app

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.curso_final_app.databinding.ActivityMainBinding
import com.google.firebase.crashlytics.FirebaseCrashlytics
import kotlinx.coroutines.launch
import androidx.lifecycle.lifecycleScope
import com.example.curso_final_app.data.repository.PostRepository
import android.util.Log



class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

    override fun onStart() {
        super.onStart()

        FirebaseCrashlytics.getInstance().log("Entré a MainActivity JDR")
        FirebaseCrashlytics.getInstance().setUserId("usuarioJDR")
        FirebaseCrashlytics.getInstance().recordException(Exception("Error manual de prueba JDR"))
        // throw RuntimeException("Test Crash") // Force a crash


        lifecycleScope.launch {
            val repository = PostRepository()
            val posts = repository.fetchPosts()
            posts.forEach {
                Log.d("MainActivity", "Post: ${it.title}")
            }

        }

    }

}