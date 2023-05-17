package com.eemmez.ktorauthhandlingapp

import android.os.Bundle
import android.widget.Button
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.eemmez.ktorauthhandlingapp.model.User
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.buttonLogin).setOnClickListener {
            mainViewModel.login(User("emir", "1234"))
        }

        findViewById<Button>(R.id.buttonAuthHello).setOnClickListener {
            mainViewModel.authHello()
        }
    }
}