package com.example.curso_final_app

import android.os.Bundle
import android.util.Log
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.curso_final_app.data.repository.PostRepository
import com.example.curso_final_app.databinding.ActivityMainBinding
import com.google.firebase.crashlytics.FirebaseCrashlytics
import com.example.curso_final_app.util.RemoteConfigManager
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private  val TAG = MainActivity::class.java.simpleName
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
    override fun onStart(){
        super.onStart()
        FirebaseCrashlytics.getInstance().log("Entre a MainActivity")
        FirebaseCrashlytics.getInstance().setUserId("RinconDelLimon")
        FirebaseCrashlytics.getInstance().recordException(Exception("Error manual de prueba de carmen"))
      //  throw RuntimeException("TestCrash2")
        RemoteConfigManager.fetchAndActivate { success ->
                if (success) {
                    val label_carmen = RemoteConfigManager.getString("label_carmen")
                   //val enabled = RemoteConfigManager.getBoolean("feature_enabled")
                    Log.d(TAG, "Label: $label_carmen")
                } else {
                    Log.e("MainActivity", "Error al cargar Remote Config")
                }
        }
        lifecycleScope.launch {
            val repository = PostRepository()
            val posts = repository.fetchPosts()
            posts.forEach {
                Log.d("MainActivity", "Post: ${it.title}")
            }
        }
    }
}