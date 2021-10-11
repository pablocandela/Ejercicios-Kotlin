package com.example.registrosuperheroes

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.ImageView
import androidx.core.graphics.drawable.toBitmap
import com.example.registrosuperheroes.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var heroImage: ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSave.setOnClickListener{
            val superHeroName = binding.heroNameInput.text.toString()
            val superHeroNickname = binding.nicknameInput.text.toString()
            val superHeroBio = binding.bioText.text.toString()
            val superHeroPower = binding.powerRating.rating
            val superHero = SuperHero(superHeroName, superHeroNickname, superHeroBio, superHeroPower)
            openDetailActivity(superHero, heroImage.drawable.toBitmap())
        }
        heroImage = binding.heroImage
        heroImage.setOnClickListener{
            openCamera()
        }
    }

    private fun openCamera() {
        val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(cameraIntent, 1000)
    }

    private fun openDetailActivity(superHero: SuperHero, heroImage: Bitmap){
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra("super_hero", superHero)
        intent.putExtra("hero_image", heroImage)
        startActivity(intent)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode == Activity.RESULT_OK && requestCode == 1000){
            val extras = data?.extras
            val heroBitmap = extras?.getParcelable<Bitmap>("data")
            heroImage.setImageBitmap(heroBitmap)
        }
    }
}