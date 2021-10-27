package com.example.earthquakes.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.earthquakes.EarthQuake
import com.example.earthquakes.R
import com.example.earthquakes.api.ApiResponseStatus
import com.example.earthquakes.api.WorkerUtil
import com.example.earthquakes.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private  lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.eqRecycle.layoutManager = LinearLayoutManager(this)

        WorkerUtil.scheduleSync(this)

        var adapter = EqAdapter()
        binding.eqRecycle.adapter = adapter

        val sortPref = getSortPref()
        viewModel = ViewModelProvider(this, MainViewModelFactory(application,sortPref)).get(MainViewModel::class.java)
        viewModel.eqList.observe(this, Observer {
            eqList ->
            adapter.submitList(eqList)
            handleEmptyView(eqList, binding)
        })

        viewModel.status.observe(this, Observer {
                apiResponseStatus ->
                if(apiResponseStatus == ApiResponseStatus.LOADING){
                    binding.loading.visibility = View.VISIBLE
                }else if(apiResponseStatus == ApiResponseStatus.DONE){
                    binding.loading.visibility = View.GONE
                }else{
                    binding.loading.visibility = View.GONE
                }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    fun saveSortType(sortByMagnitude: Boolean){
        val prefs = getSharedPreferences("eq_prefs", MODE_PRIVATE)
        val editor = prefs.edit()
        editor.putBoolean("sort_type", sortByMagnitude)
        editor.apply()
    }

    fun getSortPref():Boolean {
        val prefs = getSharedPreferences("eq_prefs", MODE_PRIVATE)
        return prefs.getBoolean("sort_type",false)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val itemId = item.itemId
        if(itemId == R.id.main_menu_sort_time){
            viewModel.reloadEarthQuake(false)
            saveSortType(false)
        }else if(itemId == R.id.main_menu_sort_magnitude){
            viewModel.reloadEarthQuake(true)
            saveSortType(true)
        }
        return super.onOptionsItemSelected(item)
    }

    private fun handleEmptyView(eqList: MutableList<EarthQuake>, binding: ActivityMainBinding ) {
        if(eqList.isEmpty()){
            binding.eqEmptyText.visibility = View.VISIBLE
        }else{
            binding.eqEmptyText.visibility = View.GONE
        }
    }
}