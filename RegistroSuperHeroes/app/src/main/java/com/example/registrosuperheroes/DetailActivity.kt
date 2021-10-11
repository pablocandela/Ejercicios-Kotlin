package com.example.registrosuperheroes

import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.registrosuperheroes.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bundle = intent.extras!!
        val superHero = bundle.getParcelable<SuperHero>("super_hero")!!
        val hero_image = bundle.getParcelable<Bitmap>("hero_image")

        binding.superhero = superHero
        binding.heroImage.setImageBitmap(hero_image)
    }
}