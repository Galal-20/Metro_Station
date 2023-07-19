package com.example.metro.Avtivity.Activities

import android.annotation.SuppressLint
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.metro.Avtivity.Adapter.Adapter
import com.example.metro.Avtivity.DataBase.MetroDatabase
import com.example.metro.R

class HistoryActivity : AppCompatActivity() {
    lateinit var recyclerView: RecyclerView
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)

        recyclerView = findViewById(R.id.recycle)

        val all = MetroDatabase.getDatabase(this).metroDao().getAllData()

        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.VERTICAL)
        recyclerView.adapter = Adapter(all)



    }
}