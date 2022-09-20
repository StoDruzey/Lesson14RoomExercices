package com.example.lesson14roomexercices

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity(R.layout.activity_main) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportFragmentManager
            .beginTransaction()
            .add(R.id.container, DatabaseFragment())
            .addToBackStack(null)
            .commit()
    }
}