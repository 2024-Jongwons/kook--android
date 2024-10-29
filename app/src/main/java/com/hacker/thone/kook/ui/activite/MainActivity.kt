package com.hacker.thone.kook.ui.activite

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.hacker.thone.kook.R
import com.hacker.thone.kook.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)


    }
}