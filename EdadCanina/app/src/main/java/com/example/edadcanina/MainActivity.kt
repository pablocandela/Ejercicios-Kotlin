package com.example.edadcanina

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.edadcanina.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnCalculate.setOnClickListener{
            updateAgeResult(binding)
        }
    }

    fun updateAgeResult(binding: ActivityMainBinding){
        val ageString = binding.inputAge.text.toString()
        if(ageString.isNotEmpty()){
            val ageInt = ageString.toInt()
            val result = ageInt * 7
            binding.resultText.text = getString(R.string.result_text, result)
        }else{
            Toast.makeText(this, R.string.you_must_insert_your_age, Toast.LENGTH_SHORT).show()
        }
    }
}