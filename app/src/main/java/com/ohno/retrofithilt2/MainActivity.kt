package com.ohno.retrofithilt2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ohno.retrofithilt2.databinding.ActivityMainBinding
import com.ohno.retrofithilt2.ui.fragment.LoginFragment
import com.ohno.retrofithilt2.util.PageUtils
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        PageUtils.replaceFragment(this, LoginFragment())
    }
}