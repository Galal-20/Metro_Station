package com.example.metro.Avtivity.Activities

import android.annotation.SuppressLint
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.annotation.RequiresApi
import com.example.metro.R

class LineOneActivity : AppCompatActivity() {
    private lateinit var listView:ListView
    @RequiresApi(Build.VERSION_CODES.Q)
    @SuppressLint("ResourceAsColor")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_line_one)

        listView = findViewById(R.id.list1)

        val line1 = arrayListOf(
            "New-Marg", "Marg", "Ezbet-al-Nakhl", "Ain-Shams", "Almatria",
            "Hilmiat-alzaytun", "Hadayiq-alzaytuni", "Saray al-Qobba", "HamaamatAlquba", "kubri-alquba",
            "Manshiyat", "Demerdash", "Ghamra", "Alshuhada", "AhmedOrabi", "Abdulnasser",
            "Alsadat", "Zaghloul", "zaynab", "Almalik-alsaalih", "MarGerges",
            "Zahra", "Dar AISalaam", "HadayiqMaadi", "Maadi", "ThakanatAlmaeadi", "tiratAlbalad",
            "Kotska", "tirat-al'asmant", "almaesara", "hadayiqHalwan", "wadi-hawf", "JamieatHulwan",
            "Ain-Helwan", "Helwan")


        val arrayAdapter:ArrayAdapter<String> = ArrayAdapter(
            this, android.R.layout.simple_selectable_list_item, line1)

        listView.adapter = arrayAdapter

    }

}