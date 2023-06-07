package com.example.metro.Avtivity

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import com.example.metro.R

class LineThreeActivity : AppCompatActivity() {

    private lateinit var listView: ListView
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_line_three)

        listView = findViewById(R.id.list3)
        val line3 = mutableListOf(
            "Adly Mansour", "El Haykestab", "Omar ibn Al-Khattab", "Qubba", "Hisham Barakat",
            "El Nozha", "Nadi El Shams", "Alf Maskan", "Heliopolis", "Haroun", "Al Ahram", "Girls' College",
            "Stadium", "Fairgrounds", "Al Abbasiya", "Abdo Pasha", "Al Giza", "Bab El Shaareya", "Al-Ataba",
            "Gamal Abdel Nasser", "Maspiro", "Safaa Hegazy", "Kit Kat"
        )

        val arrayAdapter:ArrayAdapter<String> = ArrayAdapter(
            this, android.R.layout.simple_selectable_list_item, line3
        )
        listView.adapter = arrayAdapter

    }
}