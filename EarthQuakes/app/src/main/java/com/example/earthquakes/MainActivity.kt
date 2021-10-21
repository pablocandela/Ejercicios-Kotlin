package com.example.earthquakes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.earthquakes.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.eqRecycle.layoutManager = LinearLayoutManager(this)

        var adapter = EqAdapter()
        binding.eqRecycle.adapter = adapter


        val viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        viewModel.eqList.observe(this, Observer {
            eqList ->
            adapter.submitList(eqList)
            handleEmptyView(eqList, binding)
        })
        //service.getLasHourEarthQuakes()
    }

    private fun handleEmptyView(eqList: MutableList<EarthQuake>, binding: ActivityMainBinding ) {
        if(eqList.isEmpty()){
            binding.eqEmptyText.visibility = View.VISIBLE
        }else{
            binding.eqEmptyText.visibility = View.GONE
        }
    }
}