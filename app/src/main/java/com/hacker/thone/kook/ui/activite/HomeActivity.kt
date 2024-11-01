package com.hacker.thone.kook.ui.activite

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import androidx.transition.Visibility
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.hacker.thone.kook.R
import com.hacker.thone.kook.databinding.ActivityHomeBinding
import com.hacker.thone.kook.util.BottomControllable

class HomeActivity : AppCompatActivity(), BottomControllable {
    private lateinit var binding: ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val navView: BottomNavigationView = binding.navView
        val navController =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment_activity_home) as NavHostFragment

        navView.setupWithNavController(navController.navController)
    }

    override fun setBottomNavVisibility(visibility: Boolean) {
        binding.navView.visibility = if (visibility){
            View.VISIBLE
        }
        else{
            View.GONE
        }
    }
}