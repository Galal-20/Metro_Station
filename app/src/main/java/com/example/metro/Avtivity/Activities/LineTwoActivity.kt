package com.example.metro.Avtivity.Activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import com.example.metro.R

class LineTwoActivity : AppCompatActivity() {

    private lateinit var listView:ListView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_line_two)

        listView = findViewById(R.id.list2)
        val line2 = arrayListOf(
            "Almunib", "Almisriiyn", "Giza", "Faisal", "CairoUniversity", "Albuhuth",
            "Dokki", "Opera", "Alsadat", "Najeeb", "Aleataba", "Alshuhada", "Masara", "RwadAlfaraj", "SantTiriz",
            "Alkhalafawi", "Mizalaat", "FacultyOfAgriculture", "ShubraAlkhaimah"
        )
        val arrayAdapter:ArrayAdapter<String> = ArrayAdapter(
            this,android.R.layout.simple_selectable_list_item , line2
        )
        listView.adapter = arrayAdapter
    }
}